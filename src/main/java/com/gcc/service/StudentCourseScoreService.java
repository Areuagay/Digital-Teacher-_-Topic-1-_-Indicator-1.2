package com.gcc.service;

import com.gcc.pojo.StudentCourseScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.pojo.StudentProgress;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface StudentCourseScoreService extends IService<StudentCourseScore> {
    public List<StudentCourseScore> getSingleCourseAvgScore(String student_id, String course_id);
}
