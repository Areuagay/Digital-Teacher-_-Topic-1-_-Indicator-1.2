package com.gcc.service;

import com.gcc.pojo.StudentVitality;
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
public interface StudentVitalityService extends IService<StudentVitality> {
public List<StudentVitality>getVitalityByUserId(String student_id,String course_id);
}
