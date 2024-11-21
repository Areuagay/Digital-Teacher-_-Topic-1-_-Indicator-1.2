package com.gcc.service.impl;

import com.gcc.mapper.StudentProgressMapper;
import com.gcc.pojo.StudentCourseScore;
import com.gcc.mapper.StudentCourseScoreMapper;
import com.gcc.pojo.StudentProgress;
import com.gcc.service.StudentCourseScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Service
public class StudentCourseScoreServiceImpl extends ServiceImpl<StudentCourseScoreMapper, StudentCourseScore> implements StudentCourseScoreService {
    @Resource
    private StudentCourseScoreMapper scs;

    @Override
    public List<StudentCourseScore> getSingleCourseAvgScore(String student_id, String course_id) {
        return scs.getSingleCourseAvgScore(student_id, course_id);
    }
}
