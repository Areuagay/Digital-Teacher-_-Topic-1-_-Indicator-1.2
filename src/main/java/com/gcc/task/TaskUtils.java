package com.gcc.task;

import com.alibaba.fastjson.JSON;
import com.gcc.common.BaseConvertUtil;
import com.gcc.mapper.*;
import com.gcc.pojo.*;
import com.gcc.pojo.vo.StudyReport;
import com.gcc.pojo.vo.VitalityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 每天任务执行数据
 */
@Component
@Slf4j
public class TaskUtils {


    @Autowired
    DwiLearningActivitysMapper dwiLearningActivitysMapper;


    @Autowired
    DwiPlaybackRecordVideoResourcesMapper playbackRecordVideoResourcesMapper;

    @Autowired
    DwiUserVisitMapper userVisitMapper;

    @Autowired
    DwiScoreMapper dwiScoreMapper;

    @Autowired
    StudyAbilityMapper studyAbilityMapper;

    @Autowired
    KnowledgeComplicationMapper knowledgeComplicationMapper;

    @Autowired
    StudentCourseCountScoreMapper studentCourseCountScoreMapper;

    @Autowired
    StudentVitalityMapper studentVitalityMapper;


    /**
     * step 1
     * 学生单科成绩
     * 输入：
     * 表名：dwi_exam_submission 表名：dwi_exam_subject_library
     * 输出：
     * 表名：student_course_score
     * 字段：exam_id：考试id，examniee_id：学生id，score：分数，exam_name：考试名称，exam_time：考试时间，avgscore：班平均分
     */
//    @Scheduled(cron = "${scheduled.syncStudentCourseScore}")
//    public void syncStudentCourseScore() {
//        log.info("开始统计用户学生单科成绩~");
//        List<StudentCourseScore> list = this.queryStudentCourseScore();
//        batchStudentCourseScore(list);
//        log.info("完成统计用户学生单科成绩~");
//    }

    /**
     * step 2
     * 学生总成绩
     * 输入：
     * （从单科学生成绩表提取）表名：student_course_score 字段：examinee_id：学生id，course_id：课程id，score：单科分
     * 输出：
     * 表名：student_course_count_score  字段：examinee_id：学生id，total_score：总体平均分
     * 提取本用户id所有单科成绩维度来算平均值
     */
    @Scheduled(cron = "${scheduled.syncHasQzTask}")
    public void sync_student_course_count_score() {
        log.info("开始统计student_course_count_score~");
        playbackRecordVideoResourcesMapper.sync_student_course_count_score();
        log.info("完成统计student_course_count_score~");
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("${http.teacher-url}")
    private String teacherUrl;

    @Value("${http.cloud-url}")
    private String cloudUrl;

    @Value("${scheduled.switchs}")
    private boolean switchs;

    /**
     * teacher_comment_score
     * 输入：
     * 字段：student_id：学生id，instructor_comment：教师评价
     * 输出：
     * 字段：student_id：学生id，instructor_comment：教师评价，comment_score：评价得分
     * todo 无前置任务
     */
    @Scheduled(cron = "${scheduled.syncNoHasQzTask}")
    public void syncTeacherCommentScore() {
        log.info("开始统计TeacherCommentScore~");
        String body = "";
        if (switchs) {
            ResponseEntity<String> forEntity = restTemplate.getForEntity(teacherUrl, String.class);
            body = forEntity.getBody();
            System.out.println(body);
        } else {
            body = "{\"2244101208173\": \"50\", \"2244105303236\": \"50\"}";
        }
        //todo 解析python接口入库
        List<TeacherCommentScore> list = new ArrayList<>();
        Map maps = (Map) JSON.parse(body);
        for (Object map : maps.entrySet()) {
            System.out.println(((Map.Entry) map).getKey() + "     " + ((Map.Entry) map).getValue());
            TeacherCommentScore score = new TeacherCommentScore();
            score.setStudentId(((Map.Entry) map).getKey().toString());
            score.setCommentScore(((Map.Entry) map).getValue().toString());
            list.add(score);
        }
        playbackRecordVideoResourcesMapper.syncTeacherCommentScore(list);
        log.info("完成统计TeacherCommentScore~");
    }

    /**
     * student_vitality
     * "表名：dwi_learning_activitys
     * 表名：dwi_reply"
     * 按学生id统计学习行为条数*0.5+讨论帖子回复条数*0.5，
     * 取值最大学生id为1，其余学生依次递减
     */
//    @Scheduled(cron = "${scheduled.syncNoHasQzTask}")
    public void sync_student_vitality() {
        log.info("开始统计student_vitality~");
        List<VitalityVO> replyList = dwiScoreMapper.queryReplyCount();
        List<VitalityVO> learningCount = playbackRecordVideoResourcesMapper.queryLearningCount();
        System.out.println(replyList.size());
        System.out.println(learningCount.size());

        if (replyList.size() > learningCount.size()) {
            Map<String, Float> map = learningCount.stream().collect(Collectors.toMap(VitalityVO::getStudentId, VitalityVO::getCnt, (key1, key2) -> key2));
            replyList.stream().forEach(t -> {
                Float stuVitalityCnt = t.getCnt();
                if (map.containsKey(t.getStudentId())) {
                    stuVitalityCnt = stuVitalityCnt + map.get(t.getStudentId());
                    t.setVitalityCount(stuVitalityCnt);
                } else {
                    t.setVitalityCount(stuVitalityCnt);
                }
            });
            //列表排序
            //按学生id统计学习行为条数*0.5+讨论帖子回复条数*0.5，取值最大学生id为1，其余学生依次递减
            List<VitalityVO> collect = replyList.stream().sorted(Comparator.comparing(VitalityVO::getVitalityCount).reversed()).collect(Collectors.toList());
            final Float[] step = {1F};
            collect.stream().forEach(t -> {
                step[0] = step[0] - 0.0001F;
                t.setVitalityCount(step[0]);
            });
            Double avg = replyList.stream().collect(Collectors.averagingDouble(VitalityVO::getVitalityCount));
            replyList.stream().forEach(t -> {
                t.setCourseId("1");
                t.setAvgVitalityCount(avg.floatValue());
            });

            studentVitalityMapper.syncStudentVitality(BaseConvertUtil.convertList(replyList, StudentVitality.class));
        } else {
            log.info("********");
            Map<String, Float> map = replyList.stream().collect(Collectors.toMap(VitalityVO::getStudentId, VitalityVO::getCnt, (key1, key2) -> key2));
            learningCount.stream().forEach(t -> {
//                log.info(t+"");
                Float stuVitalityCnt = t.getCnt();
                if (map.containsKey(t.getStudentId())) {
                    stuVitalityCnt = stuVitalityCnt + map.get(t.getStudentId());
                    t.setVitalityCount(stuVitalityCnt);
                } else {
                    t.setVitalityCount(stuVitalityCnt);
                }
            });

            //列表排序
            //按学生id统计学习行为条数*0.5+讨论帖子回复条数*0.5，取值最大学生id为1，其余学生依次递减
            List<VitalityVO> collect = learningCount.stream().sorted(Comparator.comparing(VitalityVO::getVitalityCount).reversed()).collect(Collectors.toList());
            System.out.println(collect);
            final Float[] step = {1F};
            collect.stream().forEach(t -> {
                step[0] = step[0] - 0.0001F;
                t.setVitalityCount(step[0]);
            });
            Double avg = learningCount.stream().collect(Collectors.averagingDouble(VitalityVO::getVitalityCount));
            learningCount.stream().forEach(t -> {
                t.setCourseId("1");
                t.setAvgVitalityCount(avg.floatValue());
            });

            studentVitalityMapper.syncStudentVitality(BaseConvertUtil.convertList(learningCount, StudentVitality.class));
        }


        log.info("完成统计student_vitality~");
    }

    /**
     * 单科学习进度
     * 输入：
     * （视频观看）表名：dwi_playback_record_video_resources
     * （总体考试成绩）表名：student_course_count_score
     * （总作业成绩，需要按用户id取平均值）表名：dwi_homework_score
     * 输出：
     * 表名：student_progress
     * 字段:student_id：学生id，course_id：课程id，study_progress：学习进度，avg_progress：课程平均进度
     */
    @Scheduled(cron = "${scheduled.syncNoHasQzTask}")
    public void sync_student_progress() {
        log.info("开始统计student_progress~");
        List<StudyReport> lists = studentCourseCountScoreMapper.queryStudyReport();
        List<VitalityVO> homework = dwiScoreMapper.queryHomework();

        List<DwiCourse> courseList = dwiScoreMapper.queryAllCourse();
        Map<String, String> courseMap = courseList.stream().collect(Collectors.toMap(DwiCourse::getCourseId, DwiCourse::getHours, (key1, key2) -> key2));


        lists.stream().forEach(t -> {
            String hours = "72";
            if (courseMap.containsKey(t.getCourseId())) {
                hours = courseMap.get(t.getCourseId());
            }
            String courseId = t.getCourseId();

            //观看时长/总时长*0.34+总体考试成绩*0.33*0.01+作业平均分*0.33*0.01
            Float gksc = (Float.parseFloat(t.getTotalHours()) / Float.parseFloat(hours)) * 0.34F;
            Float ztkscj = t.getExamScore() * 0.33F * 0.01F;
            Map<String, Float> zycjmap = homework.stream()
                    .filter(tt -> tt.getStudentId().equals(t.getStudentId()) && tt.getCourseId().equals(t.getCourseId()))
                    .collect(Collectors.toMap(VitalityVO::getStudentId, VitalityVO::getScore, (key1, key2) -> key2));
            Float zycj = 0F;
            System.out.println(zycjmap);
            if (!CollectionUtils.isEmpty(zycjmap)) {
                zycj = zycjmap.get(t.getStudentId()) * 0.33F * 0.01F;
            }
            Float study_progress = gksc + ztkscj + zycj;
            t.setStudyProgress(study_progress);

        });

        Map<String, List<StudyReport>> mapListGroupByName = lists.stream().collect(Collectors.groupingBy(map -> map.getCourseId()));

        // 课程平均进度 - 按课程算平均值
        Map<String, Float> avgMap = new HashMap<>();
        mapListGroupByName.forEach((name, mapByNameList) -> {
            log.info(name);
            OptionalDouble averageOpt = mapByNameList.stream().mapToDouble(map -> map.getStudyProgress()).average();
            System.out.println(averageOpt);
            avgMap.put(name, Float.parseFloat(averageOpt.getAsDouble() + ""));

        });
        lists.stream().forEach(tt -> {
            String courseId = tt.getCourseId();
            if (avgMap.containsKey(courseId)) {
                tt.setAvgProgress(avgMap.get(courseId));
            } else {
                tt.setAvgProgress(0F);
            }
        });


        System.out.println(lists);

        studentCourseCountScoreMapper.syncProgress(lists);

        log.info("完成统计student_progress~");
    }


    /**
     * 单科学习投入度 todo
     */
    @Scheduled(cron = "${scheduled.syncHasQzTask}")
    public void sync_study_engagement() {
        log.info("开始统计study_engagement~");
        List<StudyReport> lists = studentCourseCountScoreMapper.queryStudyReport();
        List<TeacherCommentScore> thomework = dwiScoreMapper.queryTeacherCommentScore();

        List<DwiCourse> courseList = dwiScoreMapper.queryAllCourse();

        Map<String, String> courseMap = courseList.stream().collect(Collectors.toMap(DwiCourse::getCourseId, DwiCourse::getHours, (key1, key2) -> key2));

        Map<String, String> map = thomework.stream().collect(Collectors.toMap(TeacherCommentScore::getStudentId, TeacherCommentScore::getCommentScore, (key1, key2) -> key2));


        lists.stream().forEach(t -> {
            String hours = "72";
            if (courseMap.containsKey(t.getCourseId())) {
                hours = courseMap.get(t.getCourseId());
            }

            String courseId = t.getCourseId();

            //观看时长/总时长*0.25 +总体考试成绩*0.25*0.01 +师生印象*0.5*0.01
            Float gksc = (Float.parseFloat(t.getTotalHours()) / Float.parseFloat(hours)) * 0.25F;
            Float ztkscj = t.getExamScore() * 0.25F * 0.01F;
            Float zycj = 0F;
            if (map.containsKey(t.getStudentId())) {
                zycj = Float.parseFloat(map.get(t.getStudentId())) * 0.5F * 0.01F;
            } else {
                zycj = 0F;
            }
            t.setEngagementScore(gksc + ztkscj + zycj);

        });

        Map<String, List<StudyReport>> mapListGroupByName = lists.stream().collect(Collectors.groupingBy(map1 -> map1.getCourseId()));

        // 课程平均进度 - 按课程算平均值
        Map<String, Float> avgMap = new HashMap<>();
        mapListGroupByName.forEach((name, mapByNameList) -> {
            log.info(name);
            OptionalDouble averageOpt = mapByNameList.stream().mapToDouble(map2 -> map2.getEngagementScore()).average();
            avgMap.put(name, Float.parseFloat(averageOpt.getAsDouble() + ""));

        });

        lists.stream().forEach(tt -> {
            String courseId = tt.getCourseId();
            if (avgMap.containsKey(courseId)) {
                tt.setAvgEngagementScore(avgMap.get(courseId));
            } else {
                tt.setAvgEngagementScore(0F);
            }
        });

        studentCourseCountScoreMapper.syncStudyEngagement(lists);

        log.info("完成统计study_engagement~");
    }


    /**
     * 学习效果
     * study_effect
     * 输入：
     * <p> todo
     * 输出：
     * 字段：student_id：学生id，performance：效果值，avg_performance：平均效果值
     */
    //@Scheduled(cron = "${scheduled.syncHasQzTask}")
    public void sync_study_effect() {
        log.info("开始统计study_effect~");

        List<StudyEffect> homeworkList = dwiScoreMapper.queryHomeworkAvgScore();

        List<StudentCourseCountScore> lists = studentCourseCountScoreMapper.queryStudentCountScore();
        // 不想返回对象，只返回对象里某个属性时 采用这种方式
        //效果值:每个学生总体成绩均值*0.5*0.01 +每个学生的作业成绩均值*0.5*0.01
        Map<String, String> map = lists.stream().collect(Collectors.toMap(StudentCourseCountScore::getStudentId, StudentCourseCountScore::getCountScore, (key1, key2) -> key2));
        homeworkList.stream().forEach(t -> {
            Float score = t.getScore();
            if (map.containsKey(t.getStudentId())) {
                Float scoreq = Float.parseFloat(map.get(t.getStudentId()));
                Float s = score + scoreq;
                t.setPerformanceScore(s);
            } else {
                t.setPerformanceScore(score);
            }

        });

        Double avg = homeworkList.stream().collect(Collectors.averagingDouble(StudyEffect::getPerformanceScore));
        homeworkList.stream().forEach(t -> {
            t.setAvgPerformanceScore(avg.floatValue());
        });
        studentCourseCountScoreMapper.syncEffect(homeworkList);

        log.info("完成统计~");
    }


    /**
     * step 1
     * 知识体系完成度
     * 输入:
     * 库名：ie52 表名：course_knowledge
     * 库名：mydb 表名：user_knowledge_learning_process
     * 输出:
     * 表名：knowledge_complication
     * 字段：student_id：学生id，knowledge_score：知识完成度，avg_knowledge_score：平均知识完成度
     */
    @Scheduled(cron = "${scheduled.syncNoHasQzTask}")
//    @Scheduled(cron = "0/30 * * * * ?")
    public void sync_knowledge_complication() {
        log.info("开始统计knowledge_complication~");
        List<KnowledgeComplication> stuAll = studyAbilityMapper.queryStuCourseKnowledge();

        List<KnowledgeComplication> all = studyAbilityMapper.queryCourseKnowledge();

        List<DwiCourse> courseList = dwiScoreMapper.queryAllCourse();
        Map<String, String> courseMap = courseList.stream().collect(Collectors.toMap(DwiCourse::getId, DwiCourse::getCourseId, (key1, key2) -> key2));
        System.out.println(courseMap);

        stuAll.stream().forEach(t -> {
            String stuScore = t.getScore();
            String courseId = t.getCourseId();
            all.stream().forEach(tt -> {
                String courseIds = tt.getCourseId();
//                System.out.println(courseIds);
                if (courseId.equals(courseIds)) {
                    t.setCourseId(courseMap.get(courseId));
                    Float stuAvgScore = Float.parseFloat(stuScore) / Float.parseFloat(tt.getScore());
                    t.setStuKnowledgeScore(stuAvgScore);
                    t.setKnowledgeScore(stuAvgScore.toString());
                }
            });
        });

        Double avg = stuAll.stream().collect(Collectors.averagingDouble(KnowledgeComplication::getStuKnowledgeScore));
        stuAll.stream().forEach(t -> {
            t.setAvgKnowledgeScore(avg.toString());
        });

        knowledgeComplicationMapper.batchInsert(stuAll);
        log.info("完成统计knowledge_complication~");
    }


    public List<StudentCourseScore> queryStudentCourseScore() {
        return dwiScoreMapper.query_student_course_score();
    }


    public int batchStudentCourseScore(List<StudentCourseScore> list) {
        return playbackRecordVideoResourcesMapper.sync_student_course_score(list);
    }


    /**
     * student_total_progress
     * 学习进度
     */
//    @Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron = "${scheduled.syncHasQzTask}")
    public void syncStudyTotalProgress() {
        log.info("开始统计study_total_progress~");
        batchStudentTotalProgress();
        log.info("完成统计study_total_progress~");
    }


    public int batchStudentTotalProgress() {
        return playbackRecordVideoResourcesMapper.syncStudentTotalProgress();
    }

    /**
     * study_total_engagement
     * 学习投入度
     */
    @Scheduled(cron = "${scheduled.syncHasQzTask}")
    public void syncStudyEngagement() {
        log.info("开始统计study_total_engagement~");
        batchStudyEngagement();
        log.info("完成统计study_total_engagement~");
    }


    public int batchStudyEngagement() {
        return playbackRecordVideoResourcesMapper.syncStudyEngagement();
    }


    public int batchStudyAbility(List<StudyAbility> studyAbilities) {
        return playbackRecordVideoResourcesMapper.syncStudyAbility(studyAbilities);
    }


    //    @Scheduled(cron = "0 0/30 * * * ?")
    @Scheduled(cron = "${scheduled.syncHasQzTask}")
    public void syncUserPersonalData() throws Exception {
        log.info("开始统计user_personal_data~");
        int total = 20881343;
        int pageSize = 50000;
        int totalPageNum = (total + pageSize - 1) / pageSize;
        for (int i = 0; i < totalPageNum; i++) {
            log.info("page" + i);
            System.out.println("当前页为：" + i + ",每页限制：" + pageSize);
            List<UserPersonalData> list = dwiScoreMapper.queryUserPersonalData(i * pageSize, pageSize);
            String finishId = list.get(list.size() - 1).getStudentId();
            try {
                int result = playbackRecordVideoResourcesMapper.sync_user_personal_data(list);
                log.info(String.format("当前批量执行结果：%s %d ", finishId, result));
            } catch (Exception e) {

                e.printStackTrace();
            }

        }

        log.info("完成统计user_personal_data~");
    }

    @Scheduled(cron = "${scheduled.onceADay}")
    public void sync_student_course_relationship() {
        log.info("开始统计student_course_relationship~");

        List<DwiCourse> homeworkList = dwiScoreMapper.queryAllStudentCourse();
        studentCourseCountScoreMapper.syncStudentCourseRelationship(homeworkList);

        log.info("完成统计student_course_relationship~");
    }

}