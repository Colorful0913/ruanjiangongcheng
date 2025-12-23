package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manage.domain.DormBuilding;
import com.ruoyi.manage.domain.DormRepair;
import com.ruoyi.manage.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.domain.DormRoom;
import com.ruoyi.manage.service.IDormRoomService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.mapper.DormAllocationMapper;

/**
 * 宿舍房间Service业务层处理
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class DormRoomServiceImpl implements IDormRoomService 
{
    @Autowired
    private DormRoomMapper dormRoomMapper;
    @Autowired
    private DormBuildingMapper dormBuildingMapper;
    @Autowired
    private DormAllocationMapper dormAllocationMapper;
    @Autowired
    private DormRepairMapper dormRepairMapper;




    /**
     * 查询宿舍房间
     * 
     * @param roomId 宿舍房间主键
     * @return 宿舍房间
     */
    @Override
    public DormRoom selectDormRoomByRoomId(Long roomId)
    {
        return dormRoomMapper.selectDormRoomByRoomId(roomId);
    }

    /**
     * 查询宿舍房间列表
     * 
     * @param dormRoom 宿舍房间
     * @return 宿舍房间
     */
    @Override
    public List<DormRoom> selectDormRoomList(DormRoom dormRoom)
    {
        return dormRoomMapper.selectDormRoomList(dormRoom);
    }

//    /**
//     * 新增宿舍房间
//     *
//     * @param dormRoom 宿舍房间
//     * @return 结果
//     */
//    @Override
//    public int insertDormRoom(DormRoom dormRoom)
//    {
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }


//    // 修改 insertDormRoom 方法
//    @Override
//    public int insertDormRoom(DormRoom dormRoom) {
//        // 检查同一楼栋下是否已存在相同房间号
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (!existingRooms.isEmpty()) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }

//    @Override
//    public int insertDormRoom(DormRoom dormRoom) {
//        // 检查同一楼栋下是否已存在相同房间号
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (!existingRooms.isEmpty()) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        // 根据房间类型自动设置床位数量
//        if (dormRoom.getType() != null) {
//            if ("1".equals(dormRoom.getType())) {
//                dormRoom.setMaxCount(4L); // 4人间
//            } else if ("2".equals(dormRoom.getType())) {
//                dormRoom.setMaxCount(6L); // 6人间
//            }
//        }
//
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }


//    // 修改 insertDormRoom 方法
//    @Override
//    public int insertDormRoom(DormRoom dormRoom) {
//        // 校验已住人数
//        validateOccupancy(dormRoom);
//
//        // 检查同一楼栋下是否已存在相同房间号
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (!existingRooms.isEmpty()) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }


//    // 在 insertDormRoom 中调用
//    @Override
//    public int insertDormRoom(DormRoom dormRoom) {
//        // 自动设置床位数量
//        autoSetMaxCount(dormRoom);
//
//        // 校验已住人数
//        validateOccupancy(dormRoom);
//
//        // 检查同一楼栋下是否已存在相同房间号
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (!existingRooms.isEmpty()) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }


//    // 修改 insertDormRoom 方法
//    @Override
//    public int insertDormRoom(DormRoom dormRoom) {
//        // 校验已住人数
//        validateOccupancy(dormRoom);
//
//        // 自动设置床位数量
//        autoSetMaxCount(dormRoom);
//
//        // 检查同一楼栋下是否已存在相同房间号
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (!existingRooms.isEmpty()) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }


//    // 修改 insertDormRoom 方法，添加自动计算逻辑
//    @Override
//    public int insertDormRoom(DormRoom dormRoom) {
//        // 自动设置床位数量
//        autoSetMaxCount(dormRoom);
//
//        // 自动计算已住人数和状态
//        calculateOccupancyAndStatus(dormRoom);
//
//        // 检查同一楼栋下是否已存在相同房间号
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (!existingRooms.isEmpty()) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setCreateTime(DateUtils.getNowDate());
//        return dormRoomMapper.insertDormRoom(dormRoom);
//    }

    // 修改 insertDormRoom 方法，移除对已住人数的校验，改为自动计算
    @Override
    public int insertDormRoom(DormRoom dormRoom) {
        // 自动设置床位数量
        autoSetMaxCount(dormRoom);

        // 自动计算已住人数（默认为0，因为是新增房间）
        dormRoom.setCurrentCount(0L);

        // 自动设置状态为可入住
        dormRoom.setStatus("0");

        // 检查同一楼栋下是否已存在相同房间号
        DormRoom query = new DormRoom();
        query.setBuildingId(dormRoom.getBuildingId());
        query.setRoomNumber(dormRoom.getRoomNumber());
        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
        if (!existingRooms.isEmpty()) {
            // 获取楼栋信息用于错误提示
            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
        }

        dormRoom.setCreateTime(DateUtils.getNowDate());
        return dormRoomMapper.insertDormRoom(dormRoom);
    }


//    /**
//     * 修改宿舍房间
//     *
//     * @param dormRoom 宿舍房间
//     * @return 结果
//     */
//    @Override
//    public int updateDormRoom(DormRoom dormRoom)
//    {
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }

//    // 修改 updateDormRoom 方法
//    @Override
//    public int updateDormRoom(DormRoom dormRoom) {
//        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }


//    @Override
//    public int updateDormRoom(DormRoom dormRoom) {
//        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        // 根据房间类型自动设置床位数量
//        if (dormRoom.getType() != null) {
//            if ("1".equals(dormRoom.getType())) {
//                dormRoom.setMaxCount(4L); // 4人间
//            } else if ("2".equals(dormRoom.getType())) {
//                dormRoom.setMaxCount(6L); // 6人间
//            }
//        }
//
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }


//    // 修改 updateDormRoom 方法
//    @Override
//    public int updateDormRoom(DormRoom dormRoom) {
//        // 校验已住人数
//        validateOccupancy(dormRoom);
//
//        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }


//    // 在 updateDormRoom 中调用
//    @Override
//    public int updateDormRoom(DormRoom dormRoom) {
//        // 自动设置床位数量
//        autoSetMaxCount(dormRoom);
//
//        // 校验已住人数
//        validateOccupancy(dormRoom);
//
//        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }


//    // 修改 updateDormRoom 方法
//    @Override
//    public int updateDormRoom(DormRoom dormRoom) {
//        // 校验已住人数
//        validateOccupancy(dormRoom);
//
//        // 自动设置床位数量
//        autoSetMaxCount(dormRoom);
//
//        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }


//    // 修改 updateDormRoom 方法，添加自动计算逻辑
//    @Override
//    public int updateDormRoom(DormRoom dormRoom) {
//        // 自动设置床位数量
//        autoSetMaxCount(dormRoom);
//
//        // 自动计算已住人数和状态
//        calculateOccupancyAndStatus(dormRoom);
//
//        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
//        DormRoom query = new DormRoom();
//        query.setBuildingId(dormRoom.getBuildingId());
//        query.setRoomNumber(dormRoom.getRoomNumber());
//        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
//        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
//            // 获取楼栋信息用于错误提示
//            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
//            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
//        }
//
//        dormRoom.setUpdateTime(DateUtils.getNowDate());
//        return dormRoomMapper.updateDormRoom(dormRoom);
//    }


    // 修改 updateDormRoom 方法，移除对已住人数的校验，改为自动计算
    @Override
    public int updateDormRoom(DormRoom dormRoom) {
        // 自动设置床位数量
        autoSetMaxCount(dormRoom);

        // 自动计算已住人数和状态
        calculateOccupancyAndStatus(dormRoom);

        // 检查除当前房间外是否已存在相同楼栋和房间号的记录
        DormRoom query = new DormRoom();
        query.setBuildingId(dormRoom.getBuildingId());
        query.setRoomNumber(dormRoom.getRoomNumber());
        List<DormRoom> existingRooms = dormRoomMapper.selectDormRoomList(query);
        if (existingRooms.stream().anyMatch(r -> !r.getRoomId().equals(dormRoom.getRoomId()))) {
            // 获取楼栋信息用于错误提示
            DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(dormRoom.getBuildingId());
            String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
            throw new ServiceException(buildingCode + "楼栋" + dormRoom.getRoomNumber() + "房间号已存在");
        }

        dormRoom.setUpdateTime(DateUtils.getNowDate());
        return dormRoomMapper.updateDormRoom(dormRoom);
    }



//    // 添加自动计算已住人数和状态的方法
//    private void calculateOccupancyAndStatus(DormRoom dormRoom) {
//        // 如果前端没有传入已住人数，则从数据库查询或默认为0
//        if (dormRoom.getCurrentCount() == null) {
//            // 这里可以查询住宿分配表统计实际入住人数
//            // Long actualCount = dormAllocationMapper.countByRoomId(dormRoom.getRoomId());
//            // dormRoom.setCurrentCount(actualCount != null ? actualCount : 0L);
//            dormRoom.setCurrentCount(0L); // 默认为0
//        }
//
//        // 根据房间类型和已住人数自动设置状态
//        Long maxCount = getMaxCountByType(dormRoom.getType());
//        if (dormRoom.getCurrentCount() >= maxCount) {
//            dormRoom.setStatus("1"); // 已满员
//        } else {
//            dormRoom.setStatus("0"); // 可入住
//        }
//    }


    // 添加自动计算已住人数和状态的方法
    private void calculateOccupancyAndStatus(DormRoom dormRoom) {
        // 查询该房间的实际入住人数
        Long actualCount = dormAllocationMapper.countActiveByRoomId(dormRoom.getRoomId());
        dormRoom.setCurrentCount(actualCount != null ? actualCount : 0L);

        // 根据房间类型和已住人数自动设置状态
        Long maxCount = getMaxCountByType(dormRoom.getType());
        if (dormRoom.getCurrentCount() >= maxCount) {
            dormRoom.setStatus("1"); // 已满员
        } else {
            dormRoom.setStatus("0"); // 可入住
        }
    }


//    // 根据房间类型获取最大容纳人数
//    private Long getMaxCountByType(String type) {
//        if ("1".equals(type)) {
//            return 4L; // 4人间
//        } else if ("2".equals(type)) {
//            return 6L; // 6人间
//        }
//        return 0L;
//    }


    // 根据房间类型获取最大容纳人数
    private Long getMaxCountByType(String type) {
        if ("1".equals(type)) {
            return 4L; // 4人间
        } else if ("2".equals(type)) {
            return 6L; // 6人间
        }
        return 0L;
    }


//    // 添加校验已住人数的方法
//    private void validateOccupancy(DormRoom dormRoom) {
//        // 校验已住人数是否为数字
//        if (dormRoom.getCurrentCount() == null) {
//            throw new ServiceException("已住人数不能为空");
//        }
//
//        // 根据房间类型校验已住人数是否合理
//        if ("1".equals(dormRoom.getType())) {
//            // 4人间
//            if (dormRoom.getCurrentCount() < 0 || dormRoom.getCurrentCount() > 4) {
//                throw new ServiceException("已住人数应当小于等于所选房间类型的人数(4人间最多住4人)");
//            }
//        } else if ("2".equals(dormRoom.getType())) {
//            // 6人间
//            if (dormRoom.getCurrentCount() < 0 || dormRoom.getCurrentCount() > 6) {
//                throw new ServiceException("已住人数应当小于等于所选房间类型的人数(6人间最多住6人)");
//            }
//        }
//    }

    // 添加校验已住人数的方法
    private void validateOccupancy(DormRoom dormRoom) {
        // 校验已住人数是否为数字
        if (dormRoom.getCurrentCount() == null) {
            throw new ServiceException("已住人数不能为空");
        }

        // 根据房间类型校验已住人数是否合理
        if ("1".equals(dormRoom.getType())) {
            // 4人间
            if (dormRoom.getCurrentCount() < 0 || dormRoom.getCurrentCount() > 4) {
                throw new ServiceException("已住人数应当小于等于所选房间类型的人数(4人间最多住4人)");
            }
        } else if ("2".equals(dormRoom.getType())) {
            // 6人间
            if (dormRoom.getCurrentCount() < 0 || dormRoom.getCurrentCount() > 6) {
                throw new ServiceException("已住人数应当小于等于所选房间类型的人数(6人间最多住6人)");
            }
        } else {
            throw new ServiceException("无效的房间类型");
        }
    }




//    // 在 insertDormRoom 和 updateDormRoom 方法中添加自动设置床位数量的逻辑
//    private void autoSetMaxCount(DormRoom dormRoom) {
//        if ("1".equals(dormRoom.getType())) {
//            dormRoom.setMaxCount(4L);
//        } else if ("2".equals(dormRoom.getType())) {
//            dormRoom.setMaxCount(6L);
//        }
//    }

//    // 添加自动设置床位数量的方法
//    private void autoSetMaxCount(DormRoom dormRoom) {
//        if ("1".equals(dormRoom.getType())) {
//            dormRoom.setMaxCount(4L);
//        } else if ("2".equals(dormRoom.getType())) {
//            dormRoom.setMaxCount(6L);
//        }
//    }

    // 添加自动设置床位数量的方法
    private void autoSetMaxCount(DormRoom dormRoom) {
        if ("1".equals(dormRoom.getType())) {
            dormRoom.setMaxCount(4L);
        } else if ("2".equals(dormRoom.getType())) {
            dormRoom.setMaxCount(6L);
        }
    }


//    /**
//     * 批量删除宿舍房间
//     *
//     * @param roomIds 需要删除的宿舍房间主键
//     * @return 结果
//     */
//    @Override
//    public int deleteDormRoomByRoomIds(Long[] roomIds)
//    {
//        return dormRoomMapper.deleteDormRoomByRoomIds(roomIds);
//    }


//    /**
//     * 批量删除宿舍房间
//     *
//     * @param roomIds 需要删除的宿舍房间主键
//     * @return 结果
//     */
//    @Override
//    public int deleteDormRoomByRoomIds(Long[] roomIds)
//    {
//        // 检查是否有房间存在住宿分配记录
//        for (Long roomId : roomIds) {
//            // 查询该房间是否有住宿分配记录
//            DormAllocation query = new DormAllocation();
//            query.setRoomId(roomId);
//            List<DormAllocation> allocations = dormAllocationMapper.selectDormAllocationList(query);
//
//            if (!allocations.isEmpty()) {
//                // 获取房间信息以显示房间号和楼栋编号
//                DormRoom room = dormRoomMapper.selectDormRoomByRoomId(roomId);
////                String roomInfo = room != null ? (room.getBuildingCode() + "楼栋" + room.getRoomNumber() + "房间") : roomId.toString();
//
//                // 修改为：
//                String roomInfo = roomId.toString();
//                if (room != null) {
//                    // 通过楼栋ID查询楼栋信息以获取楼栋编号
//                    DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(room.getBuildingId());
//                    String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
//                    roomInfo = buildingCode + "楼栋" + room.getRoomNumber() + "房间";
//                }
//
//
//                throw new ServiceException("无法删除 " + roomInfo + "，该房间存在住宿分配记录，请先删除相关住宿分配记录");
//            }
//
//
//
//        }
//
//        return dormRoomMapper.deleteDormRoomByRoomIds(roomIds);
//    }



    /**
     * 批量删除宿舍房间
     *
     * @param roomIds 需要删除的宿舍房间主键
     * @return 结果
     */
    @Override
    public int deleteDormRoomByRoomIds(Long[] roomIds)
    {
        // 检查是否有房间存在住宿分配记录
        for (Long roomId : roomIds) {
            // 查询该房间是否有住宿分配记录
            DormAllocation query = new DormAllocation();
            query.setRoomId(roomId);
            List<DormAllocation> allocations = dormAllocationMapper.selectDormAllocationList(query);

            if (!allocations.isEmpty()) {
                // 获取房间信息以显示房间号和楼栋编号
                DormRoom room = dormRoomMapper.selectDormRoomByRoomId(roomId);
                String roomInfo = roomId.toString();
                if (room != null) {
                    // 通过楼栋ID查询楼栋信息以获取楼栋编号
                    DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(room.getBuildingId());
                    String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
                    roomInfo = buildingCode + "楼栋" + room.getRoomNumber() + "房间";
                }
                throw new ServiceException("无法删除 " + roomInfo + "，该房间存在住宿分配记录，请先删除相关住宿分配记录");
            }

            // 查询该房间是否有维修报修记录
            DormRepair repairQuery = new DormRepair();
            repairQuery.setRoomId(roomId);
            List<DormRepair> repairs = dormRepairMapper.selectDormRepairList(repairQuery);

            if (!repairs.isEmpty()) {
                // 获取房间信息以显示房间号和楼栋编号
                DormRoom room = dormRoomMapper.selectDormRoomByRoomId(roomId);
                String roomInfo = roomId.toString();
                if (room != null) {
                    // 通过楼栋ID查询楼栋信息以获取楼栋编号
                    DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(room.getBuildingId());
                    String buildingCode = building != null ? building.getBuildingCode() : "未知楼栋";
                    roomInfo = buildingCode + "楼栋" + room.getRoomNumber() + "房间";
                }
                throw new ServiceException("无法删除 " + roomInfo + "，该房间存在维修报修记录，请先删除相关维修报修记录");
            }
        }

        return dormRoomMapper.deleteDormRoomByRoomIds(roomIds);
    }






    /**
     * 删除宿舍房间信息
     * 
     * @param roomId 宿舍房间主键
     * @return 结果
     */
    @Override
    public int deleteDormRoomByRoomId(Long roomId)
    {
        return dormRoomMapper.deleteDormRoomByRoomId(roomId);
    }
}
