package com.gcc.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.gcc.pojo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.vo.StudyReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface StudentCourseCountScoreMapper extends BaseMapper<StudentCourseCountScore> {
    List<StudentCourseCountScore> queryAvgScoreById(@Param("student_id") String id);

    List<StudyComplication> queryStudyComplication();

    int syncStudyComplication(List<StudyComplication> list);

    List<StudentCourseCountScore> queryStudentCountScore();

    List<StudyReport> queryStudyReport();

    int syncProgress(List<StudyReport> list);

    int syncStudyEngagement(List<StudyReport> list);

    int syncStudyDuration(List<StudyReport> list);


    int syncEffect(List<StudyEffect> list);


    List<StudyReport> queryStudyEfficience();

    int syncStudyEfficience(List<StudyReport> list);

    int syncUserWordCloud1(List<DwiCourse> list);

    int syncStudentCourseRelationship(List<DwiCourse> list);


}
