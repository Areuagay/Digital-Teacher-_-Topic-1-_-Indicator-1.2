package com.gcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.pojo.UserPersonalData;
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
public interface UserPersonalDataMapper extends BaseMapper<UserPersonalData> {
    List<UserPersonalData> getPersonInfoByUserId(@Param("student_id") String id);


    List<StudentCourseRelationship> getStudentCourseByUserId(@Param("student_id") String id);
}
