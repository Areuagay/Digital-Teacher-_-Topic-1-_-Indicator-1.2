package com.gcc.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.*;
import com.gcc.pojo.vo.VitalityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
@DS("slave_1")
public interface DwiScoreMapper extends BaseMapper<DwiScore> {

    @DS("slave_1")
    public List<StudentCourseScore> query_student_course_score();

    @DS("slave_1")
    public DwiCourse queryCourse(String coursecode);

    @DS("slave_1")
    List<UserPersonalData> queryUserPersonalData(@Param("page") Integer page,@Param("limit") Integer limit);

    @DS("slave_1")
    List<TeacherCommentScore> queryTeacherCommentScore();

    @DS("slave_1")
    public List<StudyEffect> queryHomeworkAvgScore();

    @DS("slave_1")
    public List<VitalityVO> queryReplyCount();

    @DS("slave_1")
    public List<VitalityVO> queryHomework();



    @DS("slave_1")
    public List<DwiCourse> queryAllCourse();

    @DS("slave_1")
    public List<StudentTotalProgress> queryStudentTotalProgress();


    @DS("slave_1")
    public List<DwiCourse> queryAllStudentCourse();

    @DS("slave_1")
    public List<DwiCourse> queryCourseByStudentId();

}
