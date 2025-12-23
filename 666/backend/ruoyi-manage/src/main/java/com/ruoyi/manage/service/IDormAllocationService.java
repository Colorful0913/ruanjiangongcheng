package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.DormAllocation;

/**
 * 住宿分配Service接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IDormAllocationService 
{
    /**
     * 查询住宿分配
     * 
     * @param allocationId 住宿分配主键
     * @return 住宿分配
     */
    public DormAllocation selectDormAllocationByAllocationId(Long allocationId);

    /**
     * 查询住宿分配列表
     * 
     * @param dormAllocation 住宿分配
     * @return 住宿分配集合
     */
    public List<DormAllocation> selectDormAllocationList(DormAllocation dormAllocation);

    /**
     * 新增住宿分配
     * 
     * @param dormAllocation 住宿分配
     * @return 结果
     */
    public int insertDormAllocation(DormAllocation dormAllocation);

    /**
     * 修改住宿分配
     * 
     * @param dormAllocation 住宿分配
     * @return 结果
     */
    public int updateDormAllocation(DormAllocation dormAllocation);

    /**
     * 批量删除住宿分配
     * 
     * @param allocationIds 需要删除的住宿分配主键集合
     * @return 结果
     */
    public int deleteDormAllocationByAllocationIds(Long[] allocationIds);

    /**
     * 删除住宿分配信息
     * 
     * @param allocationId 住宿分配主键
     * @return 结果
     */
    public int deleteDormAllocationByAllocationId(Long allocationId);
}
