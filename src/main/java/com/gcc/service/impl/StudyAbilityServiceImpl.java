package com.gcc.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyAbility;
import com.gcc.mapper.StudyAbilityMapper;
import com.gcc.service.StudyAbilityService;
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
 * @since 2023-01-03
 */
@Service
public class StudyAbilityServiceImpl extends ServiceImpl<StudyAbilityMapper, StudyAbility> implements StudyAbilityService {

    @Resource
    private StudyAbilityMapper sam;
    @Override
    public List<StudyAbility> getAbilityByUserId(String student_id, String course_id){
        return sam.getAbilityByUserId(student_id,course_id);
    }
}
