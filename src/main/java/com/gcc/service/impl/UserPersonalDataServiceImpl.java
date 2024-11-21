package com.gcc.service.impl;

import com.gcc.mapper.KnowledgeComplicationMapper;
import com.gcc.pojo.KnowledgeComplication;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.pojo.UserPersonalData;
import com.gcc.mapper.UserPersonalDataMapper;
import com.gcc.service.UserPersonalDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Service
public class UserPersonalDataServiceImpl extends ServiceImpl<UserPersonalDataMapper, UserPersonalData> implements UserPersonalDataService {
    @Resource
    private UserPersonalDataMapper updm;
    @Override
    public List<UserPersonalData> getPersonInfoByUserId(String id) {
        return updm.getPersonInfoByUserId( id);
    }

    @Override
    public List<StudentCourseRelationship> getStudentCourseByUserId(String id) {
        return updm.getStudentCourseByUserId(id);
    }
}
