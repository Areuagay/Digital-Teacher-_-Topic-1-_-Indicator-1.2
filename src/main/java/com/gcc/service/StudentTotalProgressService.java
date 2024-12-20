package com.gcc.service;

import com.gcc.pojo.StudentTotalProgress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
public interface StudentTotalProgressService extends IService<StudentTotalProgress> {
public List<StudentTotalProgress> getTotalProgressyByUserId(String id);
}
