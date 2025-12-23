package com.ruoyi.manage.service.impl;

import com.ruoyi.manage.domain.DormBuilding;
import com.ruoyi.manage.domain.DormRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.manage.mapper.DormStudentMapper;
import com.ruoyi.manage.mapper.DormAllocationMapper;
import com.ruoyi.manage.domain.DormStudent;
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.service.IMyInfoService;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.ruoyi.manage.service.IDormRoomService;
import com.ruoyi.manage.service.IDormBuildingService;


/**
 * 我的信息服务实现类
 *
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class MyInfoServiceImpl implements IMyInfoService
{
    @Autowired
    private DormStudentMapper dormStudentMapper;

    @Autowired
    private DormAllocationMapper dormAllocationMapper;

    @Autowired
    private IDormRoomService dormRoomService;

    @Autowired
    private IDormBuildingService dormBuildingService;


    /**
     * 查询我的学生信息
     */
    @Override
    public DormStudent selectMyStudentInfo()
    {
        Long userId = SecurityUtils.getUserId();
        DormStudent query = new DormStudent();
        query.setUserId(userId);
        // 根据当前登录用户的userId查询学生信息
        return dormStudentMapper.selectDormStudentList(query).stream()
                .findFirst()
                .orElse(null);
    }

//    /**
//     * 查询我的住宿分配信息
//     */
//    @Override
//    public DormAllocation selectMyAllocationInfo()
//    {
//        DormStudent student = selectMyStudentInfo();
//        if (student == null) {
//            return null;
//        }
//
//        DormAllocation query = new DormAllocation();
//        query.setStudentId(student.getStudentId());
//        query.setStatus("0"); // 只查询入住中的分配
//        // 根据学生ID查询住宿分配信息
//        return dormAllocationMapper.selectDormAllocationList(query).stream()
//                .findFirst()
//                .orElse(null);
//    }


    /**
     * 查询我的住宿分配信息
     */
    @Override
    public DormAllocation selectMyAllocationInfo() {
        DormStudent student = selectMyStudentInfo();
        if (student == null) {
            return null;
        }

        DormAllocation query = new DormAllocation();
        query.setStudentId(student.getStudentId());
        query.setStatus("0"); // 只查询入住中的分配
        // 根据学生ID查询住宿分配信息
        List<DormAllocation> list = dormAllocationMapper.selectDormAllocationList(query);

        if (!list.isEmpty()) {
            DormAllocation allocation = list.get(0);
            // 关联查询房间号和楼栋编号
            if (allocation.getRoomId() != null) {
                DormRoom room = dormRoomService.selectDormRoomByRoomId(allocation.getRoomId());
                if (room != null) {
                    allocation.setRoomNumber(room.getRoomNumber());
                    // 查询楼栋信息
                    DormBuilding building = dormBuildingService.selectDormBuildingByBuildingId(room.getBuildingId());
                    if (building != null) {
                        allocation.setBuildingCode(building.getBuildingCode());
                    }
                }
            }
            return allocation;
        }

        return null;
    }




    /**
     * 查询我的完整信息
     */
    @Override
    public Map<String, Object> selectMyFullInfo()
    {
        Map<String, Object> result = new HashMap<>();

        DormStudent student = selectMyStudentInfo();
        DormAllocation allocation = selectMyAllocationInfo();

        result.put("studentInfo", student);
        result.put("allocationInfo", allocation);

        return result;
    }
}