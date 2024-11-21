package com.gcc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.pojo.StudyDuration;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gcc
 * @since 2023-02-02
 */
public interface StudyDurationService extends IService<StudyDuration> {
    public List<StudyDuration> getStudyDurationByUserId(String student_id, String course_id);
}
