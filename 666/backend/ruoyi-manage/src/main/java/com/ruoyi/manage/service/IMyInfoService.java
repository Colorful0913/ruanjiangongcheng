package com.ruoyi.manage.service;

import com.ruoyi.manage.domain.DormStudent;
import com.ruoyi.manage.domain.DormAllocation;
import java.util.Map;

/**
 * 我的信息服务接口
 *
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IMyInfoService
{
    /**
     * 查询我的学生信息
     *
     * @return 学生信息扩展
     */
    public DormStudent selectMyStudentInfo();

    /**
     * 查询我的住宿分配信息
     *
     * @return 住宿分配
     */
    public DormAllocation selectMyAllocationInfo();

    /**
     * 查询我的完整信息
     *
     * @return 完整信息
     */
    public Map<String, Object> selectMyFullInfo();
}