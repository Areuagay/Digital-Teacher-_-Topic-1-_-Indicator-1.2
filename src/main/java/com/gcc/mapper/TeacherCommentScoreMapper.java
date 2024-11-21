package com.gcc.mapper;

import com.gcc.pojo.StudentCourseCountScore;
import com.gcc.pojo.TeacherCommentScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface TeacherCommentScoreMapper extends BaseMapper<TeacherCommentScore> {
    List<TeacherCommentScore> getCommentById(@Param("student_id") String id);
}
