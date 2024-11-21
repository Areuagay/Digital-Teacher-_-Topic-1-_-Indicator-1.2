package com.gcc.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户访问表 dwi_user_visit
 *
 * @author dev
 * @date 2023-03-15
 */
@Data
@NoArgsConstructor
public class DwiUserVisit {
    private static final long serialVersionUID = 1L;

    /**
     * 学习活动Id
     */
    @JSONField(name = "activity_id")
    @TableField("ActivityId")
    private String activityId;
    /**
     * 行为类型
     */
//	@JSONField(name="activity_type")
    @TableField("ActionType")
    private String actionType;
    /**
     * 活动类型
     */
    @JSONField(name = "activity_type")
    @TableField("ActivityType")
    private String activityType;
    /**
     * 浏览器
     */
    @JSONField(name = "browser")
    @TableField("Browser")
    private String browser;
    /**
     * 课程代码
     */
    @JSONField(name = "course_code")
    @TableField("CourseCode")
    private String courseCode;
    /**
     * 课程名称
     */
    @JSONField(name = "course_name")
    @TableField("CourseName")
    private String courseName;
    /**
     * 组Id
     */
//	@JSONField(name="")
    @TableField("GroupId")
    private String groupId;
    /**
     * 是否手机端
     */
//	@JSONField(name="")
    @TableField("IsMobile")
    private String isMobile;
    /**
     * 是否教师
     */
    @JSONField(name = "is_teacher")
    @TableField("IsTeacher")
    private String isTeacher;
    /**
     * 机构代码
     */
    @JSONField(name = "org_code")
    @TableField("OrganizationCode")
    private String organizationCode;
    /**
     * 机构名称
     */
    @JSONField(name = "org_name")
    @TableField("OrganizationName")
    private String organizationName;
    /**
     * 用户名称
     */
    @JSONField(name = "user_name")
    @TableField("UserName")
    private String userName;
    /**
     * 用户标号
     */
    @JSONField(name = "user_no")
    @TableField("UserNumber")
    private String userNumber;
    /**
     * 观看时长
     */
    @JSONField(name = "visit_duration")
    @TableField("VisitDuration")
    private String visitDuration;
    /**
     * 身份
     */
    @TableField("Role")
    private String role;
    /**
     * 日期
     */
    @TableField("Day")
    private String day;

    @JSONField(name = "created_at")
    @TableField("CreateTime")
    private String createdAt;

    @JSONField(name = "user_agent")
    @TableField(exist = false)
    private String userAgent;


}
