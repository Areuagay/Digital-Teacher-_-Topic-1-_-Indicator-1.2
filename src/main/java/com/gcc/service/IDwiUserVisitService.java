package com.gcc.service;

import com.gcc.pojo.DwiUserVisit;

import java.util.List;

/**
 * 用户访问 服务层
 * 
 * @author dev
 * @date 2023-03-15
 */
public interface IDwiUserVisitService 
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

	
}
