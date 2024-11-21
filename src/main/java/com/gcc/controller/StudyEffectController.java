package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.StudyEffect;
import com.gcc.pojo.vo.StudyEffectVO;
import com.gcc.service.StudyEffectService;
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
@RequestMapping("/GetEffectByUserId")
public class StudyEffectController {
    @Resource
    private StudyEffectService ses;

    @GetMapping("/{user_id}/{course_id}")
    public Result<List<?>> getEffectByUserId(@PathVariable String user_id, @PathVariable String course_id) {
        if (ses.getEffectByUserId(user_id, course_id).isEmpty()) {
            List<StudyEffectVO> s = new ArrayList<>();
            //StudyEffect ss = new StudyEffect(user_id, course_id, 0.35F, 0.4F, 0.35F);
            StudyEffectVO ss = new StudyEffectVO();
            ss.setPerformanceScore(0.35F);
            ss.setAvgPerformanceScore(0.4F);
            ss.setCourseId(course_id);
            ss.setScore("0.35");
            ss.setStudentId(user_id);
            System.out.println(ss);
            s.add(ss);

            //输出修改
            //Map<String, Objects> output = new HashMap<>();

            return Result.success(s);
        } else {
//            return Result.success(ses.getEffectByUserId(user_id,course_id));
            List<StudyEffect> ses_ = ses.getEffectByUserId(user_id, course_id);
            StudyEffect studyEffect = ses_.get(0);
            Float score = studyEffect.getPerformanceScore();
            Float avg = studyEffect.getAvgPerformanceScore();
            DecimalFormat df = new DecimalFormat("0.00");
            if (studyEffect.getPerformanceScore() == 0.0) {
                studyEffect.setPerformanceScore(Float.parseFloat("0.35"));
                studyEffect.setAvgPerformanceScore(Float.parseFloat(df.format(avg)));
            } else {
                score = score * 2 > 1 ? 1 : score * 2;
                avg = avg * 2 > 1 ? 1 : avg * 2;
                studyEffect.setPerformanceScore(Float.parseFloat(df.format(score)));
                studyEffect.setAvgPerformanceScore(Float.parseFloat(df.format(avg)));
            }
            StudyEffectVO vo = new StudyEffectVO(studyEffect);
            ArrayList<StudyEffectVO> vos = new ArrayList<>();
            vos.add(vo);
            return Result.success(vos);
        }
    }
}

