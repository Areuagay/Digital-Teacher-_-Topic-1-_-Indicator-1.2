package com.gcc.controller;

import com.gcc.common.Result;
import com.gcc.pojo.*;
import com.gcc.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/GetUserPesonaByUserIdAndCourseId")
public class UserPersonaController {
    @Resource
    private StudentCourseCountScoreService sccss;
    @Resource
    private TeacherCommentScoreService tcss;

    @Resource
    private StudentTotalProgressService stps;
    @Resource
    private StudyTotalEngagementService stes;

    /**
     * StudyTotalDuration 不完整，没有controller类和service类，未完成
     */
    @Resource
    private StudyTotalDurationService stds;
    @Resource
    private UserPersonalDataService upds;

    @Resource
    private UserWordcloud1Service uwcs;
    @Resource
    private StudyDurationService sds;
    @Resource
    private StudentProgressService sps;
    @Resource
    private StudentVitalityService svs;
    @Resource
    private StudyComplicationService scs;
    @Resource
    private KnowledgeComplicationService kcs;
    @Resource
    private StudyEffectService seffects;
    @Resource
    private StudyAbilityService sas;
    @Resource
    private StudyEfficiencyService sefficencys;
    @Resource
    private StudentCourseScoreService scss;
    @Resource
    private StudyEngagementService ses;

    @GetMapping("/{user_id}/{course_id}")
    public Result<UserPersona> getUserPesonaByUserIdAndCourseId(@PathVariable String user_id, @PathVariable String course_id) {
        List<StudentCourseCountScore> sccss_ = sccss.queryAvgScoreById(user_id);
        List<TeacherCommentScore> tcss_ = tcss.getCommentById(user_id);
        List<StudentTotalProgress> stps_ = stps.getTotalProgressyByUserId(user_id);
        List<StudyTotalEngagement> stes_ = stes.getTotalEngagementByUserId(user_id);
        List<UserPersonalData> upds_ = upds.getPersonInfoByUserId(user_id);
        List<UserWordcloud1> uwcs_ = uwcs.getWordCloudByUserId(user_id);
        List<StudyDuration> sds_ = sds.getStudyDurationByUserId(user_id, course_id);
        List<StudentProgress> sps_ = sps.getProgressByUserId(user_id, course_id);
        List<StudentVitality> svs_ = svs.getVitalityByUserId(user_id, course_id);
        List<StudyComplication> scs_ = scs.getComplicationByUserId(user_id, course_id);
        List<KnowledgeComplication> kcs_ = kcs.queryById(user_id, course_id);
        List<StudyEffect> seffects_ = seffects.getEffectByUserId(user_id, course_id);
        List<StudyAbility> sas_ = sas.getAbilityByUserId(user_id, course_id);
        List<StudyEfficiency> sefficencys_ = sefficencys.getEfficiencyByUserId(user_id, course_id);
        List<StudentCourseScore> scss_ = scss.getSingleCourseAvgScore(user_id, course_id);
        List<StudyEngagement> ses_ = ses.getEngagementByUserId(user_id, course_id);
        List<StudyTotalDuration> stds_ = stds.getTotalDurationByUserId(user_id);

        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat di = new DecimalFormat("0");

        UserPersona userPersona = new UserPersona();
        if (sccss_.size() != 0)
            userPersona.setCountScore(di.format(Float.parseFloat(sccss_.get(0).getCountScore())));
        else
            userPersona.setCountScore("60");

        if (tcss_.size() != 0) {
            userPersona.setInstructorComment(tcss_.get(0).getInstructorComment());
            userPersona.setCommentScore(di.format(Float.parseFloat(tcss_.get(0).getCommentScore())));
        } else {
            userPersona.setInstructorComment("比较好");
            userPersona.setCommentScore("50");
        }

        if (scss_.size() != 0)
            userPersona.setStudentCourseScores(scss_);
        else {
            List<StudentCourseScore> s = new ArrayList<>();
            StudentCourseScore ss = new StudentCourseScore("436188", course_id, "60", "60", user_id, "模拟考试", "2022-03-29");
            s.add(ss);
            userPersona.setStudentCourseScores(s);
        }


        if (stps_.size() != 0)
            userPersona.setStudyTotalProgress(df.format(Float.parseFloat(stps_.get(0).getStudyTotalProgress())));
        else
            userPersona.setStudyTotalProgress("0.2");

        if (stes_.size() != 0)
            userPersona.setTotalEngagementScore(df.format(Float.parseFloat(stes_.get(0).getTotalEngagementScore())));
        else
            userPersona.setTotalEngagementScore("0.3");

        if (upds_.size() != 0) {
            UserPersonalData u = upds_.get(0);
            userPersona.setUserName(u.getUserName());
            userPersona.setUserGender(u.getUserGender());
            userPersona.setAge(u.getAge());
            userPersona.setNationality(u.getNationality());
            userPersona.setDegree(u.getDegree());
            userPersona.setPoliticalStatus(u.getPoliticalStatus());
            userPersona.setGraduateInstitutions(u.getGraduateInstitutions());
            userPersona.setFormerMajor(u.getFormerMajor());
            userPersona.setEnrollmentData(u.getEnrollmnentData());
            userPersona.setEducationBackground(u.getEducationBackground());
            userPersona.setMajorAdmission(u.getMajorAdmission());
            userPersona.setStudentStatus(u.getStudentStatus());
            userPersona.setPhoneNumber(u.getPhoneNumber());
            userPersona.setEMail(u.getEMail());
            userPersona.setInstitutionalFramework(u.getInstitutionalFramework());
        } else {
            userPersona.setUserName("张威剑");
            userPersona.setUserGender("1");
            userPersona.setAge("31");
            userPersona.setNationality("01");
            userPersona.setDegree("01211030100");
            userPersona.setPoliticalStatus("03");
            userPersona.setGraduateInstitutions("番禺区分校");
            userPersona.setFormerMajor("艺术设计");
            userPersona.setEnrollmentData("202103");
            userPersona.setEducationBackground("国家开放大学广州分部");
            userPersona.setMajorAdmission("大专");
            userPersona.setStudentStatus("1");
            userPersona.setPhoneNumber("***********");
            userPersona.setEMail("***********");
            userPersona.setInstitutionalFramework("国家开放大学广州分部");
        }

        if (uwcs_.size() != 0)
            userPersona.setWordcloud(uwcs_.get(0).getWordCloud());
        else
            userPersona.setWordcloud("赞赏, 尊敬, 赞许, 爱戴, 文化, 教育, 政策, 经济, 财产, 产业, 学习, 政策 , 生命安全, 企业, 交通, 社会, 道德, 国家, 人类, 个体, 个人, 人群, 社会, 公民, 成人, 伙伴, 家庭, 朋友, 同事,  教育, 学生, 工人, 领导, 艺术家, 创作, 旅行, 科学, 哲学, 健康, 心理, 个性, 开放, 成长, 学习起步, 还需努力, 浅尝辄止, 明显不足, 待合格, 课程起步, 浅层投入, 高冷, 点到为止, 树懒, 新手, 状态不佳, 知识初识");

        if (sds_.size() == 0) {
            userPersona.setVideoWatchscore("0.1");
            userPersona.setFileWatchscore("0.1");
            userPersona.setTestTimescore("0.1");
            userPersona.setDiscussionScore("0.1");
            userPersona.setExaminationTimescore("0.1");
        } else {
            StudyDuration s = sds_.get(0);
            if (Objects.equals(s.getVideoWatchScore(), "0.0")) {
                userPersona.setVideoWatchscore("0.1");
                userPersona.setFileWatchscore("0.1");
                userPersona.setTestTimescore("0.1");
                userPersona.setDiscussionScore("0.1");
                userPersona.setExaminationTimescore("0.1");
            } else {
                userPersona.setVideoWatchscore(df.format(Float.parseFloat(s.getVideoWatchScore())));
                userPersona.setFileWatchscore(df.format(Float.parseFloat(s.getFileWatchScore())));
                userPersona.setTestTimescore(df.format(Float.parseFloat(s.getTestTimeScore())));
                userPersona.setDiscussionScore(df.format(Float.parseFloat(s.getDiscussionScore())));
                userPersona.setExaminationTimescore(df.format(Float.parseFloat(s.getExaminationTimeScore())));
            }
        }

        if (sps_.size() != 0) {
            StudentProgress s = sps_.get(0);
            userPersona.setStudyProgress(df.format(Float.parseFloat(s.getStudyProgress())));
            userPersona.setAvgProgress(df.format(Float.parseFloat(s.getAvgProgress())));
        } else {
            userPersona.setStudyProgress("0.35");
            userPersona.setAvgProgress("0.40");
        }

        if (svs_.size() != 0) {
            StudentVitality sv = svs_.get(0);
            userPersona.setVitalityCount(String.valueOf(Float.parseFloat(df.format(sv.getVitalityCount()))));
            userPersona.setAvgVitalitycount(String.valueOf(Float.parseFloat(df.format(sv.getAvgVitalityCount()))));
        } else {
            userPersona.setVitalityCount("0.35");
            userPersona.setAvgVitalitycount("0.40");
        }

        if (scs_.size() == 0) {
            userPersona.setComplicationScore("0.35");
            userPersona.setAvgComplicationscore("0.40");
        } else {
            StudyComplication scs = scs_.get(0);
            if (scs.getComplicationScore() == 0.0) {
                userPersona.setComplicationScore("0.35");
                userPersona.setAvgComplicationscore("0.40");
            } else {
                userPersona.setComplicationScore(df.format(Float.parseFloat(String.valueOf(scs.getComplicationScore()))));
                userPersona.setAvgComplicationscore(df.format(Float.parseFloat(String.valueOf(scs.getAvgComplicationScore()))));
            }

        }

        if (kcs_.size() != 0) {
            KnowledgeComplication k = kcs_.get(0);
            userPersona.setKnowledgeScore(df.format(Float.parseFloat(k.getKnowledgeScore())));
            userPersona.setAvgKnowledgescore(df.format(Float.parseFloat(k.getAvgKnowledgeScore())));
        } else {
            userPersona.setKnowledgeScore("0.35");
            userPersona.setAvgKnowledgescore("0.40");
        }

        if (seffects_.size() == 0) {
            userPersona.setPerformanceScore("0.35");
            userPersona.setAvgPerformancescore("0.40");
        } else {
            StudyEffect se = seffects_.get(0);
            if (se.getPerformanceScore() == 0.0) {
                userPersona.setPerformanceScore(df.format(Float.parseFloat("0.35")));
                userPersona.setAvgPerformancescore(df.format(Float.parseFloat(String.valueOf(se.getAvgPerformanceScore()))));
            } else {
                userPersona.setPerformanceScore(df.format(Float.parseFloat(String.valueOf(se.getPerformanceScore()))));
                userPersona.setAvgPerformancescore(df.format(Float.parseFloat(String.valueOf(se.getAvgPerformanceScore()))));
            }

        }

        if (sas_.size() != 0) {
            StudyAbility sas = sas_.get(0);
            userPersona.setAbilityScore(df.format(Float.parseFloat(sas.getAbilityScore())));
            userPersona.setAvgAbilityscore(df.format(Float.parseFloat(sas.getAvgAbilityScore())));
        } else {
            userPersona.setAbilityScore("0.35");
            userPersona.setAvgAbilityscore("0.40");
        }

        if (sefficencys_.size() == 0) {
            userPersona.setEfficiencyScore("0.35");
            userPersona.setAvgEfficiencyscore("0.40");
        } else {
            StudyEfficiency sef = sefficencys_.get(0);
            if (Objects.equals(sef.getEfficiencyScore(), "0.0")) {
                userPersona.setEfficiencyScore("0.35");
                userPersona.setAvgEfficiencyscore("0.40");
            } else {
                userPersona.setEfficiencyScore(df.format(Float.parseFloat(sef.getEfficiencyScore()) * 0.1));
                userPersona.setAvgEfficiencyscore(df.format(Float.parseFloat(sef.getAvgEfficiencyScore()) * 0.1));
            }
        }

//        if(scss_.size()!=0)
//        {
//            StudentCourseScore s=scss_.get(0);
//            userPersona.setStudentCourseScores(scss_);
//        }

//        if(ses_.size()!=0){
//            userPersona.setEngagementScore(ses_.get(0).getEngagementScore());
//        }

        if (stds_.size() == 0) {
            userPersona.setTotal_hours("1");
            userPersona.setTotal_minutes("60");
        } else {
            StudyTotalDuration std = stds_.get(0);
            if (std.getTotalHours() != null) {
                userPersona.setTotal_hours(df.format(Float.parseFloat(std.getTotalHours())));
                userPersona.setTotal_minutes(df.format(Float.parseFloat(std.getTotalMinutes())));
            } else if (Objects.equals(std.getTotalHours(), "0")) {
                userPersona.setTotal_hours("1");
                userPersona.setTotal_minutes("60");
            } else {
                userPersona.setTotal_hours("1");
                userPersona.setTotal_minutes("60");
            }
        }

        return Result.success(userPersona);
    }
}
