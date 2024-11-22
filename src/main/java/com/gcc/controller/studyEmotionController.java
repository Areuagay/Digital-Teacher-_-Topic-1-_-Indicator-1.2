package com.gcc.controller;

import com.gcc.common.Result;
import com.gcc.pojo.StudyComplication;
import com.gcc.pojo.StudyEfficiency;
import com.gcc.pojo.studyEmotion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GetEmotionByUserId")
public class studyEmotionController {

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<studyEmotion>> GetEmotion(@PathVariable String user_id, @PathVariable String course_id){
        List<studyEmotion> s = new ArrayList<>();
        studyEmotion studyEmotion = new studyEmotion(user_id,course_id,"0.43","0.646");
        s.add(studyEmotion);
        return Result.success(s);
    }

}
