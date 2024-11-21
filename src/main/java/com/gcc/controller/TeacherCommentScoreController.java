package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.TeacherCommentScore;
import com.gcc.service.TeacherCommentScoreService;
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
@RequestMapping("/GetCommentByUserId")
public class TeacherCommentScoreController {
    @Resource
    private TeacherCommentScoreService tcss;

    @GetMapping("/{user_id}")
    public Result<List<TeacherCommentScore>> getCommentById(@PathVariable String user_id) {
        if (tcss.getCommentById(user_id).isEmpty()) {
            List<TeacherCommentScore> s = new ArrayList<>();
            TeacherCommentScore ss = new TeacherCommentScore(user_id, "很不错", "50");
            s.add(ss);
            return Result.success(s);
        } else {
            return Result.success(tcss.getCommentById(user_id));
        }
    }
}

