package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyEngagement;
import com.gcc.service.StudyEngagementService;
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
@RequestMapping("/GetEngagementByUserId")
public class StudyEngagementController {

    @Resource
    private StudyEngagementService ses;
    @GetMapping("/{user_id}/{course_id}")
    public Result<List<StudyEngagement>> getEngagementByUserId(@PathVariable String user_id, @PathVariable String course_id) {
        if (ses.getEngagementByUserId(user_id, course_id).isEmpty()) {
            List<StudyEngagement> s = new ArrayList<>();
            StudyEngagement ss = new StudyEngagement(user_id, course_id, "0.35", "0.4");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(ses.getEngagementByUserId(user_id, course_id));
            List<StudyEngagement> ses_ = ses.getEngagementByUserId(user_id, course_id);
            StudyEngagement studyEngagement = ses_.get(0);
            String score = studyEngagement.getEngagementScore();
            String avg = studyEngagement.getAvgEngagementScore();
            if (Objects.equals(studyEngagement.getEngagementScore(), "0.0")) {
                studyEngagement.setEngagementScore("0.35");
                studyEngagement.setAvgEngagementScore("0.40");
            } else {
                DecimalFormat df = new DecimalFormat("0.00");
                studyEngagement.setEngagementScore(df.format(Float.parseFloat(score)));
                studyEngagement.setAvgEngagementScore(df.format(Float.parseFloat(avg)));
            }

            return Result.success(ses_);
        }

    }
}

