package com.gcc.service.impl;

import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyComplication;
import com.gcc.mapper.StudyComplicationMapper;
import com.gcc.service.StudyComplicationService;
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
public class StudyComplicationServiceImpl extends ServiceImpl<StudyComplicationMapper, StudyComplication> implements StudyComplicationService {
    @Resource
    private StudyComplicationMapper scm;
    @Override
    public List<StudyComplication> getComplicationByUserId(String student_id, String course_id){
        return scm.getComplicationByUserId(student_id,course_id);
    }
}