package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.DormRepair;

/**
 * 维修报修Service接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IDormRepairService 
{
    /**
     * 查询维修报修
     * 
     * @param repairId 维修报修主键
     * @return 维修报修
     */
    public DormRepair selectDormRepairByRepairId(Long repairId);

    /**
     * 查询维修报修列表
     * 
     * @param dormRepair 维修报修
     * @return 维修报修集合
     */
    public List<DormRepair> selectDormRepairList(DormRepair dormRepair);

    /**
     * 新增维修报修
     * 
     * @param dormRepair 维修报修
     * @return 结果
     */
    public int insertDormRepair(DormRepair dormRepair);

    /**
     * 修改维修报修
     * 
     * @param dormRepair 维修报修
     * @return 结果
     */
    public int updateDormRepair(DormRepair dormRepair);

    /**
     * 批量删除维修报修
     * 
     * @param repairIds 需要删除的维修报修主键集合
     * @return 结果
     */
    public int deleteDormRepairByRepairIds(Long[] repairIds);

    /**
     * 删除维修报修信息
     * 
     * @param repairId 维修报修主键
     * @return 结果
     */
    public int deleteDormRepairByRepairId(Long repairId);
}
