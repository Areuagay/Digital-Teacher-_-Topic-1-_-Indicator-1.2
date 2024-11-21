package com.gcc.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.StudentCourseScore;
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
public interface StudentCourseScoreMapper extends BaseMapper<StudentCourseScore> {
    List<StudentCourseScore> getSingleCourseAvgScore(@Param("student_id") String student_id, @Param("course_id") String course_id);

    @DS(value = "slave_1")
    List<StudentCourseScore> selectFromDwi();

    void batchInsert(List<StudentCourseScore> studentCourseScoreList);
}
