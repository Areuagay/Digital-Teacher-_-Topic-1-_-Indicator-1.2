package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyAbility;
import com.gcc.service.StudyAbilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2023-01-03
 */
@RestController
@RequestMapping("/GetAbilityByUserId")
public class StudyAbilityController {
    @Resource
    private StudyAbilityService sas;

    @GetMapping("/{student_id}/{course_id}")
    public Result<List<StudyAbility>> getAbilityByUserId(@PathVariable String student_id, @PathVariable String course_id) {
        if (sas.getAbilityByUserId(student_id, course_id).isEmpty()) {
            List<StudyAbility> s = new ArrayList<>();
            StudyAbility ss = new StudyAbility(student_id, course_id, "0.35", "0.4");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(sas.getAbilityByUserId(student_id, course_id));
            List<StudyAbility> sas_ = sas.getAbilityByUserId(student_id, course_id);
            StudyAbility studyAbility = sas_.get(0);
            double score = Double.parseDouble(studyAbility.getAbilityScore());
            double avg = Double.parseDouble(studyAbility.getAbilityScore());

            if (score < 0.1) {
                score = 0.1;
            }
            if (avg < 0.1) {
                avg = 0.1;
            }


//            DecimalFormat df = new DecimalFormat("0.00");
//            studyAbility.setAbilityScore(df.format(score));
//            studyAbility.setAvgAbilityScore(df.format(avg));

            studyAbility.setAbilityScore(String.format("%.2f", score));
            studyAbility.setAvgAbilityScore(String.format("%.2f", avg));

            return Result.success(sas_);
        }
    }
}

