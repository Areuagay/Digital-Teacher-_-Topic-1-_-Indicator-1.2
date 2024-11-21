package com.gcc.service.impl;

import com.gcc.mapper.StudentCourseScoreMapper;
import com.gcc.pojo.StudentCourseCountScore;
import com.gcc.mapper.StudentCourseCountScoreMapper;
import com.gcc.pojo.StudentCourseScore;
import com.gcc.service.StudentCourseCountScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Service
public class StudentCourseCountScoreServiceImpl extends ServiceImpl<StudentCourseCountScoreMapper, StudentCourseCountScore> implements StudentCourseCountScoreService {
    @Resource
    private StudentCourseCountScoreMapper sccsm;
    @Override
    public List<StudentCourseCountScore> queryAvgScoreById(String id){
        return sccsm.queryAvgScoreById(id);
    }
}
