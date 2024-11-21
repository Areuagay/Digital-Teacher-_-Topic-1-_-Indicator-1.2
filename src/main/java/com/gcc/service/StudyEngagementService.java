package com.gcc.service;

import com.gcc.pojo.StudyEngagement;
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
public interface StudyEngagementService extends IService<StudyEngagement> {
public List<StudyEngagement> getEngagementByUserId(String student_id,String course_id);
}
