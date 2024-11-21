package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudentCourseScore;
import com.gcc.service.StudentCourseScoreService;
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
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/GetSingleCourseAvgScore")
public class StudentCourseScoreController {
    @Resource
    private StudentCourseScoreService scss;

    @GetMapping("/{student_id}/{course_id}")
    public Result<List<StudentCourseScore>> getSingleCourseAvgScore(@PathVariable String student_id, @PathVariable String course_id) {
        if (scss.getSingleCourseAvgScore(student_id, course_id).isEmpty()) {
            List<StudentCourseScore> s = new ArrayList<>();
            StudentCourseScore ss = new StudentCourseScore("436188", course_id, "60", "60", student_id, "模拟考试", "2022-03-29");
            s.add(ss);
            return Result.success(s);
        } else
            return Result.success(scss.getSingleCourseAvgScore(student_id, course_id));
    }
}

