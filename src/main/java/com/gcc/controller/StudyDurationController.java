package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyDuration;
import com.gcc.service.StudyDurationService;
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
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/GetStudyDurationByUserId")
public class StudyDurationController {
    @Resource
    private StudyDurationService sds;

//    @GetMapping("/{student_id}/{course_id}")
//    public Result<List<StudyDuration>> getStudyDurationByUserId(@PathVariable String student_id, @PathVariable String course_id) {
//        if (sds.getStudyDurationByUserId(student_id, course_id).size() == 0) {
//            List<StudyDuration> s = new ArrayList<>();
//            StudyDuration ss = new StudyDuration(student_id, course_id, "0.1", "0.1", "0.1", "0.1", "0.1");
//            s.add(ss);
//            return Result.success(s);
//        } else {
////            return Result.success(sds.getStudyDurationByUserId(student_id, course_id));
//            List<StudyDuration> sds_ = sds.getStudyDurationByUserId(student_id, course_id);
//            StudyDuration studyDuration = sds_.get(0);
//            String video = studyDuration.getVideoWatchScore();
//            String file = studyDuration.getFileWatchScore();
//            String test = studyDuration.getTestTimeScore();
//            String discussion = studyDuration.getDiscussionScore();
//            String exam = studyDuration.getExaminationTimeScore();
//
//            DecimalFormat df = new DecimalFormat("0.00");
//            studyDuration.setVideoWatchScore(df.format(Float.parseFloat(video)));
//            studyDuration.setVideoWatchScore(df.format(Float.parseFloat(file)));
//            studyDuration.setVideoWatchScore(df.format(Float.parseFloat(test)));
//            studyDuration.setVideoWatchScore(df.format(Float.parseFloat(discussion)));
//            studyDuration.setVideoWatchScore(df.format(Float.parseFloat(exam)));
//
//            return Result.success(sds_);
//        }
//    }

    @GetMapping("/{student_id}/{course_id}")
    public Result<List<StudyDuration>> getStudyDurationByUserId(@PathVariable String student_id, @PathVariable String course_id) {
        List<StudyDuration> studyDurationByUserId = sds.getStudyDurationByUserId(student_id, course_id);
        if (studyDurationByUserId.isEmpty()) {
            List<StudyDuration> s = new ArrayList<>();
            StudyDuration ss = new StudyDuration(student_id, course_id, "0.1", "0.1", "0.1", "0.1", "0.1");
            s.add(ss);
            return Result.success(s);
        } else {
//            return Result.success(studyDurationByUserId);

            StudyDuration studyDuration = studyDurationByUserId.get(0);
            float video = Float.parseFloat(studyDuration.getVideoWatchScore());
            float file = Float.parseFloat(studyDuration.getFileWatchScore());
            float test = Float.parseFloat(studyDuration.getTestTimeScore());
            float discussion = Float.parseFloat(studyDuration.getDiscussionScore());
            float exam = Float.parseFloat(studyDuration.getExaminationTimeScore());
            float sum = video + file + test + discussion + exam;
            if (video == 0) {
                video = sum / 10;
            }
            if (file == 0) {
                file = sum / 10;
            }
            if (test == 0) {
                test = sum / 10;
            }
            if (discussion == 0) {
                discussion = sum / 10;
            }
            if (exam == 0) {
                exam = sum / 10;
            }
            studyDuration.setVideoWatchScore(String.format("%.2f", video / sum));
            studyDuration.setFileWatchScore(String.format("%.2f", file / sum));
            studyDuration.setTestTimeScore(String.format("%.2f", test / sum));
            studyDuration.setDiscussionScore(String.format("%.2f", discussion / sum));
            studyDuration.setExaminationTimeScore(String.format("%.2f", exam / sum));

            return Result.success(studyDurationByUserId);
        }
    }
}

