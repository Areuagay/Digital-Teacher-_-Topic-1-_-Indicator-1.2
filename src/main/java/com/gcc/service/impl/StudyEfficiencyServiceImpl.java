package com.gcc.service.impl;

import com.gcc.mapper.StudyEffectMapper;
import com.gcc.pojo.StudyEffect;
import com.gcc.pojo.StudyEfficiency;
import com.gcc.mapper.StudyEfficiencyMapper;
import com.gcc.service.StudyEfficiencyService;
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
public class StudyEfficiencyServiceImpl extends ServiceImpl<StudyEfficiencyMapper, StudyEfficiency> implements StudyEfficiencyService {
    @Resource
    private StudyEfficiencyMapper sem;
    @Override
    public List<StudyEfficiency> getEfficiencyByUserId(String student_id, String course_id){
        return sem.getEfficiencyByUserId(student_id,course_id);
    }
}
