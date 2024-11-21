package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyComplication;
import com.gcc.service.StudyComplicationService;
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
@RequestMapping("/GetComplicationByUserId")
public class StudyComplicationController {
    @Resource
    private StudyComplicationService scs;

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<StudyComplication>> getComplicationByUserId(@PathVariable String user_id, @PathVariable String course_id) {
        if (scs.getComplicationByUserId(user_id, course_id).isEmpty()) {
            List<StudyComplication> s = new ArrayList<>();
            StudyComplication ss = new StudyComplication(user_id, course_id, 0.35F, "0.4", "0.35");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(scs.getComplicationByUserId(user_id, course_id));
            List<StudyComplication> scs_ = scs.getComplicationByUserId(user_id, course_id);
            StudyComplication studyComplication = scs_.get(0);
            Float score = studyComplication.getComplicationScore();
            String avg = studyComplication.getAvgComplicationScore();
            if (studyComplication.getComplicationScore() == 0.0) {
                studyComplication.setComplicationScore(Float.parseFloat("0.35"));
                studyComplication.setAvgComplicationScore("0.40");
            } else {
                DecimalFormat df = new DecimalFormat("0.00");
                studyComplication.setComplicationScore(Float.parseFloat(df.format(score)));
                studyComplication.setAvgComplicationScore(df.format(Float.parseFloat(avg)));
            }

            return Result.success(scs_);
        }
    }
}

