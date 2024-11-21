package com.gcc.service;

import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyAbility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2023-01-03
 */
public interface StudyAbilityService extends IService<StudyAbility> {
    public List<StudyAbility> getAbilityByUserId(String student_id, String course_id);
}
