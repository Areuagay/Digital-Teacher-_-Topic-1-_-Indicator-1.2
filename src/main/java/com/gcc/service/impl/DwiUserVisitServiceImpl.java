package com.gcc.service.impl;

import java.util.List;

import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.pojo.DwiUserVisit;
import com.gcc.service.IDwiUserVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 用户访问 服务层实现
 * 
 * @author dev
 * @date 2023-03-15
 */
@Service
public class DwiUserVisitServiceImpl implements IDwiUserVisitService
{
	@Autowired
	private DwiUserVisitMapper dwiUserVisitMapper;

	/**
     * 查询用户访问信息
     * 
     * @param activityId 用户访问ID
     * @return 用户访问信息
     */
    @Override
	public DwiUserVisit selectDwiUserVisitById(String activityId)
	{
	    return dwiUserVisitMapper.selectDwiUserVisitById(activityId);
	}
	
	/**
     * 查询用户访问列表
     * 
     * @param dwiUserVisit 用户访问信息
     * @return 用户访问集合
     */
	@Override
	public List<DwiUserVisit> selectDwiUserVisitList(DwiUserVisit dwiUserVisit)
	{
	    return dwiUserVisitMapper.selectDwiUserVisitList(dwiUserVisit);
	}
	
    /**
     * 新增用户访问
     * 
     * @param dwiUserVisit 用户访问信息
     * @return 结果
     */
	@Override
	public int insertDwiUserVisit(DwiUserVisit dwiUserVisit)
	{
	    return dwiUserVisitMapper.insertDwiUserVisit(dwiUserVisit);
	}
	
	/**
     * 修改用户访问
     * 
     * @param dwiUserVisit 用户访问信息
     * @return 结果
     */
	@Override
	public int updateDwiUserVisit(DwiUserVisit dwiUserVisit)
	{
	    return dwiUserVisitMapper.updateDwiUserVisit(dwiUserVisit);
	}

	
}
