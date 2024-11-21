package com.gcc.mapper;

import com.gcc.pojo.DwiPlaybackRecordVideoResources;
import com.gcc.pojo.KnowledgeComplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Mapper
public interface KnowledgeComplicationMapper extends BaseMapper<KnowledgeComplication> {
    List<KnowledgeComplication> queryById(@Param("student_id") String student_id, @Param("course_id") String course_id);

    public int batchInsert(List<KnowledgeComplication> list);
}
