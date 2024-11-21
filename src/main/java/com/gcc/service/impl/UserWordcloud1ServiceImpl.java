package com.gcc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcc.mapper.*;
import com.gcc.pojo.*;
import com.gcc.service.UserWordcloud1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Service
public class UserWordcloud1ServiceImpl extends ServiceImpl<UserWordcloud1Mapper, UserWordcloud1> implements UserWordcloud1Service {

    @Resource
    private UserWordcloud1Mapper uwm;

    @Resource
    private DwiScoreMapper scoreMapper;
    @Resource
    private DwiPlaybackRecordVideoResourcesMapper playbackRecordVideoResourcesMapper;

    @Resource
    private StudentTotalProgressMapper studentTotalProgressMapper;

    @Resource
    private StudentCourseCountScoreMapper studentCourseCountScoreMapper;

    @Resource
    private StudyTotalEngagementMapper studyTotalEngagementMapper;

    @Resource
    private TeacherCommentScoreMapper teacherCommentScoreMapper;

    @Resource
    private StudentCourseScoreMapper studentCourseScoreMapper;

    @Resource
    private StudentProgressMapper studentProgressMapper;

    @Resource
    private StudyEngagementMapper studyEngagementMapper;

    @Resource
    private StudentVitalityMapper studentVitalityMapper;

    @Resource
    private StudyComplicationMapper studyComplicationMapper;

    @Resource
    private KnowledgeComplicationMapper knowledgeComplicationMapper;

    @Resource
    private StudyEffectMapper studyEffectMapper;

    @Resource
    private StudyAbilityMapper studyAbilityMapper;

    @Resource
    private StudyEfficiencyMapper studyEfficiencyMapper;

    @Resource
    private UserPersonalDataMapper userPersonalDataMapper;

    @Resource
    private StudyTotalDurationMapper studyTotalDurationMapper;


    @Override
    public List<UserWordcloud1> getWordCloudByUserId(String id) {
        LambdaQueryWrapper<UserWordcloud1> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserWordcloud1::getStudentId, id);
        UserWordcloud1 userWordcloud1 = uwm.selectOne(lqw);
        if (userWordcloud1 == null) {
            return new ArrayList<>();
        }
        String wordCloud = userWordcloud1.getWordCloud();//用户词云

        //获取小时时长
        LambdaQueryWrapper<StudyTotalDuration> totalTimeWrapper = new LambdaQueryWrapper<>();
        totalTimeWrapper.eq(StudyTotalDuration::getStudentId, id);
        StudyTotalDuration studyTotalDuration = studyTotalDurationMapper.selectOne(totalTimeWrapper);

        if (studyTotalDuration == null) {
            wordCloud = getWorldCloudByTotalTime("0", wordCloud);
        } else {
            wordCloud = getWorldCloudByTotalTime(studyTotalDuration.getTotalHours(), wordCloud);
        }
        wordCloud += getTags(id);//固定词云
        wordCloud += otherMsg(id);


        userWordcloud1.setWordCloud(wordCloud);

        List<UserWordcloud1> userWordcloud1s = new ArrayList<>();
        userWordcloud1s.add(userWordcloud1);
        return userWordcloud1s;
    }

    private String getWorldCloudByTotalTime(String totalTime, String wordCloud) {
        List<String> wc = Arrays.asList(wordCloud.split(", "));
        int random = 23;
        if (Math.abs(Double.parseDouble(totalTime) - 0) <= 0.001) {
            random = 23;
        } else if (Double.parseDouble(totalTime) >= 2.0 && Double.parseDouble(totalTime) < 10.0) {
            random = 70;
        } else if (Double.parseDouble(totalTime) >= 10.0) {
            return wordCloud;
        } else if (Double.parseDouble(totalTime) < 2.0) {
            random = 40;
        }

        Collections.shuffle(wc);
        return String.join(", ", wc.subList(0, random));
    }


    private String otherMsg(String id) {
        LambdaQueryWrapper<UserPersonalData> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserPersonalData::getStudentId, id);
        UserPersonalData userPersonalData = userPersonalDataMapper.selectOne(lqw);

        String ageDes = "";
        String genDes = "";
        String degDes = "";

        try {
            int age = Integer.parseInt(userPersonalData.getAge());
            if (age >= 0 && age <= 29) {
                ageDes = "青年, 初出茅庐, 学生, 探索, 梦想";
            } else if (age >= 30 && age <= 39) {
                ageDes = "壮年, 事业, 家庭, 责任, 奋斗";
            } else if (age >= 40 && age <= 49) {
                ageDes = "中年, 事业巅峰, 家庭生活, 自我实现, 顶梁柱";
            } else if (age >= 50 && age <= 59) {
                ageDes = "职业经验, 领导力, 继续职业发展, 人生满足, 退休规划";
            } else {
                ageDes = "老年, 养老生活, 健康关切, 退休生活, 长者";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int userGender = Integer.parseInt(userPersonalData.getUserGender());
            if (userGender == 1) {
                genDes = "坚定, 理性, 责任, 勇敢, 冷静";
            } else {
                genDes = "同理心, 感性, 温柔, 耐心, 亲和力";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int userDegree = Integer.parseInt(userPersonalData.getDegree());
            if (userDegree == 1) {
                degDes = "深度调查, 学术交流, 论文, 研发, 学术研究";
            } else if (userDegree == 2) {
                degDes = "知识积累, 课程学习, 学科拓展, 知识提升, 学习计划";
            } else if (userDegree == 3) {
                degDes = "知识积累, 课程学习, 学科拓展, 知识提升, 学习计划";
            } else if (userDegree == 4) {
                degDes = "专项培训, 实践, 技能, 技术进步, 专业培养, 就业";
            } else if (userDegree == 5) {
                degDes = "职业培训, 实用技能, 就业前景, 实训, 实习";
            } else {
                degDes = "专业教育, 专业知识, 实践经验, 资格证书, 职业发展";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        if (!ageDes.isEmpty()) {
            sb.append(", ").append(ageDes);
        }
        if (!genDes.isEmpty()) {
            sb.append(", ").append(genDes);
        }
        if (!degDes.isEmpty()) {
            sb.append(", ").append(degDes);
        }
        return sb.toString();
    }

    private String getTags(String id) {
        String totalProgressWord = "学习起步";
        String countScoreWord = "还需努力";
        String studyTotalEngagementWord = "浅尝辄止";
        String teacherCommentScoreWord = "明显不足";
        String scoreWord = "待合格";
        String studyProgressWord = "课程起步";
        String studyEngagementWord = "浅层投入";
        String vitalityCountWord = "高冷";
        String complicationScoreWord = "点到为止";
        String efficiencyScoreWord = "树懒";
        String knowledgeScoreWord = "新手";
        String abilityScoreWord = "状态不佳";
        String performanceScoreWord = "知识初识";

        // 根据其他的表获取词
        try {
            LambdaQueryWrapper<StudentTotalProgress> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(StudentTotalProgress::getStudentId, id);
            List<StudentTotalProgress> studentTotalProgresss = studentTotalProgressMapper.selectList(lqw1);
            StudentTotalProgress studentTotalProgress = new StudentTotalProgress();
            if (studentTotalProgresss != null && !studentTotalProgresss.isEmpty()) {
                studentTotalProgress = studentTotalProgresss.get(0);
            } else {
                studentTotalProgress.setStudyTotalProgress("0.1");
            }
            double studyTotalProgress = Double.parseDouble(studentTotalProgress.getStudyTotalProgress());
            if (studyTotalProgress < 0.2) {
                totalProgressWord = "学习起步";
            } else if (studyTotalProgress < 0.4) {
                totalProgressWord = "进度较慢";
            } else if (studyTotalProgress < 0.6) {
                totalProgressWord = "进度正常";
            } else if (studyTotalProgress < 0.8) {
                totalProgressWord = "进度较快";
            } else {
                totalProgressWord = "基本完成学习";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudentCourseCountScore> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(StudentCourseCountScore::getStudentId, id);
            List<StudentCourseCountScore> studentCourseCountScore = studentCourseCountScoreMapper.selectList(lqw2);
            StudentCourseCountScore studentCourseCountScore1 = new StudentCourseCountScore();
            if (studentCourseCountScore != null && !studentCourseCountScore.isEmpty()) {
                studentCourseCountScore1 = studentCourseCountScore.get(0);
            } else {
                studentCourseCountScore1.setCountScore("50");
            }
            double countScore = Double.parseDouble(studentCourseCountScore1.getCountScore());
            if (countScore <= 60) {
                countScoreWord = "还需努力";
            } else if (countScore <= 80) {
                countScoreWord = "稳步攀升";
            } else if (countScore <= 90) {
                countScoreWord = "总体不错";
            } else {
                countScoreWord = "成绩突出";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudyTotalEngagement> lqw3 = new LambdaQueryWrapper<>();
            lqw3.eq(StudyTotalEngagement::getStudentId, id);
            List<StudyTotalEngagement> studyTotalEngagement = studyTotalEngagementMapper.selectList(lqw3);
            StudyTotalEngagement studyTotalEngagement1 = new StudyTotalEngagement();
            if (studyTotalEngagement != null && !studyTotalEngagement.isEmpty()) {
                studyTotalEngagement1 = studyTotalEngagement.get(0);
            } else {
                studyTotalEngagement1.setTotalEngagementScore("0.5");
            }
            double totalEngagementScore = Double.parseDouble(studyTotalEngagement1.getTotalEngagementScore());
            if (totalEngagementScore <= 0.6) {
                studyTotalEngagementWord = "浅尝辄止";
            } else if (totalEngagementScore <= 0.8) {
                studyTotalEngagementWord = "劳逸结合";
            } else {
                studyTotalEngagementWord = "全神贯注";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<TeacherCommentScore> lqw4 = new LambdaQueryWrapper<>();
            lqw4.eq(TeacherCommentScore::getStudentId, id);
            List<TeacherCommentScore> teacherCommentScore = teacherCommentScoreMapper.selectList(lqw4);
            TeacherCommentScore teacherCommentScore1 = new TeacherCommentScore();
            if (teacherCommentScore != null && !teacherCommentScore.isEmpty()) {
                teacherCommentScore1 = teacherCommentScore.get(0);
            } else {
                teacherCommentScore1.setCommentScore("2");
            }
            double commentScore = Double.parseDouble(teacherCommentScore1.getCommentScore());
            if (commentScore <= 3) {
                teacherCommentScoreWord = "明显不足";
            } else if (commentScore <= 6) {
                teacherCommentScoreWord = "稍有不足";
            } else if (commentScore <= 8) {
                teacherCommentScoreWord = "表现良好";
            } else {
                teacherCommentScoreWord = "表现优秀";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudentCourseScore> lqw5 = new LambdaQueryWrapper<>();
            lqw5.eq(StudentCourseScore::getStudentId, id);
            List<StudentCourseScore> studentCourseScores = studentCourseScoreMapper.selectList(lqw5);
            StudentCourseScore studentCourseScore = new StudentCourseScore();
            if (studentCourseScores != null && !studentCourseScores.isEmpty()) {
                studentCourseScore = studentCourseScores.get(0);
            } else {
                studentCourseScore.setScore("50");
            }
            double score = Double.parseDouble(studentCourseScore.getScore());
            if (score <= 60) {
                scoreWord = "待合格";
            } else if (score <= 70) {
                scoreWord = "合格";
            } else if (score <= 85) {
                scoreWord = "良好";
            } else {
                scoreWord = "优秀";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudentProgress> lqw6 = new LambdaQueryWrapper<>();
            lqw6.eq(StudentProgress::getStudentId, id);
            List<StudentProgress> studentProgress = studentProgressMapper.selectList(lqw6);
            StudentProgress studentProgress1 = new StudentProgress();
            if (studentProgress != null && !studentProgress.isEmpty()) {
                studentProgress1 = studentProgress.get(0);
            } else {
                studentProgress1.setStudyProgress("0.1");
            }
            double studyProgress = Double.parseDouble(studentProgress1.getStudyProgress());
            if (studyProgress <= 0.2) {
                studyProgressWord = "课程起步";
            } else if (studyProgress <= 0.4) {
                studyProgressWord = "了解课程";
            } else if (studyProgress <= 0.6) {
                studyProgressWord = "熟悉课程";
            } else if (studyProgress <= 0.8) {
                studyProgressWord = "掌握课程";
            } else {
                studyProgressWord = "基本结课";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudyEngagement> lqw7 = new LambdaQueryWrapper<>();
            lqw7.eq(StudyEngagement::getStudentId, id);
            List<StudyEngagement> studyEngagement = studyEngagementMapper.selectList(lqw7);
            StudyEngagement studyEngagement1 = new StudyEngagement();
            if (studyEngagement != null && !studyEngagement.isEmpty()) {
                studyEngagement1 = studyEngagement.get(0);
            } else {
                studyEngagement1.setEngagementScore("0.2");
            }
            double engagementScore = Double.parseDouble(studyEngagement1.getEngagementScore());
            if (engagementScore <= 0.3) {
                studyEngagementWord = "浅层投入";
            } else if (engagementScore <= 0.6) {
                studyEngagementWord = "适度投入";
            } else if (engagementScore <= 0.9) {
                studyEngagementWord = "正常投入";
            } else {
                studyEngagementWord = "深度投入";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudentVitality> lqw8 = new LambdaQueryWrapper<>();
            lqw8.eq(StudentVitality::getStudentId, id);
            List<StudentVitality> studentVitalitys = studentVitalityMapper.selectList(lqw8);
            StudentVitality studentVitality = new StudentVitality();
            if (studentVitalitys != null && !studentVitalitys.isEmpty()) {
                studentVitality = studentVitalitys.get(0);
            } else {
                studentVitality.setVitalityCount(0.5F);
            }
            double vitalityCount = studentVitality.getVitalityCount();
            if (vitalityCount <= 0.5) {
                vitalityCountWord = "高冷";
            } else {
                vitalityCountWord = "社牛";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudyComplication> lqw9 = new LambdaQueryWrapper<>();
            lqw9.eq(StudyComplication::getStudentId, id);
            List<StudyComplication> studyComplication = studyComplicationMapper.selectList(lqw9);
            StudyComplication studyComplication1 = new StudyComplication();
            if (studyComplication != null && !studyComplication.isEmpty()) {
                studyComplication1 = studyComplication.get(0);
            } else {
                studyComplication1.setComplicationScore(0.5F);
            }
            double complicationScore = studyComplication1.getComplicationScore();
            if (complicationScore <= 0.5) {
                complicationScoreWord = "点到为止";
            } else if (complicationScore <= 0.8) {
                complicationScoreWord = "空杯心态";
            } else {
                complicationScoreWord = "求知欲强";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<KnowledgeComplication> lqw10 = new LambdaQueryWrapper<>();
            lqw10.eq(KnowledgeComplication::getStudentId, id);
            List<KnowledgeComplication> knowledgeComplication = knowledgeComplicationMapper.selectList(lqw10);
            KnowledgeComplication knowledgeComplication1 = new KnowledgeComplication();
            if (knowledgeComplication != null && !knowledgeComplication.isEmpty()) {
                knowledgeComplication1 = knowledgeComplication.get(0);
            } else {
                knowledgeComplication1.setKnowledgeScore("0.5");
            }
            double knowledgeScore = Double.parseDouble(knowledgeComplication1.getKnowledgeScore());
            if (knowledgeScore <= 0.5) {
                knowledgeScoreWord = "新手";
            } else if (knowledgeScore <= 0.8) {
                knowledgeScoreWord = "高手";
            } else {
                knowledgeScoreWord = "达人";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudyEffect> lqw11 = new LambdaQueryWrapper<>();
            lqw11.eq(StudyEffect::getStudentId, id);
            List<StudyEffect> studyEffect = studyEffectMapper.selectList(lqw11);
            StudyEffect studyEffect1 = new StudyEffect();
            if (studyEffect != null && !studyEffect.isEmpty()) {
                studyEffect1 = studyEffect.get(0);
            } else {
                studyEffect1.setPerformanceScore(0.5F);
            }
            double performanceScore = studyEffect1.getPerformanceScore();
            if (performanceScore <= 0.2) {
                performanceScoreWord = "知识初识";
            } else if (performanceScore <= 0.4) {
                performanceScoreWord = "总体认识";
            } else if (performanceScore <= 0.6) {
                performanceScoreWord = "投入学习";
            } else if (performanceScore <= 0.8) {
                performanceScoreWord = "大体掌握";
            } else {
                performanceScoreWord = "融会贯通";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudyAbility> lqw12 = new LambdaQueryWrapper<>();
            lqw12.eq(StudyAbility::getStudentId, id);
            List<StudyAbility> studyAbility = studyAbilityMapper.selectList(lqw12);
            StudyAbility studyAbility1 = new StudyAbility();
            if (studyAbility != null && !studyAbility.isEmpty()) {
                studyAbility1 = studyAbility.get(0);
            } else {
                studyAbility1.setAbilityScore("0.5");
            }
            double abilityScore = Double.parseDouble(studyAbility1.getAbilityScore());
            if (abilityScore <= 0.2) {
                abilityScoreWord = "状态不佳";
            } else if (abilityScore <= 0.4) {
                abilityScoreWord = "仍需努力";
            } else if (abilityScore <= 0.6) {
                abilityScoreWord = "初见成效";
            } else if (abilityScore <= 0.8) {
                abilityScoreWord = "稳步向前";
            } else {
                abilityScoreWord = "游刃有余";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            LambdaQueryWrapper<StudyEfficiency> lqw13 = new LambdaQueryWrapper<>();
            lqw13.eq(StudyEfficiency::getStudentId, id);
            List<StudyEfficiency> studyEfficiency = studyEfficiencyMapper.selectList(lqw13);
            StudyEfficiency studyEfficiency1 = new StudyEfficiency();
            if (studyEfficiency != null && !studyEfficiency.isEmpty()) {
                studyEfficiency1 = studyEfficiency.get(0);
            } else {
                studyEfficiency1.setEfficiencyScore("0.5");
            }
            double efficiencyScore = Double.parseDouble(studyEfficiency1.getEfficiencyScore());
            if (efficiencyScore <= 0.5) {
                efficiencyScoreWord = "树懒";
            } else if (efficiencyScore <= 0.8) {
                efficiencyScoreWord = "有效率";
            } else {
                efficiencyScoreWord = "时间管理大师";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return totalProgressWord + ", " + countScoreWord + ", " + studyTotalEngagementWord + ", " + teacherCommentScoreWord + ", " + scoreWord + ", " + studyProgressWord + ", " + studyEngagementWord + ", " + vitalityCountWord + ", " + complicationScoreWord + ", " + efficiencyScoreWord + ", " + knowledgeScoreWord + ", " + abilityScoreWord + ", " + performanceScoreWord;
    }
}
