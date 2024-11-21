package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.mapper.UserPersonalDataMapper;
import com.gcc.pojo.DwiUserVisit;
import com.gcc.pojo.StudyDuration;
import com.gcc.pojo.UserPersonalData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author yp
 * @date: 2023/10/23
 */
@SpringBootTest
@TypeExcludeFilters({ListenerExcludeFilter.class})
@Transactional
public class DataLoadTest {

    @Resource
    private DwiUserVisitMapper dwiUserVisitMapper;

    @Resource
    private StudyDurationMapper studyDurationMapper;

    private ExecutorService executorService = new ThreadPoolExecutor(40, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    int count = 14;

    @Resource
    private UserPersonalDataMapper userPersonalDataMapper;

    @Test
    public void testRoll() {
        UserPersonalData userPersonalData = new UserPersonalData();
        userPersonalData.setStudentId("");
        userPersonalData.setUserName("55555");
        userPersonalData.setUserGender("");
        userPersonalData.setAge("");
        userPersonalData.setNationality("");
        userPersonalData.setDegree("");
        userPersonalData.setPoliticalStatus("dwa");
        userPersonalData.setGraduateInstitutions("dwa");
        userPersonalData.setFormerMajor("");
        userPersonalData.setEnrollmnentData("");
        userPersonalData.setEducationBackground("");
        userPersonalData.setMajorAdmission("");
        userPersonalData.setStudentStatus("");
        userPersonalData.setPhoneNumber("");
        userPersonalData.setEMail("");
        userPersonalData.setInstitutionalFramework("");
        userPersonalDataMapper.insert(userPersonalData);
    }

    @Test
    public void testData() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(40);

        int batchSize = 3000;
        int size = count * 40 + 40;
        for (int i = count * 40; i < size; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    List<DwiUserVisit> dwiUserVisits = dwiUserVisitMapper.selectList(new LambdaQueryWrapper<DwiUserVisit>().last("limit " + finalI * batchSize + "," + batchSize));
                    loadingData(dwiUserVisits);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            //等待计数器归零
            count += 40;
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                studyDurationMapper.updateById(studyDuration1);
            }
        }
    }

}