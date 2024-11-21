package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiScoreMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.mapper.StudyEfficiencyMapper;
import com.gcc.pojo.DwiScore;
import com.gcc.pojo.StudyDuration;
import com.gcc.pojo.StudyEfficiency;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yp
 * @date: 2023/10/25
 */
@SpringBootTest
@TypeExcludeFilters({ListenerExcludeFilter.class})
public class StudyEffiencyTest {

    @Resource
    private DwiScoreMapper dwiScoreMapper;

    @Resource
    private StudyDurationMapper studyDurationMapper;

    @Resource
    private StudyEfficiencyMapper studyEfficiencyMapper;

    @Test
    void testAdd() {
        List<DwiScore> dwiScores = dwiScoreMapper.selectList(new LambdaQueryWrapper<DwiScore>());
        List<StudyEfficiency> studyEfficiencies = new ArrayList<>();
        for (DwiScore dwiScore : dwiScores) {
            String userId = dwiScore.getUserNumber();
            BigDecimal score = dwiScore.getScore();
            if (score == null) {
                score = new BigDecimal(40);
            }
            double scoreDouble = score.doubleValue();
            List<StudyEfficiency> calculate = calculate(userId, scoreDouble);
            studyEfficiencies.addAll(calculate);
        }
        // 按课程分组
        Map<String, List<StudyEfficiency>> collect = studyEfficiencies.stream().collect(Collectors.groupingBy(StudyEfficiency::getCourseId));
        // 课程平均进度 - 按课程算平均值
        Map<String, Float> avgMap = new HashMap<>();
        collect.forEach((name, mapByNameList) -> {
            OptionalDouble averageOpt = mapByNameList.stream().mapToDouble(map2 -> Double.parseDouble(map2.getEfficiencyScore())).average();
            avgMap.put(name, Float.parseFloat(averageOpt.getAsDouble() + ""));

        });
        studyEfficiencies.stream().forEach(tt -> {
            String courseId = tt.getCourseId();
            if (avgMap.containsKey(courseId)) {
                tt.setAvgEfficiencyScore(String.valueOf(avgMap.get(courseId)));
            } else {
                tt.setAvgEfficiencyScore(String.valueOf(0.4F));
            }
        });
        // 使用stream流过滤studyEfficiencies中userId和courseId重复的
        studyEfficiencies = studyEfficiencies.stream().distinct().collect(Collectors.toList());
        studyEfficiencyMapper.batchInsert(studyEfficiencies);
    }

    private List<StudyEfficiency> calculate(String userId, Double score) {
        LambdaQueryWrapper<StudyDuration> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StudyDuration::getStudentId, userId);
        List<StudyDuration> studyDurations = studyDurationMapper.selectList(lqw);
        if (score == null) {
            score = 40.0;
        }
        double totalScore = score;
        List<StudyEfficiency> studyEfficiencies = new ArrayList<>();

        for (StudyDuration studyDuration : studyDurations) {
            String courseId = studyDuration.getCourseId();
            double rate = getRate(studyDuration);
            double effScore = rate * totalScore;
            StudyEfficiency studyEfficiency = new StudyEfficiency(userId, courseId, effScore / 100 + "", "");
            studyEfficiencies.add(studyEfficiency);
        }
        return studyEfficiencies;
    }

    private double getRate(StudyDuration studyDuration) {
        double videoWatchScore = Double.parseDouble(studyDuration.getVideoWatchScore());
        double fileWatchScore = Double.parseDouble(studyDuration.getFileWatchScore());
        double testTimeScore = Double.parseDouble(studyDuration.getTestTimeScore());
        double discussionScore = Double.parseDouble(studyDuration.getDiscussionScore());
        double examinationTimeScore = Double.parseDouble(studyDuration.getExaminationTimeScore());

        if (videoWatchScore == 0) {
            return 0.4;
        }

        double res = videoWatchScore / (videoWatchScore + fileWatchScore + testTimeScore + discussionScore + examinationTimeScore);
        if (res < 0.5) {
            return 1;
        } else if (res > 0.5 && res < 0.7) {
            return 0.8;
        } else if (res > 0.7 && res < 0.9) {
            return 0.7;
        } else {
            return 0.6;
        }
    }
}
