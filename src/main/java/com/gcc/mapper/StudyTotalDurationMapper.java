package com.gcc.mapper;

import com.gcc.pojo.StudyTotalDuration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.StudyTotalEngagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2023-02-06
 */
public interface StudyTotalDurationMapper extends BaseMapper<StudyTotalDuration> {

    List<StudyTotalDuration> getTotalDurationByUserId(@Param("student_id") String id);
}
