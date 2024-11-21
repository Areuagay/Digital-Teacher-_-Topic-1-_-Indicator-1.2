package com.gcc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.mapper.StudentCourseRelationshipMapper;
import com.gcc.service.StudentCourseRelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gcc
 * @since 2023-10-25
 */
@Service
public class StudentCourseRelationshipServiceImpl extends ServiceImpl<StudentCourseRelationshipMapper, StudentCourseRelationship> implements StudentCourseRelationshipService {
    @Resource
    private StudentCourseRelationshipMapper studentCourseRelationshipMapper;

    @Override
    public List<String> getCourseIdByStudentId(String id) {
        LambdaQueryWrapper<StudentCourseRelationship> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseRelationship::getStudentId,id);
        List<StudentCourseRelationship> strings = studentCourseRelationshipMapper.selectList(wrapper);

        ArrayList<String> list = new ArrayList<>();
        for (StudentCourseRelationship s:strings
             ) {
            list.add(s.getCourseId());
        }


        return list;
    }
}
