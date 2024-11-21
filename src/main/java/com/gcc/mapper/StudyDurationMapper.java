package com.gcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.StudyDuration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2023-02-02
 */
public interface StudyDurationMapper extends BaseMapper<StudyDuration> {
    List<StudyDuration> getStudyDurationByUserId(@Param("student_id") String student_id, @Param("course_id") String course_id);

    void insertStudentDuration(StudyDuration studyDuration);

    long selectMaxDiscussion(String courseId);
}
