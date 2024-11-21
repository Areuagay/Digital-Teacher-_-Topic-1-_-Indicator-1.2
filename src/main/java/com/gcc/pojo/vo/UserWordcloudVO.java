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
public class UserWordcloudVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String studentId;

    private String courseId;

    private String words;

//    /**
//     * 总进度词云
//     */
//    private String studyTotalProgressWordcloud;
//
//    /**
//     * 总成绩词云
//     */
//    private String countScoreWordcloud;
//
//    /**
//     * 总投入度词云
//     */
//    private String totalEngagementScoreWordcloud;
//
//    /**
//     * 教师评价词云
//     */
//    private String commentScoreWordcloud;
//
//    /**
//     * 单科成绩词云
//     */
//    private String scoreWordcloud;
//
//    /**
//     * 单科学习进度词云
//     */
//    private String studyProgressWordcloud;
//
//    /**
//     * 单科学习投入度词云
//     */
//    private String engagementScoreWordcloud;
//
//    /**
//     * 活跃度词云
//     */
//    private String vitalityCountWordcount;
//
//    /**
//     * 学习完成度词云
//     */
//    private String complicationScoreWordcloud;
//
//    /**
//     * 知识完成度词云
//     */
//    private String knowledgeScoreWordcloud;
//
//    /**
//     * 学习效果词云
//     */
//    private String performanceScoreWordcloud;
//
//    /**
//     * 学习能力词云
//     */
//    private String abilityScoreWordcloud;
//
//    /**
//     * 学习效率词云
//     */
//    private String efficiencyScoreWordcloud;
//
//    /**
//     * 城市
//     */
//    private String city;
//
//    /**
//     * 职业
//     */
//    private String career;
//
//    private String hobby;
//
//    private String peopleType;
//
//    private String emotion;
//
//    private String characterName;
//
//    private String shoppingMotivation;
//
//    private String constellation;
//
//    private String phoneType;
//
//    /**
//     * 用户年龄段
//     */
//    private String age;
//
//    private String word1;
//
//    private String word2;
//
//    private String word3;
//
//    private String word4;
//
//    private String word5;
//
//    private String word6;
//
//    private String word7;
//
//    private String word8;
//
//    private String word9;
//
//    private String word10;
//
//    private String word11;
//
//    private String word12;
//
//    private String word13;
//
//    private String word14;
//
//    private String word15;


}
