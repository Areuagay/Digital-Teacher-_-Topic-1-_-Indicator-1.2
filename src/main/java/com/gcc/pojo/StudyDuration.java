package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author gcc
 * @since 2023-02-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudyDuration implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "student_id")
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;
    /**
     * 视频观看得分
     */
    private String videoWatchScore;

    /**
     * 文档查看得分
     */
    private String fileWatchScore;

    /**
     * 题目练习时长得分
     */
    private String testTimeScore;

    /**
     * 讨论时长得分
     */
    private String discussionScore;

    /**
     * 考试时长得分
     */
    private String examinationTimeScore;


    public StudyDuration(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        videoWatchScore = "0";
        fileWatchScore = "0";
        testTimeScore = "0";
        discussionScore = "0";
        examinationTimeScore = "0";
    }
}
