package com.gcc.pojo;

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
 * @since 2022-12-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserPersonalData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "student_id")
    private String studentId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户性别
     */
    private String userGender;

    /**
     * 用户年龄段
     */
    private String age;

    /**
     *
     */
    private String nationality;

    /**
     * 学历
     */
    private String degree;

    /**
     *
     */
    private String politicalStatus;

    private String graduateInstitutions;

    private String formerMajor;

    private String enrollmnentData;

    private String educationBackground;

    private String majorAdmission;
    private String studentStatus;
    private String phoneNumber;
    private String eMail;
    private String institutionalFramework;



}
