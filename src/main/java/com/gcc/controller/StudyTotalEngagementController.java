package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyTotalEngagement;
import com.gcc.service.StudyTotalEngagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/GetTotalEngagementByUserId")
public class StudyTotalEngagementController {
    @Resource
    private StudyTotalEngagementService stec;

    @GetMapping("/{user_id}")
    public Result<List<StudyTotalEngagement>> getTotalEngagementByUserId(@PathVariable String user_id) {
        if (stec.getTotalEngagementByUserId(user_id).isEmpty()) {
            List<StudyTotalEngagement> s = new ArrayList<>();
            StudyTotalEngagement ss = new StudyTotalEngagement(user_id, "20");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(stec.getTotalEngagementByUserId(user_id));
            List<StudyTotalEngagement> stec_ = stec.getTotalEngagementByUserId(user_id);
            StudyTotalEngagement studyTotalEngagement = stec_.get(0);
            String score = studyTotalEngagement.getTotalEngagementScore();
            if (Objects.equals(studyTotalEngagement.getTotalEngagementScore(), "0")) {
                studyTotalEngagement.setTotalEngagementScore("30");
            } else {
                DecimalFormat df = new DecimalFormat("0");
                studyTotalEngagement.setTotalEngagementScore(df.format(Float.parseFloat(score) * 100));
            }

            return Result.success(stec_);
        }
    }
}

