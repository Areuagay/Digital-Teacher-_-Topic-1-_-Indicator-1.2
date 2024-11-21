package com.gcc.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.*;
import com.gcc.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yp
 * @date: 2023/10/25
 */
@Component
@Slf4j
public class NewTasks {

    @Resource
    private DwiScoreMapper dwiScoreMapper;

    @Resource
    private StudyDurationMapper studyDurationMapper;

    @Resource
    private StudyEfficiencyMapper studyEfficiencyMapper;

    @Resource
    private StudyComplicationMapper studyComplicationMapper;

    @Resource
    private StudentVitalityMapper studentVitalityMapper;

    @Resource
    private StudentCourseScoreMapper studentCourseScoreMapper;

    @Scheduled(cron = "${scheduled.onceADay}")
    void syncStudentCourseScore() {
        List<StudentCourseScore> studentCourseScoreList = studentCourseScoreMapper.selectFromDwi();
        List<StudentCourseScore> collect = studentCourseScoreList.stream().filter(scs -> scs.getScore() != null && scs.getExamTimes() != null).collect(Collectors.toList());
        studentCourseScoreMapper.batchInsert(collect);
    }


    @Scheduled(cron = "${scheduled.onceADay}")
    void syncStudentVitality() {
//        user_prof_fake库study_duration数据表查询到的discussion_score数据字段除该表discussion_score数据字段的最大值，若小于0.1则设置为0.1。平均值计算与之前一样。
        List<StudyDuration> studyDurations = studyDurationMapper.selectList(new LambdaQueryWrapper<StudyDuration>());
        List<StudentVitality> toInsert = new ArrayList<>();
        for (StudyDuration studyDuration : studyDurations) {
            String studentId = studyDuration.getStudentId();
            String courseId = studyDuration.getCourseId();
            long maxDiscussion = studyDurationMapper.selectMaxDiscussion(courseId);
            if (maxDiscussion == 0) {
                maxDiscussion = 1000;
            }
            StudentVitality studentVitality = new StudentVitality(studentId, courseId);
            double discussionScore = Double.parseDouble(studyDuration.getDiscussionScore());
            double v = discussionScore / maxDiscussion;
            if (discussionScore == 0 || v < 0.1) {
                v = 0.1;
            }
            studentVitality.setVitalityCount((float) v);
            toInsert.add(studentVitality);
        }

        // 根据课程分组
        Map<String, List<StudentVitality>> collect = toInsert.stream().collect(Collectors.groupingBy(StudentVitality::getCourseId));
        // 求平均值
        Map<String, Float> avgMap = new HashMap<>();
        collect.forEach((name, mapByNameList) -> {
            OptionalDouble averageOpt = mapByNameList.stream().mapToDouble(StudentVitality::getVitalityCount).average();
            avgMap.put(name, Float.parseFloat(averageOpt.getAsDouble() + ""));

        });
        toInsert.stream().forEach(tt -> {
            String courseId = tt.getCourseId();
            if (avgMap.containsKey(courseId)) {
                tt.setAvgVitalityCount(avgMap.get(courseId));
            } else {
                tt.setAvgVitalityCount(0.1F);
            }
        });
        // 使用stream流过滤student_vitality中userId和courseId重复的
        toInsert = toInsert.stream().distinct().collect(Collectors.toList());
        studentVitalityMapper.batchInsert(toInsert);
    }


    /**
     * 学习效率的定时任务
     */
    @Scheduled(cron = "${scheduled.onceADay}")
    void syncStudyEfficiency() {
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

    /**
     * study_complication的定时任务
     */
    @Scheduled(cron = "${scheduled.onceADay}")
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
