package com.gcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcc.pojo.DwiLearningActivitys;
import com.gcc.pojo.DwiUserVisit;

import java.util.List;

/**
 * 用户访问 数据层
 * 
 * @author dev
 * @date 2023-03-15
 */
public interface DwiUserVisitMapper extends BaseMapper<DwiUserVisit>
{
	/**
     * 查询用户访问信息
     * 
     * @param activityId 用户访问ID
     * @return 用户访问信息
     */
	public DwiUserVisit selectDwiUserVisitById(String activityId);
	
	/**
     * 查询用户访问列表
     * 
     * @param dwiUserVisit 用户访问信息
     * @return 用户访问集合
     */
	public List<DwiUserVisit> selectDwiUserVisitList(DwiUserVisit dwiUserVisit);
	
	/**
     * 新增用户访问
     * 
     * @param dwiUserVisit 用户访问信息
     * @return 结果
     */
	public int insertDwiUserVisit(DwiUserVisit dwiUserVisit);
	
	/**
     * 修改用户访问
     * 
     * @param dwiUserVisit 用户访问信息
     * @return 结果
     */
	public int updateDwiUserVisit(DwiUserVisit dwiUserVisit);
	
	/**
     * 删除用户访问
     * 
     * @param activityId 用户访问ID
     * @return 结果
     */
	public int deleteDwiUserVisitById(String activityId);
	
	/**
     * 批量删除用户访问
     * 
     * @param activityIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDwiUserVisitByIds(String[] activityIds);

	public int batchInsert(List<DwiUserVisit> list);
	
}