package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyTotalDuration;
import com.gcc.service.StudentCourseRelationshipService;
import com.gcc.service.StudyTotalDurationService;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/GetTotalDurationByUserId")
@Slf4j
public class StudyTotalDurationController {
    @Resource
    private StudyTotalDurationService stds;

    @Resource
    private StudentCourseRelationshipService service;

    @GetMapping("/{user_id}")
    public Result<List<StudyTotalDuration>> getTotalDurationByUserId(@PathVariable String user_id) {

//        if(stds.getTotalDurationByUserId(user_id).size()==0){
//            long beginTime = System.currentTimeMillis();
//
//
//            List<StudyTotalDuration>s=new ArrayList<>();
//            StudyTotalDuration ss = new StudyTotalDuration(user_id,"1","60");
//            s.add(ss);
//
//            long endTime = System.currentTimeMillis();
//            log.info("main cost{} ms",beginTime-endTime);
//            return Result.success(s);
//        }
//        else {
////            return Result.success(stds.getTotalDurationByUserId(user_id));
//            List<StudyTotalDuration> stds_ = stds.getTotalDurationByUserId(user_id);
//            StudyTotalDuration studyTotalDuration = stds_.get(0);
//            String hours = studyTotalDuration.getTotalHours();
//            String minutes = studyTotalDuration.getTotalMinutes();
//
//            if(Objects.equals(studyTotalDuration.getTotalHours(), "0")) {
//                studyTotalDuration.setTotalMinutes("60");
//                studyTotalDuration.setTotalHours("1");
//            } else if (studyTotalDuration.getTotalHours()!=null ){
//                DecimalFormat df = new DecimalFormat("0");
//                studyTotalDuration.setTotalHours(df.format(Float.parseFloat(hours)));
//                studyTotalDuration.setTotalMinutes(df.format(Float.parseFloat(minutes)));
//            } else {
//                studyTotalDuration.setTotalMinutes("60");
//                studyTotalDuration.setTotalHours("1");
//            }
//        log.info("main cost{} ms",beginTime-endTime);



        return Result.success(stds.getTotalDurationByUserId(user_id));

    }


}

