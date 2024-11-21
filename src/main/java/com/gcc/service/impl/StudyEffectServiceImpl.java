package com.gcc.service.impl;

import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyEffect;
import com.gcc.mapper.StudyEffectMapper;
import com.gcc.service.StudyEffectService;
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
public class StudyEffectServiceImpl extends ServiceImpl<StudyEffectMapper, StudyEffect> implements StudyEffectService {
    @Resource
    private StudyEffectMapper sem;
    @Override
    public List<StudyEffect> getEffectByUserId(String student_id, String course_id){
        return sem.getEffectByUserId(student_id,course_id);
    }
}
