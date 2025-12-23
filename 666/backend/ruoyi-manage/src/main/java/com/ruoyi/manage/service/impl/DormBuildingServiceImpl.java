package com.ruoyi.manage.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manage.domain.DormRoom;
import com.ruoyi.manage.mapper.DormRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.DormBuildingMapper;
import com.ruoyi.manage.domain.DormBuilding;
import com.ruoyi.manage.service.IDormBuildingService;


/**
 * 宿舍楼Service业务层处理
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class DormBuildingServiceImpl implements IDormBuildingService 
{
    @Autowired
    private DormBuildingMapper dormBuildingMapper;
    @Autowired
    private DormRoomMapper dormRoomMapper;




    /**
     * 查询宿舍楼
     * 
     * @param buildingId 宿舍楼主键
     * @return 宿舍楼
     */
    @Override
    public DormBuilding selectDormBuildingByBuildingId(Long buildingId)
    {
        return dormBuildingMapper.selectDormBuildingByBuildingId(buildingId);
    }

    /**
     * 查询宿舍楼列表
     * 
     * @param dormBuilding 宿舍楼
     * @return 宿舍楼
     */
    @Override
    public List<DormBuilding> selectDormBuildingList(DormBuilding dormBuilding)
    {
        return dormBuildingMapper.selectDormBuildingList(dormBuilding);
    }

//    /**
//     * 新增宿舍楼
//     *
//     * @param dormBuilding 宿舍楼
//     * @return 结果
//     */
//    @Override
//    public int insertDormBuilding(DormBuilding dormBuilding)
//    {
//        dormBuilding.setCreateTime(DateUtils.getNowDate());
//        return dormBuildingMapper.insertDormBuilding(dormBuilding);
//    }

    /**
     * 新增宿舍楼
     *
     * @param dormBuilding 宿舍楼
     * @return 结果
     */
    @Override
    public int insertDormBuilding(DormBuilding dormBuilding) {
        // 检查楼栋名称是否已存在
        DormBuilding queryByName = new DormBuilding();
        queryByName.setBuildingName(dormBuilding.getBuildingName());
        if (!selectDormBuildingList(queryByName).isEmpty()) {
            throw new ServiceException("楼栋名称已存在");
        }

        // 检查楼栋编号是否已存在
        DormBuilding queryByCode = new DormBuilding();
        queryByCode.setBuildingCode(dormBuilding.getBuildingCode());
        if (!selectDormBuildingList(queryByCode).isEmpty()) {
            throw new ServiceException("楼栋编号已存在");
        }

        dormBuilding.setCreateTime(DateUtils.getNowDate());
        return dormBuildingMapper.insertDormBuilding(dormBuilding);
    }



//    /**
//     * 修改宿舍楼
//     *
//     * @param dormBuilding 宿舍楼
//     * @return 结果
//     */
//    @Override
//    public int updateDormBuilding(DormBuilding dormBuilding)
//    {
//        dormBuilding.setUpdateTime(DateUtils.getNowDate());
//        return dormBuildingMapper.updateDormBuilding(dormBuilding);
//    }


    /**
     * 修改宿舍楼
     *
     * @param dormBuilding 宿舍楼
     * @return 结果
     */
    @Override
    public int updateDormBuilding(DormBuilding dormBuilding) {
        // 检查是否存在其他具有相同楼栋名称的记录
        DormBuilding queryByName = new DormBuilding();
        queryByName.setBuildingName(dormBuilding.getBuildingName());
        List<DormBuilding> nameResults = selectDormBuildingList(queryByName);
        if (nameResults.stream().anyMatch(b -> !b.getBuildingId().equals(dormBuilding.getBuildingId()))) {
            throw new ServiceException("楼栋名称已存在");
        }

        // 检查是否存在其他具有相同楼栋编号的记录
        DormBuilding queryByCode = new DormBuilding();
        queryByCode.setBuildingCode(dormBuilding.getBuildingCode());
        List<DormBuilding> codeResults = selectDormBuildingList(queryByCode);
        if (codeResults.stream().anyMatch(b -> !b.getBuildingId().equals(dormBuilding.getBuildingId()))) {
            throw new ServiceException("楼栋编号已存在");
        }

        dormBuilding.setUpdateTime(DateUtils.getNowDate());
        return dormBuildingMapper.updateDormBuilding(dormBuilding);
    }




//    /**
//     * 批量删除宿舍楼
//     *
//     * @param buildingIds 需要删除的宿舍楼主键
//     * @return 结果
//     */
//    @Override
//    public int deleteDormBuildingByBuildingIds(Long[] buildingIds)
//    {
//        return dormBuildingMapper.deleteDormBuildingByBuildingIds(buildingIds);
//    }


    /**
     * 批量删除宿舍楼
     *
     * @param buildingIds 需要删除的宿舍楼主键
     * @return 结果
     */
    @Override
    public int deleteDormBuildingByBuildingIds(Long[] buildingIds)
    {
        // 检查是否有楼栋存在房间记录
        for (Long buildingId : buildingIds) {
            // 查询该楼栋是否有房间记录
            DormRoom query = new DormRoom();
            query.setBuildingId(buildingId);
            List<DormRoom> rooms = dormRoomMapper.selectDormRoomList(query);

            if (!rooms.isEmpty()) {
                // 获取楼栋信息以显示楼栋编号
                DormBuilding building = dormBuildingMapper.selectDormBuildingByBuildingId(buildingId);
                String buildingInfo = building != null ? building.getBuildingCode() : buildingId.toString();
                throw new ServiceException("无法删除楼栋 " + buildingInfo + "，该楼栋下存在房间记录，请先删除相关房间记录");
            }
        }

        return dormBuildingMapper.deleteDormBuildingByBuildingIds(buildingIds);
    }




    /**
     * 删除宿舍楼信息
     * 
     * @param buildingId 宿舍楼主键
     * @return 结果
     */
    @Override
    public int deleteDormBuildingByBuildingId(Long buildingId)
    {
        return dormBuildingMapper.deleteDormBuildingByBuildingId(buildingId);
    }
}
