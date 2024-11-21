package com.gcc.service.impl;

import com.gcc.mapper.TeacherCommentScoreMapper;
import com.gcc.pojo.StudyTotalEngagement;
import com.gcc.mapper.StudyTotalEngagementMapper;
import com.gcc.pojo.TeacherCommentScore;
import com.gcc.service.StudyTotalEngagementService;
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
public class StudyTotalEngagementServiceImpl extends ServiceImpl<StudyTotalEngagementMapper, StudyTotalEngagement> implements StudyTotalEngagementService {
    @Resource
    private StudyTotalEngagementMapper stem;
    @Override
    public List<StudyTotalEngagement> getTotalEngagementByUserId(String id){
        return stem.getTotalEngagementByUserId(id);
    }
}
