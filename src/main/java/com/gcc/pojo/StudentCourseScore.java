package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudentCourseScore implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField(value = "exam_id")
    private String examId;

    @TableField(value = "course_id")
    private String courseId;

    //写库忽略该字段
    private String avgscore;

    //写库忽略该字段
    private String score;


    @TableField(value = "student_id")
    private String studentId;

    @TableField(value = "exam_name")
    private String examName;

    @TableField(value = "exam_times")
    private String examTimes;



}
