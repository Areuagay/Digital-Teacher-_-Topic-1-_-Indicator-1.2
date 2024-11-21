package com.gcc.service.impl;

import com.gcc.mapper.StudentVitalityMapper;
import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyEngagement;
import com.gcc.mapper.StudyEngagementMapper;
import com.gcc.service.StudyEngagementService;
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
public class StudyEngagementServiceImpl extends ServiceImpl<StudyEngagementMapper, StudyEngagement> implements StudyEngagementService {
    @Resource
    private StudyEngagementMapper sem;
    @Override
    public List<StudyEngagement> getEngagementByUserId(String student_id, String course_id){
        return sem.getEngagementByUserId(student_id,course_id);
    }
}
