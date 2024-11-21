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
public class VitalityVO implements Serializable {

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
     * 效果值
     */
    private Float cnt;

    private Float vitalityCount;

    private Float avgVitalityCount;

    private Float score;


    private Float studyProgress;

    private Float avgProgress;



    private Float commentScore;

    private Float engagementScore;

    private Float avgEngagementScore;

}
