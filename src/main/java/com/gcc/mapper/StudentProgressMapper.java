package com.gcc.mapper;

import com.gcc.pojo.StudentProgress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.StudentVitality;
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
public interface StudentProgressMapper extends BaseMapper<StudentProgress> {
    List<StudentProgress> getProgressByUserId(@Param("student_id") String student_id, @Param("course_id") String course_id);
}
