package com.gcc.controller;

import com.gcc.common.Result;
import com.gcc.pojo.studyCharacterScore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GetCharacterByUserId")
public class studyCharacterController {

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<studyCharacterScore>> GetCharacterById(@PathVariable String user_id, @PathVariable String course_id){
        List<studyCharacterScore> s = new ArrayList<>();
        studyCharacterScore s1 = new studyCharacterScore(user_id,course_id,"0.62","0.644");
        s.add(s1);
        return Result.success(s);
    }

}
