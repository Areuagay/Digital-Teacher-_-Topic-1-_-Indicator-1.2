package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudentCourseCountScore;
import com.gcc.service.StudentCourseCountScoreService;
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
@RequestMapping("/GetAvgScoreByUserId")
public class StudentCourseCountScoreController {
    @Resource
    private StudentCourseCountScoreService scss;

    @GetMapping("/{user_id}")
    public Result<List<StudentCourseCountScore>> queryById(@PathVariable String user_id) {
        if (scss.queryAvgScoreById(user_id).isEmpty()) {
            List<StudentCourseCountScore> s = new ArrayList<>();
            StudentCourseCountScore ss = new StudentCourseCountScore(user_id, "60");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(scss.queryAvgScoreById(user_id));
            List<StudentCourseCountScore> scss_ = scss.queryAvgScoreById(user_id);
            StudentCourseCountScore studentCourseCountScore = scss_.get(0);
            String score = studentCourseCountScore.getCountScore();

            DecimalFormat df = new DecimalFormat("0");
            studentCourseCountScore.setCountScore(df.format(Float.parseFloat(score)));

            return Result.success(scss_);
        }

    }
}

