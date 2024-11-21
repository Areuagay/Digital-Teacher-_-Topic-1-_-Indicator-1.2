package com.gcc.controller;

import com.gcc.common.Result;
import com.gcc.pojo.StudyComplication;
import com.gcc.pojo.StudyEfficiency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GetEmotionByUserId")
public class studyEmotionController {
    @GetMapping("/{user_id}/{course_id}")
    public Result<List<String>> GetEmotion(){
        List<String> s = new ArrayList<>();
        return Result.success(s);
    }
}
