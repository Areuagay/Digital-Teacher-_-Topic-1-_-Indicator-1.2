package com.gcc.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yp
 * @date: 2023/10/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentCourseRelationshipVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名
     */
    private String courseName;


}