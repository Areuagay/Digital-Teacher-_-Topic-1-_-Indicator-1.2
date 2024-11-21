package com.gcc.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 学习活动表 dwi_learning_activitys
 * 
 * @author dev
 * @date 2023-03-15
 */
@Data
public class DwiLearningActivitys
{
	private static final long serialVersionUID = 1L;
	
	/** 活动Id */
	@JSONField(name="activity_id")
	private String activityId;
	/** 课程代码 */
	@JSONField(name="course_code")
	private String courseCode;
	/** 课程名称 */
	@JSONField(name="course_name")
	private String courseName;
	/** 机构代码 */
	@JSONField(name="org_code")
	private String organizationCode;
	/** 机构名称 */
	@JSONField(name="org_name")
	private String organizationName;
	/** 学院编码 */
	@JSONField(name="dep_code")
	private String departmentCode;
	/** 活动类型 */
	@JSONField(name="activity_type")
	private String activityType;
	/** 活动模式 */
	@JSONField(name="mode")
	private String activityMode;
	/** 活动行为 */
	@JSONField(name="action")
	private String activityAction;
	/** 活动来源 */
//	@JSONField(name="user_id")
	private String activitySource;
	/** 开始时间 */
	@JSONField(name="start_time")
	private String startTime;
	/** 结束时间 */
	@JSONField(name="end_time")
	private String endTime;
	/** 时间戳 */
	@JSONField(name="created_at")
	private String ts;
	/** 用户编码 */
	@JSONField(name="user_no")
	private String userNumber;
	/** 用户姓名 */
	@JSONField(name="user_name")
	private String userName;
	/** 参与角色 */
	@JSONField(name="enrollment_role")
	private String activityEnrollmentRole;
	/** 日期 */
//	@JSONField(name="user_id")
	private String day;

	@JSONField(name="created_at")
	private String createdAt;

}
