package com.gcc.controller;

import com.gcc.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GetCharacterByUserId")
public class studyCharacterController {
    @GetMapping("/{user_id}/{course_id}")
    public Result<List<String>> GetCharacterById(){
        List<String> s = new ArrayList<>();
        return Result.success(s);
    }
}
