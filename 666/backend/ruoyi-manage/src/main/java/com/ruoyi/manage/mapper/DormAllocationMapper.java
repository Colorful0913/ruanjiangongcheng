package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.DormAllocation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 住宿分配Mapper接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Mapper
public interface DormAllocationMapper 
{
    /**
     * 根据房间ID统计当前在住人数
     *
     * @param roomId 房间ID
     * @return 在住人数
     */
    public Long countActiveByRoomId(Long roomId);


    /**
     * 根据房间ID统计入住人数
     *
     * @param roomId 房间ID
     * @return 入住人数
     */
    public Long countByRoomId(Long roomId);


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
     * 删除住宿分配
     * 
     * @param allocationId 住宿分配主键
     * @return 结果
     */
    public int deleteDormAllocationByAllocationId(Long allocationId);

    /**
     * 批量删除住宿分配
     * 
     * @param allocationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDormAllocationByAllocationIds(Long[] allocationIds);
}
