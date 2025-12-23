package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.DormBuilding;

/**
 * 宿舍楼Service接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IDormBuildingService 
{
    /**
     * 查询宿舍楼
     * 
     * @param buildingId 宿舍楼主键
     * @return 宿舍楼
     */
    public DormBuilding selectDormBuildingByBuildingId(Long buildingId);

    /**
     * 查询宿舍楼列表
     * 
     * @param dormBuilding 宿舍楼
     * @return 宿舍楼集合
     */
    public List<DormBuilding> selectDormBuildingList(DormBuilding dormBuilding);

    /**
     * 新增宿舍楼
     * 
     * @param dormBuilding 宿舍楼
     * @return 结果
     */
    public int insertDormBuilding(DormBuilding dormBuilding);

    /**
     * 修改宿舍楼
     * 
     * @param dormBuilding 宿舍楼
     * @return 结果
     */
    public int updateDormBuilding(DormBuilding dormBuilding);

    /**
     * 批量删除宿舍楼
     * 
     * @param buildingIds 需要删除的宿舍楼主键集合
     * @return 结果
     */
    public int deleteDormBuildingByBuildingIds(Long[] buildingIds);

    /**
     * 删除宿舍楼信息
     * 
     * @param buildingId 宿舍楼主键
     * @return 结果
     */
    public int deleteDormBuildingByBuildingId(Long buildingId);
}
