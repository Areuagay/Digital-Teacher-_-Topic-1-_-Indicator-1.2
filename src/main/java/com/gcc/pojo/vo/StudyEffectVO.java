package com.gcc.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gcc.pojo.StudyEffect;
import lombok.Data;

@Data
public class StudyEffectVO  {
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
    private Float performanceScore;

    /**
     * 平均效果值
     */
    private Float avgPerformanceScore;

    //写库忽略该字段
    @TableField(exist = false)
    private String score;

    public StudyEffectVO(String studentId, String courseId, Float performanceScore, Float avgPerformanceScore, String score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.performanceScore = performanceScore;
        this.avgPerformanceScore = avgPerformanceScore;
        this.score = score;
    }

    public StudyEffectVO(StudyEffect studyEffect){
        setPerformanceScore(0.35F);
        setAvgPerformanceScore(0.4F);
        setCourseId(studyEffect.getCourseId());
        setScore("0.35");
        setStudentId(studyEffect.getStudentId());
    }
    public StudyEffectVO() {
    }
}
