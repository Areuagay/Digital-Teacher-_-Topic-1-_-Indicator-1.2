package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudyComplication implements Serializable {

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
     * 学习完成度
     */
    private Float complicationScore;

    /**
     * 平均学习完成度
     */
    private String avgComplicationScore;

    //写库忽略该字段
    @TableField(exist = false)
    private String score;

    public StudyComplication(String studentId, String courseId, Float complicationScore, String avgComplicationScore) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.complicationScore = complicationScore;
        this.avgComplicationScore = avgComplicationScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyComplication that = (StudyComplication) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
