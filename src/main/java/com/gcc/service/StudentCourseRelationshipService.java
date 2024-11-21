package com.gcc.service;

import com.gcc.pojo.StudentCourseRelationship;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2023-10-25
 */
public interface StudentCourseRelationshipService extends IService<StudentCourseRelationship> {
    List<String> getCourseIdByStudentId(String id);
}
