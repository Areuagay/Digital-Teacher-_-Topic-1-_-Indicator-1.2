package com.gcc.service.impl;

import com.gcc.pojo.TeacherCommentScore;
import com.gcc.mapper.TeacherCommentScoreMapper;
import com.gcc.service.TeacherCommentScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TeacherCommentScoreServiceImpl extends ServiceImpl<TeacherCommentScoreMapper, TeacherCommentScore> implements TeacherCommentScoreService {
@Resource
private TeacherCommentScoreMapper tcsm;
@Override
public List<TeacherCommentScore> getCommentById(String id){
    return tcsm.getCommentById(id);
}
}
