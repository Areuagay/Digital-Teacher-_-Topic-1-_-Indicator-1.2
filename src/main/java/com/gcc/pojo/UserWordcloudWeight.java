package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class UserWordcloudWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总进度词云权重
     */
    private Integer studyTotalProgressWordcloudWeight;

    /**
     * 总成绩词云权重
     */
    private Integer countScoreWordcloudWeight;

    /**
     * 总投入度词云权重
     */
    private Integer totalEngagementScoreWordcloudWeight;

    /**
     * 教师评价词云权重
     */
    private Integer commentScoreWordcloudWeight;

    /**
     * 单科成绩词云权重
     */
    private Integer scoreWordcloudWeight;

    /**
     * 单科学习进度词云权重
     */
    private Integer studyProgressWordcloudWeight;

    /**
     * 单科学习投入度词云权重
     */
    private Integer engagementScoreWordcloudWeight;

    /**
     * 活跃度词云权重
     */
    private Integer vitalityCountWordcountWeight;

    /**
     * 学习完成度词云权重
     */
    private Integer complicationScoreWordcloudWeight;

    /**
     * 知识完成度词云权重
     */
    private Integer knowledgeScoreWordcloudWeight;

    /**
     * 学习效果词云权重
     */
    private Integer performanceScoreWordcloudWeight;

    /**
     * 学习能力词云权重
     */
    private Integer abilityScoreWordcloudWeight;

    /**
     * 学习效率词云权重
     */
    private Integer efficiencyScoreWordcloudWeight;

    /**
     * 城市权重
     */
    private Integer cityWeight;

    /**
     * 职业权重
     */
    private Integer careerWeight;

    private Integer hobbyWeight;

    private String peopleTypeWeight;

    private Integer emotionWeight;

    private Integer characterWeight;

    private Integer shoppingMotivationWeight;

    private Integer constellationWeight;

    private Integer phoneTypeWeight;

    /**
     * 用户年龄段权重
     */
    private Integer ageWeight;

    private Integer word1Weight;

    private Integer word2Weight;

    private Integer word3Weight;

    private Integer word4Weight;

    private Integer word5Weight;

    private Integer word6Weight;

    private Integer word7Weight;

    private Integer word8Weight;

    private Integer word9Weight;

    private Integer word10Weight;

    private Integer word11Weight;

    private Integer word12Weight;

    private Integer word13Weight;

    private Integer word14Weight;

    private Integer word15Weight;


}
