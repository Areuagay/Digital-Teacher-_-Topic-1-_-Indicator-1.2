package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.KnowledgeComplication;
import com.gcc.service.KnowledgeComplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/GetKnowledgeComplicationByUserId")
public class KnowledgeComplicationController {
    @Resource
    private KnowledgeComplicationService kcm;

    @GetMapping("/{student_id}/{course_id}")
    public Result<List<KnowledgeComplication>> queryById(@PathVariable String student_id, @PathVariable String course_id) {
        if (kcm.queryById(student_id, course_id).isEmpty()) {
            List<KnowledgeComplication> k = new ArrayList<>();
            KnowledgeComplication kk = new KnowledgeComplication(student_id, course_id, "0.35", "0.4", "0.35", 0.35F);
            k.add(kk);
            return Result.success(k);
        } else {
//            return Result.success(kcm.queryById(student_id, course_id));
            List<KnowledgeComplication> kcm_ = kcm.queryById(student_id, course_id);
            KnowledgeComplication knowledgeComplication = kcm_.get(0);
            String knowledge = knowledgeComplication.getKnowledgeScore();
            String avg = knowledgeComplication.getAvgKnowledgeScore();

            DecimalFormat df = new DecimalFormat("0.00");
            knowledgeComplication.setKnowledgeScore(df.format(Float.parseFloat(knowledge)));
            knowledgeComplication.setAvgKnowledgeScore(df.format(Float.parseFloat(avg)));


            return Result.success(kcm_);
        }
    }
}

