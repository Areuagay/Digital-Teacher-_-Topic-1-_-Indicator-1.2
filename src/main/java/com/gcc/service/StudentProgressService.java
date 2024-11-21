package com.gcc.service;

import com.gcc.pojo.StudentProgress;
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
public interface StudentProgressService extends IService<StudentProgress> {
public List<StudentProgress> getProgressByUserId(String student_id,String course_id);
}
