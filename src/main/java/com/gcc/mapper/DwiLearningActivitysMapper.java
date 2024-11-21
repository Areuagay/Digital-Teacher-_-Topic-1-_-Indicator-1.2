package com.gcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.DwiLearningActivitys;
import com.gcc.pojo.DwiPlaybackRecordVideoResources;

import java.util.List;

/**
 * 学习活动 数据层
 * 
 * @author dev
 * @date 2023-03-15
 */
public interface DwiLearningActivitysMapper extends BaseMapper<DwiLearningActivitys>
{
	/**
     * 查询学习活动信息
     * 
     * @param activityId 学习活动ID
     * @return 学习活动信息
     */
	public DwiLearningActivitys selectDwiLearningActivitysById(String activityId);
	
	/**
     * 查询学习活动列表
     * 
     * @param dwiLearningActivitys 学习活动信息
     * @return 学习活动集合
     */
	public List<DwiLearningActivitys> selectDwiLearningActivitysList(DwiLearningActivitys dwiLearningActivitys);
	
	/**
     * 新增学习活动
     * 
     * @param dwiLearningActivitys 学习活动信息
     * @return 结果
     */
	public int insertDwiLearningActivitys(DwiLearningActivitys dwiLearningActivitys);
	
	/**
     * 修改学习活动
     * 
     * @param dwiLearningActivitys 学习活动信息
     * @return 结果
     */
	public int updateDwiLearningActivitys(DwiLearningActivitys dwiLearningActivitys);
	
	/**
     * 删除学习活动
     * 
     * @param activityId 学习活动ID
     * @return 结果
     */
	public int deleteDwiLearningActivitysById(String activityId);
	
	/**
     * 批量删除学习活动
     * 
     * @param activityIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDwiLearningActivitysByIds(String[] activityIds);


	public int batchInsert(List<DwiLearningActivitys> list);
	
}