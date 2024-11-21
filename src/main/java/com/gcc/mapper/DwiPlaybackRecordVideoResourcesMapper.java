package com.gcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.*;
import com.gcc.pojo.vo.VitalityVO;

import java.util.List;

/**
 * 观看视频 数据层
 *
 * @author dev
 * @date 2023-03-15
 */
public interface DwiPlaybackRecordVideoResourcesMapper extends BaseMapper<DwiPlaybackRecordVideoResources> {
    /**
     * 查询观看视频信息
     *
     * @param id 观看视频ID
     * @return 观看视频信息
     */
    public DwiPlaybackRecordVideoResources selectDwiPlaybackRecordVideoResourcesById(String id);

    /**
     * 查询观看视频列表
     *
     * @param dwiPlaybackRecordVideoResources 观看视频信息
     * @return 观看视频集合
     */
    public List<DwiPlaybackRecordVideoResources> selectDwiPlaybackRecordVideoResourcesList(DwiPlaybackRecordVideoResources dwiPlaybackRecordVideoResources);

    /**
     * 新增观看视频
     *
     * @param dwiPlaybackRecordVideoResources 观看视频信息
     * @return 结果
     */
    public int insertDwiPlaybackRecordVideoResources(DwiPlaybackRecordVideoResources dwiPlaybackRecordVideoResources);

    /**
     * 修改观看视频
     *
     * @param dwiPlaybackRecordVideoResources 观看视频信息
     * @return 结果
     */
    public int updateDwiPlaybackRecordVideoResources(DwiPlaybackRecordVideoResources dwiPlaybackRecordVideoResources);

    /**
     * 删除观看视频
     *
     * @param id 观看视频ID
     * @return 结果
     */
    public int deleteDwiPlaybackRecordVideoResourcesById(String id);

    /**
     * 批量删除观看视频
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDwiPlaybackRecordVideoResourcesByIds(String[] ids);


    public int batchInsert(List<DwiPlaybackRecordVideoResources> list);

    public int sync_study_total_duration();

    public int sync_student_course_score(List<StudentCourseScore> list);

    public int sync_student_course_count_score();

    public int sync_user_personal_data(List<UserPersonalData> list) throws Exception;

    public int syncTeacherCommentScore(List<TeacherCommentScore> list);

    public int syncUserWordcloud1(List<UserWordcloud1> list);


    public List<VitalityVO> queryLearningCount();

    public int syncStudentTotalProgress();

    public int syncStudyEngagement();

    public int syncStudyAbility(List<StudyAbility> list);

    public List<WordcloudJudgment> queryCloudWords();

}