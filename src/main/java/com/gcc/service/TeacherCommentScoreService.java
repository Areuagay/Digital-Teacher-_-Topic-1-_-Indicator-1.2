package com.gcc.service;

import com.gcc.pojo.TeacherCommentScore;
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
public interface TeacherCommentScoreService extends IService<TeacherCommentScore> {
public List<TeacherCommentScore> getCommentById(String id);
}
