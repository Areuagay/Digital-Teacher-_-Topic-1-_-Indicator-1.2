package com.gcc.service;

import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyComplication;
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
public interface StudyComplicationService extends IService<StudyComplication> {
    public List<StudyComplication> getComplicationByUserId(String student_id, String course_id);
}
