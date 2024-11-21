package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生选课<学生课程关系>
 *
 * @TableName dwi_student_course
 */
@Data
public class DwiStudentCourse implements Serializable {
    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     *
     */
    @TableField(value = "studentnumber")
    private String studentNumber;

    /**
     *
     */
    @TableField(value = "semestercode")
    private String semesterCode;

    /**
     *
     */
    @TableField(value = "coursecode")
    private String courseCode;

    /**
     *
     */
    @TableField(value = "coursename")
    private String courseName;

    /**
     *
     */
    @TableField(value = "provinceschoolcode")
    private String provinceSchoolCode;

    /**
     *
     */
    @TableField(value = "collegeschoolcode")
    private String collegeSchoolCode;

    /**
     *
     */
    @TableField(value = "teachingschoolcode")
    private String teachingSchoolCode;

    /**
     *
     */
    @TableField(value = "selectcoursetime")
    private String selectCourseTime;

    /**
     *
     */
    @TableField(value = "credit")
    private Double credit;

    /**
     *
     */
    @TableField(value = "coursenature")
    private String courseNature;

    /**
     *
     */
    @TableField(value = "curriculumorganizationcode")
    private String curriculumorganizationcode;

    /**
     *
     */
    @TableField(value = "teachingtaskid")
    private String teachingTaskId;

    /**
     *
     */
    @TableField(value = "confirm")
    private String confirm;

    /**
     *
     */
    @TableField(value = "confirmtime")
    private String confirmTime;

    /**
     *
     */
    @TableField(value = "status")
    private String status;

    /**
     *
     */
    @TableField(value = "createtime")
    private Date createTime;

    /**
     *
     */
    @TableField(value = "lastupdate")
    private Date lastUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}