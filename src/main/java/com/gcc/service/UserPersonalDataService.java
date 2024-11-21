package com.gcc.service;

import com.gcc.pojo.KnowledgeComplication;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.pojo.UserPersonalData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface UserPersonalDataService extends IService<UserPersonalData> {
    public List<UserPersonalData> getPersonInfoByUserId(String id);

    public List<StudentCourseRelationship> getStudentCourseByUserId(String id);

}
