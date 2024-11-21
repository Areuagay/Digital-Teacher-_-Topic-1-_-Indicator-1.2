package com.gcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.UserWordcloud1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface UserWordcloud1Mapper extends BaseMapper<UserWordcloud1> {
    List<UserWordcloud1> getWordCloudByUserId(@Param("student_id") String student_id);

    void insertWordCloud(UserWordcloud1 userWordcloud1);
}
