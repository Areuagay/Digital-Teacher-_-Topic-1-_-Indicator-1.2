package com.gcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcc.mapper.DwiLearningActivitysMapper;
import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.pojo.StudyDuration;
import com.gcc.service.StudyDurationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gcc
 * @since 2023-02-02
 */
@Service
public class StudyDurationServiceImpl extends ServiceImpl<StudyDurationMapper, StudyDuration> implements StudyDurationService {
    @Resource
    private StudyDurationMapper sdm;

    @Resource
    private DwiLearningActivitysMapper dlam;

    @Resource
    private DwiUserVisitMapper duvm;

    @Override
    public List<StudyDuration> getStudyDurationByUserId(String student_id, String course_id) {
        return sdm.getStudyDurationByUserId(student_id, course_id);
    }


    // todo 使用线程池加快查询速度，建立联合索引

//    @Override
//    public List<StudyDuration> getStudyDurationByUserId(String student_id, String course_id) {
//        StopWatch stopWatch = new StopWatch("查询数据库计时器");
//        stopWatch.start();
//        LambdaQueryWrapper<DwiUserVisit> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(DwiUserVisit::getUserNumber, student_id);
//        lqw.eq(DwiUserVisit::getCourseCode, course_id);
//        List<DwiUserVisit> dwiUserVisits = duvm.selectList(lqw);
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//
//        if (dwiUserVisits == null) {
//            return new ArrayList<>();
//        }
//
//        double pageSource = 0;
//        double onlineVideoSource = 0;
//        double materialSource = 0;
//        double webLinkSource = 0;
//        double forumSource = 0;
//        double examSource = 0;
//        double homeWorkSource = 0;
//
//        for (DwiUserVisit d : dwiUserVisits) {
//            if (d.getActivityType() == null) {
//                continue;
//            }
//            String activityType = d.getActivityType();
//            double duration = Double.parseDouble(d.getVisitDuration());
//            switch (activityType) {
//                case "page":
//                    pageSource += duration;
//                    break;
//                case "online_video":
//                    onlineVideoSource += duration;
//                    break;
//                case "material":
//                    materialSource += duration;
//                    break;
//                case "web-link":
//                    webLinkSource += duration;
//                    break;
//                case "forum":
//                    forumSource += duration;
//                    break;
//                case "exam":
//                    examSource += duration;
//                    break;
//                case "homework":
//                    homeWorkSource += duration;
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        List<StudyDuration> studyDurations = new ArrayList<>();
//        StudyDuration studyDuration = new StudyDuration(student_id, course_id, onlineVideoSource + "", pageSource + "", homeWorkSource + "", forumSource + "", examSource + "");
//        // 更新数据库
//        LambdaQueryWrapper<StudyDuration> lqw1 = new LambdaQueryWrapper<StudyDuration>().eq(StudyDuration::getStudentId, student_id).eq(StudyDuration::getCourseId, course_id);
//        StudyDuration studyDuration1 = sdm.selectOne(lqw1);
//        if (studyDuration1 == null) {
//            sdm.insertStudentDuration(studyDuration);
//        } else {
//            sdm.update(studyDuration, lqw1);
//        }
//        studyDurations.add(studyDuration);
//        return studyDurations;
//    }
}
