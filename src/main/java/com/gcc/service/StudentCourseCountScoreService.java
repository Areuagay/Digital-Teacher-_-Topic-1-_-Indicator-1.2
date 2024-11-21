package com.gcc.service;

import com.gcc.pojo.StudentCourseCountScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.pojo.StudentCourseScore;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface StudentCourseCountScoreService extends IService<StudentCourseCountScore> {
    public List<StudentCourseCountScore> queryAvgScoreById(String id);
}
