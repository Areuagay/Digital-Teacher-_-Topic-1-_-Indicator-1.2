package com.gcc.service;

import com.gcc.pojo.KnowledgeComplication;
import com.gcc.pojo.UserWordcloud1;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcc.pojo.vo.UserWordcloudVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface UserWordcloud1Service extends IService<UserWordcloud1> {
    public List<UserWordcloud1> getWordCloudByUserId(String student_id);
}
