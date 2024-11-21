package com.gcc.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StudyReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 总体考试成绩
     */
    private Float examScore;

    /**
     * 总时长
     */
    private String totalHours;



    private Float studyProgress;

    private Float avgProgress;


    private Float commentScore;

    private Float engagementScore;

    private Float avgEngagementScore;



    private Float performanceScore;

    private Float avgPerformanceScore;


    /**
     * 视频观看时长
     */
    private Float videoWatchScore;
    /**
     *文档查看时长
     */
    private Float fileWatchScore;
    /**
     *题目练习时长
     */
    private Float testTimeScore;
    /**
     *讨论时长
     */
    private Float discussionScore;
    /**
     *考试时长
     */
    private Float examinationTimeScore;


    /**
     * 效率值
     */
    private Float efficiencyScore;
    /**
     *平均效率值
     */
    private Float avgEfficiencyScore;




}
