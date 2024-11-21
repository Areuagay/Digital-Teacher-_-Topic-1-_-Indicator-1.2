package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyEfficiency;
import com.gcc.service.StudyEfficiencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/GetEfficiencyByUserId")
public class StudyEfficiencyController {

    @Resource
    private StudyEfficiencyService ses;

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<StudyEfficiency>> getEfficiencyByUserId(@PathVariable String user_id, @PathVariable String course_id) {
        if (ses.getEfficiencyByUserId(user_id, course_id).isEmpty()) {
            List<StudyEfficiency> s = new ArrayList<>();
            StudyEfficiency ss = new StudyEfficiency(user_id, course_id, "0.35", "0.4");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(ses.getEfficiencyByUserId(user_id, course_id));

            List<StudyEfficiency> ses_ = ses.getEfficiencyByUserId(user_id, course_id);
            StudyEfficiency studyEfficiency = ses_.get(0);
            String score = studyEfficiency.getEfficiencyScore();
            String avg = studyEfficiency.getAvgEfficiencyScore();
            DecimalFormat df = new DecimalFormat("0.00");
            if (Objects.equals(studyEfficiency.getEfficiencyScore(), "0.0")) {
                studyEfficiency.setEfficiencyScore("0.35");
                studyEfficiency.setAvgEfficiencyScore("0.40");
            } else {
                studyEfficiency.setEfficiencyScore(df.format(Float.parseFloat(score) * 0.1));
                studyEfficiency.setAvgEfficiencyScore(df.format(Float.parseFloat(avg) * 0.1));
            }

            return Result.success(ses_);
        }
    }
}

