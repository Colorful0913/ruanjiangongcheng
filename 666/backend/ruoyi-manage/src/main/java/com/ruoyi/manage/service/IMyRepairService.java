package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.DormRepair;

/**
 * 我的报修Service接口
 *
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IMyRepairService
{
    /**
     * 查询我的报修
     *
     * @param repairId 报修ID
     * @return 报修信息
     */
    public DormRepair selectMyRepairByRepairId(Long repairId);

    /**
     * 查询我的报修列表
     *
     * @param dormRepair 报修信息
     * @return 报修集合
     */
    public List<DormRepair> selectMyRepairList(DormRepair dormRepair);

    /**
     * 新增我的报修
     *
     * @param dormRepair 报修信息
     * @return 结果
     */
    public int insertMyRepair(DormRepair dormRepair);

    /**
     * 修改我的报修
     *
     * @param dormRepair 报修信息
     * @return 结果
     */
    public int updateMyRepair(DormRepair dormRepair);

    /**
     * 批量删除我的报修
     *
     * @param repairIds 需要删除的报修ID
     * @return 结果
     */
    public int deleteMyRepairByRepairIds(Long[] repairIds);

    /**
     * 删除我的报修信息
     *
     * @param repairId 报修ID
     * @return 结果
     */
    public int deleteMyRepairByRepairId(Long repairId);
}