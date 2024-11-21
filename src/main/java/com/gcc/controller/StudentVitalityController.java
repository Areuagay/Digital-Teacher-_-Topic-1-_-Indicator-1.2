package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudentVitality;
import com.gcc.service.StudentVitalityService;
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
@RequestMapping("/GetVitalityByUserId")
public class StudentVitalityController {
    @Resource
    private StudentVitalityService svs;

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<StudentVitality>> getVitalityByUserId(@PathVariable String user_id, @PathVariable String course_id) {
        if (svs.getVitalityByUserId(user_id, course_id).isEmpty()) {
            List<StudentVitality> s = new ArrayList<>();
            StudentVitality ss = new StudentVitality(user_id, course_id, 0.35F, 0.4F);
            s.add(ss);
            return Result.success(s);
        } else {
//        return Result.success(svs.getVitalityByUserId(user_id,course_id));
            List<StudentVitality> svs_ = svs.getVitalityByUserId(user_id, course_id);
            StudentVitality studentVitality = svs_.get(0);
            Float score = studentVitality.getVitalityCount();
            Float avg = studentVitality.getAvgVitalityCount();

            DecimalFormat df = new DecimalFormat("0.00");
            studentVitality.setVitalityCount(Float.parseFloat(df.format(score)));
            studentVitality.setAvgVitalityCount(Float.parseFloat(df.format(avg)));


            return Result.success(svs_);
        }
    }
}

