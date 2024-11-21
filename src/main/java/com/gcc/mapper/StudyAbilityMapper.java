package com.gcc.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.gcc.pojo.KnowledgeComplication;
import com.gcc.pojo.StudentVitality;
import com.gcc.pojo.StudyAbility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gcc
 * @since 2023-01-03
 */
public interface StudyAbilityMapper extends BaseMapper<StudyAbility> {

    @DS("slave_2")
    List<StudyAbility> getAbilityByUserId(@Param("student_id") String student_id, @Param("course_id") String course_id);

    @DS("slave_2")
    List<KnowledgeComplication> queryCourseKnowledge();

    @DS("slave_2")
    List<KnowledgeComplication> queryStuCourseKnowledge();

    @DS("slave_2")
    List<StudyAbility> getAllAbility();

}
