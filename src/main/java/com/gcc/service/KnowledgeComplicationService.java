package com.gcc.service;

import com.gcc.pojo.KnowledgeComplication;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Service
public interface KnowledgeComplicationService extends IService<KnowledgeComplication> {
    /**
     * 查询知识完成度维度
     *
     * @param student_id
     * @param course_id
     * @return
     */
    public List<KnowledgeComplication> queryById(String student_id, String course_id);
}
