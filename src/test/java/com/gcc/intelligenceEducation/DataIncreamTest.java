package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.pojo.DwiUserVisit;
import com.gcc.pojo.StudyDuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author yp
 * @date: 2023/10/25
 */
@SpringBootTest
@Transactional
@TypeExcludeFilters({ListenerExcludeFilter.class})
public class DataIncreamTest {
    @Resource
    private DwiUserVisitMapper dwiUserVisitMapper;
    @Resource
    private StudyDurationMapper studyDurationMapper;

    @Test
    void timeTest() {
        LocalDate now = LocalDate.now().minusDays(1);

        LambdaQueryWrapper<DwiUserVisit> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DwiUserVisit::getDay, now);
        List<DwiUserVisit> dwiUserVisits = dwiUserVisitMapper.selectList(lqw);
        loadingData(dwiUserVisits);
    }

    private void loadingData(List<DwiUserVisit> dwiUserVisits) {
        for (DwiUserVisit dwiUserVisit : dwiUserVisits) {
            double visitDuration = Double.parseDouble(dwiUserVisit.getVisitDuration());
            String activityType = dwiUserVisit.getActivityType();
            if (activityType == null) {
                continue;
            }

            String userNumber = dwiUserVisit.getUserNumber();
            String courseCode = dwiUserVisit.getCourseCode();

            StudyDuration studyDuration = new StudyDuration(userNumber, courseCode);

            LambdaQueryWrapper<StudyDuration> lqw = new LambdaQueryWrapper<>();
            lqw.eq(StudyDuration::getStudentId, userNumber);
            lqw.eq(StudyDuration::getCourseId, courseCode);
            StudyDuration studyDuration1 = studyDurationMapper.selectOne(lqw);
            if (studyDuration1 == null) {
                switch (activityType) {
                    case "page":
                        studyDuration.setFileWatchScore(visitDuration + "");
                        break;
                    case "online_video":
                        studyDuration.setVideoWatchScore(visitDuration + "");
                        break;
                    case "forum":
                        studyDuration.setDiscussionScore(visitDuration + "");
                        break;
                    case "exam":
                        studyDuration.setExaminationTimeScore(visitDuration + "");
                        break;
                    case "homework":
                        studyDuration.setTestTimeScore(visitDuration + "");
                        break;
                    default:
                        break;
                }
                try {
                    studyDurationMapper.insertStudentDuration(studyDuration);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                switch (activityType) {
                    case "page":
                        studyDuration1.setFileWatchScore((Double.parseDouble(studyDuration1.getFileWatchScore()) + visitDuration) + "");
                        break;
                    case "online_video":
                        studyDuration1.setVideoWatchScore((Double.parseDouble(studyDuration1.getVideoWatchScore()) + visitDuration) + "");
                        break;
                    case "forum":
                        studyDuration1.setDiscussionScore((Double.parseDouble(studyDuration1.getDiscussionScore()) + visitDuration) + "");
                        break;
                    case "exam":
                        studyDuration1.setExaminationTimeScore((Double.parseDouble(studyDuration1.getExaminationTimeScore()) + visitDuration) + "");
                        break;
                    case "homework":
                        studyDuration1.setTestTimeScore((Double.parseDouble(studyDuration1.getTestTimeScore()) + visitDuration) + "");
                        break;
                    default:
                        break;
                }
                try {
                    studyDurationMapper.updateById(studyDuration1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
