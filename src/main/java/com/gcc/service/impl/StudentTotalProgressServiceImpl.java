package com.gcc.service.impl;

import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.pojo.StudentTotalProgress;
import com.gcc.mapper.StudentTotalProgressMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.service.StudentTotalProgressService;
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
public class StudentTotalProgressServiceImpl extends ServiceImpl<StudentTotalProgressMapper, StudentTotalProgress> implements StudentTotalProgressService {
    @Resource
    private StudentTotalProgressMapper stpm;
    @Override
    public List<StudentTotalProgress> getTotalProgressyByUserId(String student_id){
        return stpm.getTotalProgressyByUserId(student_id);
    }
}
