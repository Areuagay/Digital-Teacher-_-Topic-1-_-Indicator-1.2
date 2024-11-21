package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudentTotalProgress;
import com.gcc.service.StudentTotalProgressService;
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
@RequestMapping("/GetTotalProgressyByUserId")
public class StudentTotalProgressController {

    @Resource
    private StudentTotalProgressService stps;

    @GetMapping("/{user_id}")
    public Result<List<StudentTotalProgress>> getTotalProgressyByUserId(@PathVariable String user_id) {
        if (stps.getTotalProgressyByUserId(user_id).isEmpty()) {
            List<StudentTotalProgress> s = new ArrayList<>();
            StudentTotalProgress ss = new StudentTotalProgress(user_id, "0.2");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(stps.getTotalProgressyByUserId(user_id));
            List<StudentTotalProgress> stps_ = stps.getTotalProgressyByUserId(user_id);
            StudentTotalProgress studentTotalProgress = stps_.get(0);
            String score = studentTotalProgress.getStudyTotalProgress();

            DecimalFormat df = new DecimalFormat("0.00");
            studentTotalProgress.setStudyTotalProgress(df.format(Float.parseFloat(score)));

            return Result.success(stps_);
        }
    }
}

