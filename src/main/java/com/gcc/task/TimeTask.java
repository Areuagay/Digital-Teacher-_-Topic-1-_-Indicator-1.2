package com.gcc.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.pojo.DwiUserVisit;
import com.gcc.pojo.StudyDuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yp
 * @date: 2023/10/23
 */
@Component
@Slf4j
public class TimeTask {
    @Resource
    private DwiUserVisitMapper dwiUserVisitMapper;

    @Resource
    private StudyDurationMapper studyDurationMapper;

    private final ExecutorService executorService = new ThreadPoolExecutor(40, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    private int count = 0;
    private final CountDownLatch countDownLatch = new CountDownLatch(40);

    /**
     * 单科时长数据库存量同步
     */
//    @Scheduled(cron = "${scheduled.onceADay}")
    @Scheduled(cron = "0 0/4 3,7 * * ?")
    public void loadData() {
        int batchSize = 1000;
        int size = count * 40 + 40;
        for (int i = count * 40; i < size; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    List<DwiUserVisit> dwiUserVisits = dwiUserVisitMapper.selectList(new LambdaQueryWrapper<DwiUserVisit>().last("WHERE STR_TO_DATE( `Day`, '%Y-%m-%d' ) < STR_TO_DATE( '2023-10-24', '%Y-%m-%d' )  limit " + finalI * batchSize + "," + batchSize));
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
            log.info("count的值为：" + count);
            count += 1;
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单科时长数据库增量同步
     */
    @Scheduled(cron = "${scheduled.onceADay}")
    public void loadDataIncrement() {
        log.info("开始增量同步dwi_user_visit表数据");
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
