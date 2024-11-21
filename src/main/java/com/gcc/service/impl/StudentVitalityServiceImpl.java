package com.gcc.service.impl;

import com.gcc.pojo.StudentVitality;
import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.service.StudentVitalityService;
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
public class StudentVitalityServiceImpl extends ServiceImpl<StudentVitalityMapper, StudentVitality> implements StudentVitalityService {

    @Resource
    private StudentVitalityMapper svm;
    @Override
    public List<StudentVitality> getVitalityByUserId(String student_id, String course_id){
        return svm.getVitalityByUserId(student_id,course_id);
    }
}
