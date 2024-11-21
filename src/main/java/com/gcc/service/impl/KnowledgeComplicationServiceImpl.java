package com.gcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcc.mapper.KnowledgeComplicationMapper;
import com.gcc.pojo.KnowledgeComplication;
import com.gcc.service.KnowledgeComplicationService;
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
public class KnowledgeComplicationServiceImpl extends ServiceImpl<KnowledgeComplicationMapper, KnowledgeComplication> implements KnowledgeComplicationService {
    @Resource
    private KnowledgeComplicationMapper kcm;

    @Override
    public List<KnowledgeComplication> queryById(String student_id, String course_id) {
//        kcm.
        return kcm.queryById(student_id, course_id);
    }
}
