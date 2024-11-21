package com.gcc.mapper;

import com.gcc.pojo.StudyTotalEngagement;
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
public interface StudyTotalEngagementMapper extends BaseMapper<StudyTotalEngagement> {
List<StudyTotalEngagement>getTotalEngagementByUserId(@Param("student_id") String id);
}
