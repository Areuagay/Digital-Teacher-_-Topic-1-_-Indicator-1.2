package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyDuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yp
 * @date: 2023/11/2
 */
@TypeExcludeFilters({ListenerExcludeFilter.class})
@SpringBootTest
public class StudentVitalityTest {

    @Resource
    private StudyDurationMapper studyDurationMapper;


    @Resource
    private StudentVitalityMapper studentVitalityMapper;


    @Test
    void syncStudentVitality() {
        List<StudyDuration> studyDurations = studyDurationMapper.selectList(new LambdaQueryWrapper<StudyDuration>().last("limit 10000"));
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
}
