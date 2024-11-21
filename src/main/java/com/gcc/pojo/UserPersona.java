package com.gcc.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String countScore;
    private String instructorComment;
    private String commentScore;

    private String studyTotalProgress;
    private String totalEngagementScore;
    private String total_hours;
    private String total_minutes;

//    user_personal_data
    private String userName;
    private String userGender;
    private String age;
    private String nationality;
    private String degree;
    private String politicalStatus;
    private String graduateInstitutions;
    private String formerMajor;
    private String enrollmentData;
    private String educationBackground;
    private String majorAdmission;
    private String studentStatus;
    private String phoneNumber;
    private String eMail;
    private String institutionalFramework;

    private String wordcloud;

    private String videoWatchscore;
    private String fileWatchscore;
    private String testTimescore;
    private String discussionScore;
    private String examinationTimescore;

    private String studyProgress;
    private String avgProgress;
    private String vitalityCount;
    private String avgVitalitycount;
    private String complicationScore;
    private String avgComplicationscore;

    private String knowledgeScore;
    private String avgKnowledgescore;
    private String performanceScore;
    private String avgPerformancescore;
    private String abilityScore;
    private String avgAbilityscore;

    private String efficiencyScore;
    private String avgEfficiencyscore;

//    private String examId;
//    private String score;
//    private String examName;
//    private String examTimes;
//    private String avgScore;
    /* private String engagementScore;*/
    private List<StudentCourseScore>studentCourseScores;

    private String engagementScore;





}
