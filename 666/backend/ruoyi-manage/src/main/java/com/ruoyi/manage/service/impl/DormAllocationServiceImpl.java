package com.ruoyi.manage.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manage.domain.DormRoom;
import com.ruoyi.manage.domain.DormStudent;
import com.ruoyi.manage.service.IDormRoomService;
import com.ruoyi.manage.service.IDormStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.DormAllocationMapper;
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.service.IDormAllocationService;
import java.util.Date;
/**
 * 住宿分配Service业务层处理
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class DormAllocationServiceImpl implements IDormAllocationService 
{
    @Autowired
    private DormAllocationMapper dormAllocationMapper;
    // 在类的字段部分添加
    @Autowired
    private IDormRoomService dormRoomService;
    // 需要添加依赖注入
    @Autowired
    private IDormStudentService dormStudentService;


    /**
     * 查询住宿分配
     * 
     * @param allocationId 住宿分配主键
     * @return 住宿分配
     */
    @Override
    public DormAllocation selectDormAllocationByAllocationId(Long allocationId)
    {
        return dormAllocationMapper.selectDormAllocationByAllocationId(allocationId);
    }

    /**
     * 查询住宿分配列表
     * 
     * @param dormAllocation 住宿分配
     * @return 住宿分配
     */
    @Override
    public List<DormAllocation> selectDormAllocationList(DormAllocation dormAllocation)
    {
        return dormAllocationMapper.selectDormAllocationList(dormAllocation);
    }

    /**
     * 新增住宿分配
     * 
     * @param dormAllocation 住宿分配
     * @return 结果
     */
//    @Override
//    public int insertDormAllocation(DormAllocation dormAllocation)
//    {
//        dormAllocation.setCreateTime(DateUtils.getNowDate());
//        return dormAllocationMapper.insertDormAllocation(dormAllocation);
//    }

//    @Override
//    public int insertDormAllocation(DormAllocation dormAllocation) {
//        dormAllocation.setCreateTime(DateUtils.getNowDate());
//        int result = dormAllocationMapper.insertDormAllocation(dormAllocation);
//
//        // 更新房间的已住人数和状态
//        if (result > 0) {
//            updateRoomStatusAndCount(dormAllocation.getRoomId());
//        }
//
//        return result;
//    }

//    @Override
//    public int insertDormAllocation(DormAllocation dormAllocation) {
//        dormAllocation.setCreateTime(DateUtils.getNowDate());
//        int result = dormAllocationMapper.insertDormAllocation(dormAllocation);
//
//        // 确保在插入成功后更新房间状态
//        if (result > 0 && dormAllocation.getRoomId() != null) {
//            updateRoomStatusAndCount(dormAllocation.getRoomId());
//        }
//
//        return result;
//    }

// 在 insertDormAllocation 方法中添加逻辑处理
//    @Override
//    public int insertDormAllocation(DormAllocation dormAllocation) {
//        // 根据学生学号查找学生ID（如果前端传的是学号而不是ID）
//        if (dormAllocation.getStudentId() == null && dormAllocation.getStudentNumber() != null) {
//            DormStudent student = dormStudentService.selectDormStudentByStudentNumber(dormAllocation.getStudentNumber());
//            if (student != null) {
//                dormAllocation.setStudentId(student.getStudentId());
//            }
//        }
//
//        // 设置入住日期为当前日期
//        dormAllocation.setCheckInDate(new Date());
//
//        // 设置状态为入住中
//        dormAllocation.setStatus("0");
//
//        // 自动生成床位编号
//        dormAllocation.setBedNumber(generateBedNumber(dormAllocation.getRoomId()));
//
//        dormAllocation.setCreateTime(DateUtils.getNowDate());
//        return dormAllocationMapper.insertDormAllocation(dormAllocation);
//    }


    @Override
    public int insertDormAllocation(DormAllocation dormAllocation) {
        // 如果传入的是学生学号而不是学生ID，则需要转换
        if (dormAllocation.getStudentId() == null && dormAllocation.getStudentNumber() != null) {
            // 根据学号查找学生信息
            DormStudent student = dormStudentService.selectDormStudentByStudentNumber(dormAllocation.getStudentNumber());
            if (student != null) {
                // 设置学生ID
                dormAllocation.setStudentId(student.getStudentId());
                // 可以同时设置学生姓名（如果需要）
                dormAllocation.setStudentName(student.getRemark());
            } else {
                // 如果找不到对应的学生，抛出异常
                throw new ServiceException("找不到学号为 " + dormAllocation.getStudentNumber() + " 的学生信息");
            }
        }

        // 验证学生ID是否存在
        if (dormAllocation.getStudentId() == null) {
            throw new ServiceException("学生信息不能为空");
        }

        // 验证学生ID是否有效
        DormStudent student = dormStudentService.selectDormStudentByStudentId(dormAllocation.getStudentId());
        if (student == null) {
            throw new ServiceException("学生ID无效");
        }

        // 检查该学生是否已有入住状态的分配记录
        DormAllocation query = new DormAllocation();
        query.setStudentId(dormAllocation.getStudentId());
        query.setStatus("0"); // 入住状态
        List<DormAllocation> existingAllocations = dormAllocationMapper.selectDormAllocationList(query);
        if (!existingAllocations.isEmpty()) {
            DormStudent existingStudent = dormStudentService.selectDormStudentByStudentId(dormAllocation.getStudentId());
            String studentInfo = existingStudent != null ? (existingStudent.getStudentNumber() + "(" + existingStudent.getRemark() + ")") : dormAllocation.getStudentId().toString();
            throw new ServiceException("学生 " + studentInfo + " 已有入住中的宿舍分配记录，不能重复分配");
        }


        // 其他原有逻辑...
        dormAllocation.setCheckInDate(new Date());
        dormAllocation.setStatus("0"); // 入住中

        // 自动生成床位编号
        dormAllocation.setBedNumber(generateBedNumber(dormAllocation.getRoomId()));

        dormAllocation.setCreateTime(DateUtils.getNowDate());
//        return dormAllocationMapper.insertDormAllocation(dormAllocation);
        int result = dormAllocationMapper.insertDormAllocation(dormAllocation);

        // 新增成功后，更新房间的已住人数和状态
        if (result > 0 && dormAllocation.getRoomId() != null) {
            updateRoomStatusAndCount(dormAllocation.getRoomId());
        }

        return result;
    }






    // 添加生成床位编号的方法
    private String generateBedNumber(Long roomId) {
        // 查询该房间已分配的床位
        DormAllocation query = new DormAllocation();
        query.setRoomId(roomId);
        List<DormAllocation> allocations = dormAllocationMapper.selectDormAllocationList(query);

        // 简单的床位编号生成逻辑（A, B, C, D...）
        char bedChar = (char)('A' + allocations.size());
        return String.valueOf(bedChar);
    }




    /**
     * 修改住宿分配
     * 
     * @param dormAllocation 住宿分配
     * @return 结果
     */
//    @Override
//    public int updateDormAllocation(DormAllocation dormAllocation)
//    {
//        dormAllocation.setUpdateTime(DateUtils.getNowDate());
//        return dormAllocationMapper.updateDormAllocation(dormAllocation);
//    }

//    @Override
//    public int updateDormAllocation(DormAllocation dormAllocation) {
//        // 先查询原始分配信息
//        DormAllocation oldAllocation = dormAllocationMapper.selectDormAllocationByAllocationId(dormAllocation.getAllocationId());
//
//        dormAllocation.setUpdateTime(DateUtils.getNowDate());
//        int result = dormAllocationMapper.updateDormAllocation(dormAllocation);
//
//        // 更新房间的已住人数和状态
//        if (result > 0) {
//            // 更新原始房间（如果房间ID改变了）
//            if (oldAllocation != null && !oldAllocation.getRoomId().equals(dormAllocation.getRoomId())) {
//                updateRoomStatusAndCount(oldAllocation.getRoomId());
//            }
//            // 更新新房间
//            updateRoomStatusAndCount(dormAllocation.getRoomId());
//        }
//
//        return result;
//    }

//    @Override
//    public int updateDormAllocation(DormAllocation dormAllocation) {
//        // 先查询原始分配信息
//        DormAllocation oldAllocation = dormAllocationMapper.selectDormAllocationByAllocationId(dormAllocation.getAllocationId());
//
//        dormAllocation.setUpdateTime(DateUtils.getNowDate());
//        int result = dormAllocationMapper.updateDormAllocation(dormAllocation);
//
//        // 更新房间的已住人数和状态
//        if (result > 0) {
//            // 更新原始房间（如果房间ID改变了）
//            if (oldAllocation != null && !oldAllocation.getRoomId().equals(dormAllocation.getRoomId())) {
//                updateRoomStatusAndCount(oldAllocation.getRoomId());
//            }
//            // 更新新房间
//            if (dormAllocation.getRoomId() != null) {
//                updateRoomStatusAndCount(dormAllocation.getRoomId());
//            }
//        }
//
//        return result;
//    }



    @Override
    public int updateDormAllocation(DormAllocation dormAllocation) {
        // 先查询原始分配信息
        DormAllocation oldAllocation = dormAllocationMapper.selectDormAllocationByAllocationId(dormAllocation.getAllocationId());


        // 如果状态从非入住改为入住，需要检查该学生是否已经有入住状态的记录
        if ("0".equals(dormAllocation.getStatus())) {
            DormAllocation query = new DormAllocation();
            query.setStudentId(dormAllocation.getStudentId());
            query.setStatus("0");
            List<DormAllocation> existingAllocations = dormAllocationMapper.selectDormAllocationList(query);

            // 如果已存在入住记录且不是当前这条记录，则不允许更新
            for (DormAllocation allocation : existingAllocations) {
                if (!allocation.getAllocationId().equals(dormAllocation.getAllocationId())) {
                    DormStudent student = dormStudentService.selectDormStudentByStudentId(dormAllocation.getStudentId());
                    String studentInfo = student != null ? (student.getStudentNumber() + "(" + student.getRemark() + ")") : dormAllocation.getStudentId().toString();
                    throw new ServiceException("学生 " + studentInfo + " 已有入住中的宿舍分配记录，不能重复分配");
                }
            }
        }



        // 检查房间是否发生变化
        if (oldAllocation != null && !oldAllocation.getRoomId().equals(dormAllocation.getRoomId())) {
            // 房间发生变化，重新生成床位编号
            dormAllocation.setBedNumber(generateBedNumber(dormAllocation.getRoomId()));
        }

        dormAllocation.setUpdateTime(DateUtils.getNowDate());


        int result = dormAllocationMapper.updateDormAllocation(dormAllocation);

        // 更新房间的已住人数和状态
        if (result > 0) {
            // 更新原始房间（如果房间ID改变了）
            if (oldAllocation != null && !oldAllocation.getRoomId().equals(dormAllocation.getRoomId())) {
                updateRoomStatusAndCount(oldAllocation.getRoomId());
            }
            // 更新新房间
            if (dormAllocation.getRoomId() != null) {
                updateRoomStatusAndCount(dormAllocation.getRoomId());
            }
        }

        return result;
    }












    /**
     * 批量删除住宿分配
     * 
     * @param allocationId 需要删除的住宿分配主键
     * @return 结果
     */
//    @Override
//    public int deleteDormAllocationByAllocationIds(Long[] allocationIds)
//    {
//        return dormAllocationMapper.deleteDormAllocationByAllocationIds(allocationIds);
//    }

//    @Override
//    public int deleteDormAllocationByAllocationId(Long allocationId) {
//        // 先查询分配信息以获取房间ID
//        DormAllocation allocation = dormAllocationMapper.selectDormAllocationByAllocationId(allocationId);
//
//        int result = dormAllocationMapper.deleteDormAllocationByAllocationId(allocationId);
//
//        // 更新房间的已住人数和状态
//        if (result > 0 && allocation != null) {
//            updateRoomStatusAndCount(allocation.getRoomId());
//        }
//
//        return result;
//    }

    @Override
    public int deleteDormAllocationByAllocationId(Long allocationId) {
        // 先查询分配信息以获取房间ID
        DormAllocation allocation = dormAllocationMapper.selectDormAllocationByAllocationId(allocationId);

        int result = dormAllocationMapper.deleteDormAllocationByAllocationId(allocationId);

        // 更新房间的已住人数和状态
        if (result > 0 && allocation != null && allocation.getRoomId() != null) {
            updateRoomStatusAndCount(allocation.getRoomId());
        }

        return result;
    }




    /**
     * 删除住宿分配信息
     * 
     * @param allocationIds 住宿分配主键
     * @return 结果
     */
//    @Override
//    public int deleteDormAllocationByAllocationId(Long allocationId)
//    {
//        return dormAllocationMapper.deleteDormAllocationByAllocationId(allocationId);
//    }

    @Override
    public int deleteDormAllocationByAllocationIds(Long[] allocationIds) {
        // 先查询所有分配信息以获取房间ID
        Set<Long> roomIds = new HashSet<>();
        for (Long allocationId : allocationIds) {
            DormAllocation allocation = dormAllocationMapper.selectDormAllocationByAllocationId(allocationId);
            if (allocation != null) {
                roomIds.add(allocation.getRoomId());
            }
        }

        int result = dormAllocationMapper.deleteDormAllocationByAllocationIds(allocationIds);

        // 更新涉及的房间的已住人数和状态
        if (result > 0) {
            for (Long roomId : roomIds) {
                updateRoomStatusAndCount(roomId);
            }
        }

        return result;
    }


//    /**
//     * 更新房间的已住人数和状态
//     * @param roomId 房间ID
//     */
//    private void updateRoomStatusAndCount(Long roomId) {
//        // 查询房间信息
//        DormRoom room = dormRoomService.selectDormRoomByRoomId(roomId);
//        if (room == null) {
//            return;
//        }
//
//        // 查询该房间的住宿分配数量
//        DormAllocation queryAllocation = new DormAllocation();
//        queryAllocation.setRoomId(roomId);
//        queryAllocation.setStatus("0"); // 只统计入住中的学生
//        List<DormAllocation> allocations = dormAllocationMapper.selectDormAllocationList(queryAllocation);
//
//        // 更新已住人数
//        int currentCount = allocations.size();
//        room.setCurrentCount((long) currentCount);
//
//        // 根据已住人数和床位数量更新状态
//        if ("2".equals(room.getStatus())) {
//            // 如果房间状态是维修中，则不改变状态
//            // 维修中的房间即使没有人住也保持维修中状态
//        } else if (currentCount >= room.getMaxCount()) {
//            // 如果已住人数大于等于床位数量，则状态设为已满员
//            room.setStatus("1");
//        } else {
//            // 否则状态设为可入住
//            room.setStatus("0");
//        }
//
//        // 更新房间信息
//        dormRoomService.updateDormRoom(room);
//    }

    /**
     * 更新房间的已住人数和状态
     * @param roomId 房间ID
     */
    private void updateRoomStatusAndCount(Long roomId) {
        // 查询房间信息
        DormRoom room = dormRoomService.selectDormRoomByRoomId(roomId);
        if (room == null) {
            return;
        }

        // 查询该房间的住宿分配数量（只统计有效的分配）
        DormAllocation queryAllocation = new DormAllocation();
        queryAllocation.setRoomId(roomId);
        queryAllocation.setStatus("0"); // 只统计入住中的学生
        List<DormAllocation> allocations = dormAllocationMapper.selectDormAllocationList(queryAllocation);

        // 更新已住人数
        int currentCount = allocations != null ? allocations.size() : 0;
        room.setCurrentCount((long) currentCount);

        // 根据已住人数和床位数量更新状态
        // 注意：如果房间已被标记为维修中(2)，则保持该状态
        if (!"2".equals(room.getStatus())) { // 只有非维修状态才重新计算
            if (currentCount >= room.getMaxCount()) {
                // 如果已住人数大于等于床位数量，则状态设为已满员
                room.setStatus("1");
            } else {
                // 否则状态设为可入住
                room.setStatus("0");
            }
        }

        // 更新房间信息
        dormRoomService.updateDormRoom(room);
    }








}
