package com.gcc.mapper;

import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyEffect;
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
public interface StudyEffectMapper extends BaseMapper<StudyEffect> {
    List<StudyEffect> getEffectByUserId(@Param("student_id") String student_id, @Param("course_id") String course_id);
}
