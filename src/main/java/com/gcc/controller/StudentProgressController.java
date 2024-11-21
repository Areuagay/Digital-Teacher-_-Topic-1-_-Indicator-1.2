package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudentProgress;
import com.gcc.service.StudentProgressService;
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
@RequestMapping("/GetProgressByUserId")
public class StudentProgressController {
    @Resource
    private StudentProgressService sps;

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<StudentProgress>> getProgressByUserId(@PathVariable String user_id, @PathVariable String course_id) {
        if (sps.getProgressByUserId(user_id, course_id).isEmpty()) {
            List<StudentProgress> s = new ArrayList<>();
            StudentProgress ss = new StudentProgress(user_id, course_id, "0.35", "0.4");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(sps.getProgressByUserId(user_id, course_id));
            List<StudentProgress> sps_ = sps.getProgressByUserId(user_id, course_id);
            StudentProgress studentProgress = sps_.get(0);
            String score = studentProgress.getStudyProgress();
            String avg = studentProgress.getAvgProgress();

            DecimalFormat df = new DecimalFormat("0.00");
            studentProgress.setStudyProgress(df.format(Float.parseFloat(score)));
            studentProgress.setAvgProgress(df.format(Float.parseFloat(avg)));


            return Result.success(sps_);
        }

    }
}

