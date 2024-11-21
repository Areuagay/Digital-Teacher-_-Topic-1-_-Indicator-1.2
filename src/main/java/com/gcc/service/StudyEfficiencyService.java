package com.gcc.service;

import com.gcc.pojo.StudyEffect;
import com.gcc.pojo.StudyEfficiency;
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
public interface StudyEfficiencyService extends IService<StudyEfficiency> {
    public List<StudyEfficiency> getEfficiencyByUserId(String student_id, String course_id);
}
