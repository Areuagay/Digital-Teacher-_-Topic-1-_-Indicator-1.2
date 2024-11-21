package com.gcc.service;

import com.gcc.pojo.StudyTotalEngagement;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface StudyTotalEngagementService extends IService<StudyTotalEngagement> {
public List<StudyTotalEngagement> getTotalEngagementByUserId(String id);
}
