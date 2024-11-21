package com.gcc.service.impl;

import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.pojo.StudentProgress;
import com.gcc.mapper.StudentProgressMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.service.StudentProgressService;
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
public class StudentProgressServiceImpl extends ServiceImpl<StudentProgressMapper, StudentProgress> implements StudentProgressService {
    @Resource
    private StudentProgressMapper spm;
    @Override
    public List<StudentProgress> getProgressByUserId(String student_id, String course_id){
        return spm.getProgressByUserId(student_id,course_id);
    }
}
