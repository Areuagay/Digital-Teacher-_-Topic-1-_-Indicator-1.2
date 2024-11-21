package com.gcc.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 观看视频表 dwi_playback_record_video_resources
 * 
 * @author dev
 * @date 2023-03-15
 */
@Data
public class DwiPlaybackRecordVideoResources
{
	private static final long serialVersionUID = 1L;
	
	/** 自增长主键 */
	private Long id;
	/** 用户Id */
	@JSONField(name="user_id")
	private String userNumber;
	/** 用户编码 */
	@JSONField(name="user_no")
	private String userCode;
	/** 机构Id */
	@JSONField(name="org_id")
	private String organizationId;
	/** 机构代码 */
	@JSONField(name="org_code")
	private String organizationCode;
	/** 学院Id */
	@JSONField(name="dep_id")
	private String departmentId;
	/** 学院编码 */
	@JSONField(name="dep_code")
	private String departmentCode;
	/** 课程Id */
	@JSONField(name="course_id")
	private String courseId;
	/** 课程编码 */
	@JSONField(name="course_code")
	private String courseCode;
	/** 课程名称 */
	@JSONField(name="course_name")
	private String courseName;
	/** 章节Id */
	@JSONField(name="module_id")
	private String moduleId;
	/** 单元Id */
	@JSONField(name="syllabus_id")
	private String syllabusId;
	/** 学习活动Id */
	@JSONField(name="activity_id")
	private String activityId;
	/** 浏览时长 */
	@JSONField(name="duration")
	private String duration;
	/** 是否来自移动端 */
//	@JSONField(name="course_code")
	private String isMobile;
	/** 是否是老师 */
	@JSONField(name="is_teacher")
	private String isTeacher;
	/** 学习活动类型 */
	@JSONField(name="action_type")
	private String actionType;
	/** 资源Id */
	@JSONField(name="upload_id")
	private String uploadId;
	/** 观看开始时间点 */
	@JSONField(name="start_at")
	private String startTime;
	/** 观看结束时间点 */
	@JSONField(name="end_at")
	private String endTime;

	@JSONField(name="created_at")
	private String createdAt;




}
