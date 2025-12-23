package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.DormRepairMapper;
import com.ruoyi.manage.domain.DormRepair;
import com.ruoyi.manage.service.IDormRepairService;

/**
 * 维修报修Service业务层处理
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class DormRepairServiceImpl implements IDormRepairService 
{
    @Autowired
    private DormRepairMapper dormRepairMapper;

    /**
     * 查询维修报修
     * 
     * @param repairId 维修报修主键
     * @return 维修报修
     */
    @Override
    public DormRepair selectDormRepairByRepairId(Long repairId)
    {
        return dormRepairMapper.selectDormRepairByRepairId(repairId);
    }

    /**
     * 查询维修报修列表
     * 
     * @param dormRepair 维修报修
     * @return 维修报修
     */
    @Override
    public List<DormRepair> selectDormRepairList(DormRepair dormRepair)
    {
        return dormRepairMapper.selectDormRepairList(dormRepair);
    }

    /**
     * 新增维修报修
     * 
     * @param dormRepair 维修报修
     * @return 结果
     */
    @Override
    public int insertDormRepair(DormRepair dormRepair)
    {
        dormRepair.setCreateTime(DateUtils.getNowDate());
        return dormRepairMapper.insertDormRepair(dormRepair);
    }

    /**
     * 修改维修报修
     * 
     * @param dormRepair 维修报修
     * @return 结果
     */
    @Override
    public int updateDormRepair(DormRepair dormRepair)
    {
        dormRepair.setUpdateTime(DateUtils.getNowDate());
        return dormRepairMapper.updateDormRepair(dormRepair);
    }

    /**
     * 批量删除维修报修
     * 
     * @param repairIds 需要删除的维修报修主键
     * @return 结果
     */
    @Override
    public int deleteDormRepairByRepairIds(Long[] repairIds)
    {
        return dormRepairMapper.deleteDormRepairByRepairIds(repairIds);
    }

    /**
     * 删除维修报修信息
     * 
     * @param repairId 维修报修主键
     * @return 结果
     */
    @Override
    public int deleteDormRepairByRepairId(Long repairId)
    {
        return dormRepairMapper.deleteDormRepairByRepairId(repairId);
    }
}
