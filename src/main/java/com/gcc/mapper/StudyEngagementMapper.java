package com.gcc.mapper;

import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyEngagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface StudyEngagementMapper extends BaseMapper<StudyEngagement> {
    List<StudyEngagement> getEngagementByUserId(@Param("student_id") String student_id, @Param("course_id") String course_id);
}
