package com.gcc.service;

import com.gcc.pojo.StudyTotalDuration;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2023-02-06
 */
public interface StudyTotalDurationService extends IService<StudyTotalDuration> {
    public List<StudyTotalDuration> getTotalDurationByUserId(String user_id);
    public StudyTotalDuration getToTalDuration(String student_id);
}
