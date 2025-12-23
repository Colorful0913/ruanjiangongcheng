package com.ruoyi.manage.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.service.IMyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.DormRepairMapper;
import com.ruoyi.manage.domain.DormRepair;
import com.ruoyi.manage.service.IMyRepairService;

/**
 * 我的报修服务实现类
 *
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class MyRepairServiceImpl implements IMyRepairService
{
    @Autowired
    private DormRepairMapper dormRepairMapper;
    // 在已有代码的基础上添加
    @Autowired
    private IMyInfoService myInfoService;



//    /**
//     * 查询我的报修
//     */
//    @Override
//    public DormRepair selectMyRepairByRepairId(Long repairId)
//    {
//        Long userId = SecurityUtils.getUserId();
//        DormRepair query = new DormRepair();
//        query.setRepairId(repairId);
//        query.setReportUserId(userId);
//        // 根据报修ID和当前用户ID查询报修记录
//        return dormRepairMapper.selectDormRepairList(query).stream()
//                .findFirst()
//                .orElse(null);
//    }


    /**
     * 查询我的报修
     */
    @Override
    public DormRepair selectMyRepairByRepairId(Long repairId)
    {
        // 直接根据repairId查询记录
        DormRepair repair = dormRepairMapper.selectDormRepairByRepairId(repairId);

        // 获取当前用户ID
        Long userId = SecurityUtils.getUserId();

        // 验证查询到的记录是否属于当前用户
        if (repair != null && repair.getReportUserId().equals(userId)) {
            return repair;
        }

        // 如果记录不存在或不属于当前用户，返回null
        return null;
    }



    /**
     * 查询我的报修列表
     */
    @Override
    public List<DormRepair> selectMyRepairList(DormRepair dormRepair)
    {
        Long userId = SecurityUtils.getUserId();
        dormRepair.setReportUserId(userId); // 设置查询条件为当前用户的报修
        return dormRepairMapper.selectDormRepairList(dormRepair);
    }

//    /**
//     * 新增我的报修
//     */
//    @Override
//    public int insertMyRepair(DormRepair dormRepair)
//    {
//        dormRepair.setCreateTime(DateUtils.getNowDate());
//        return dormRepairMapper.insertDormRepair(dormRepair);
//    }

    /**
     * 新增我的报修
     */
    @Override
    public int insertMyRepair(DormRepair dormRepair)
    {
        // 如果roomId为空，则尝试从当前用户的住宿信息中获取
        if (dormRepair.getRoomId() == null) {
            DormAllocation allocation = myInfoService.selectMyAllocationInfo();
            if (allocation != null) {
                dormRepair.setRoomId(allocation.getRoomId());
            } else {
                throw new ServiceException("无法获取当前用户的房间信息，请确认您已分配宿舍");
            }
        }

        dormRepair.setCreateTime(DateUtils.getNowDate());
        return dormRepairMapper.insertDormRepair(dormRepair);
    }





    /**
     * 修改我的报修
     */
    @Override
    public int updateMyRepair(DormRepair dormRepair)
    {
        dormRepair.setUpdateTime(DateUtils.getNowDate());
        return dormRepairMapper.updateDormRepair(dormRepair);
    }

    /**
     * 批量删除我的报修
     */
    @Override
    public int deleteMyRepairByRepairIds(Long[] repairIds)
    {
        Long userId = SecurityUtils.getUserId();
        // 只删除当前用户的报修记录
        int count = 0;
        for (Long repairId : repairIds) {
            DormRepair repair = selectMyRepairByRepairId(repairId);
            if (repair != null) {
                count += dormRepairMapper.deleteDormRepairByRepairId(repairId);
            }
        }
        return count;
    }

    /**
     * 删除我的报修信息
     */
    @Override
    public int deleteMyRepairByRepairId(Long repairId)
    {
        // 验证报修记录属于当前用户后再删除
        DormRepair repair = selectMyRepairByRepairId(repairId);
        if (repair != null) {
            return dormRepairMapper.deleteDormRepairByRepairId(repairId);
        }
        return 0;
    }
}