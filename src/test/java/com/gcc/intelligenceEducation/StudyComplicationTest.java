package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiScoreMapper;
import com.gcc.mapper.StudyComplicationMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.pojo.DwiScore;
import com.gcc.pojo.StudyComplication;
import com.gcc.pojo.StudyDuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yp
 * @date: 2023/10/26
 */
@SpringBootTest
@TypeExcludeFilters({ListenerExcludeFilter.class})
public class StudyComplicationTest {

    @Resource
    private DwiScoreMapper dwiScoreMapper;

    @Resource
    private StudyDurationMapper studyDurationMapper;

    @Resource
    private StudyComplicationMapper studyComplicationMapper;

    @Test
    void syncStudyComplication() {
        List<DwiScore> dwiScores = dwiScoreMapper.selectList(new LambdaQueryWrapper<DwiScore>());
        List<StudyComplication> studyComplicationList = new ArrayList<>();
        for (DwiScore dwiScore : dwiScores) {
            String userId = dwiScore.getUserNumber();
            BigDecimal score = dwiScore.getScore();
            if (score == null) {
                score = new BigDecimal(40);
            }
            double scoreDouble = score.doubleValue();
            List<StudyComplication> calculate = comCalculate(userId, scoreDouble);
            studyComplicationList.addAll(calculate);
        }
        // 按课程分组
        Map<String, List<StudyComplication>> collect = studyComplicationList.stream().collect(Collectors.groupingBy(StudyComplication::getCourseId));
        // 课程平均进度 - 按课程算平均值
        Map<String, Float> avgMap = new HashMap<>();
        collect.forEach((name, mapByNameList) -> {
            OptionalDouble averageOpt = mapByNameList.stream().mapToDouble(StudyComplication::getComplicationScore).average();
            avgMap.put(name, Float.parseFloat(averageOpt.getAsDouble() + ""));

        });
        studyComplicationList.stream().forEach(tt -> {
            String courseId = tt.getCourseId();
            if (avgMap.containsKey(courseId)) {
                tt.setAvgComplicationScore(String.valueOf(avgMap.get(courseId)));
            } else {
                tt.setAvgComplicationScore(String.valueOf(0.4F));
            }
        });
        studyComplicationList = studyComplicationList.stream().distinct().collect(Collectors.toList());
        studyComplicationMapper.batchInsert(studyComplicationList);
    }

    private List<StudyComplication> comCalculate(String userId, Double score) {
        LambdaQueryWrapper<StudyDuration> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StudyDuration::getStudentId, userId);
        List<StudyDuration> studyDurations = studyDurationMapper.selectList(lqw);
        if (score == null) {
            score = 40.0;
        }
        double totalScore = score;
        List<StudyComplication> studyComplicationList = new ArrayList<>();

        for (StudyDuration studyDuration : studyDurations) {
            String courseId = studyDuration.getCourseId();
            double rate = getComRate(studyDuration);
            double effScore = rate * totalScore;
            StudyComplication studyComplication = new StudyComplication(userId, courseId, (float) effScore / 100, "");
            studyComplicationList.add(studyComplication);
        }
        return studyComplicationList;
    }

    private double getComRate(StudyDuration studyDuration) {
        double videoWatchScore = Double.parseDouble(studyDuration.getVideoWatchScore());
        double fileWatchScore = Double.parseDouble(studyDuration.getFileWatchScore());
        double testTimeScore = Double.parseDouble(studyDuration.getTestTimeScore());
        double discussionScore = Double.parseDouble(studyDuration.getDiscussionScore());
        double examinationTimeScore = Double.parseDouble(studyDuration.getExaminationTimeScore());

        if (videoWatchScore == 0) {
            return 0.4;
        }

        double res = videoWatchScore + fileWatchScore / (videoWatchScore + fileWatchScore + testTimeScore + discussionScore + examinationTimeScore);
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
