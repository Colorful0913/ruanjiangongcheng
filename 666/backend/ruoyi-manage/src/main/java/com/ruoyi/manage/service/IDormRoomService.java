package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.DormRoom;

/**
 * 宿舍房间Service接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IDormRoomService 
{
    /**
     * 查询宿舍房间
     * 
     * @param roomId 宿舍房间主键
     * @return 宿舍房间
     */
    public DormRoom selectDormRoomByRoomId(Long roomId);

    /**
     * 查询宿舍房间列表
     * 
     * @param dormRoom 宿舍房间
     * @return 宿舍房间集合
     */
    public List<DormRoom> selectDormRoomList(DormRoom dormRoom);

    /**
     * 新增宿舍房间
     * 
     * @param dormRoom 宿舍房间
     * @return 结果
     */
    public int insertDormRoom(DormRoom dormRoom);

    /**
     * 修改宿舍房间
     * 
     * @param dormRoom 宿舍房间
     * @return 结果
     */
    public int updateDormRoom(DormRoom dormRoom);

    /**
     * 批量删除宿舍房间
     * 
     * @param roomIds 需要删除的宿舍房间主键集合
     * @return 结果
     */
    public int deleteDormRoomByRoomIds(Long[] roomIds);

    /**
     * 删除宿舍房间信息
     * 
     * @param roomId 宿舍房间主键
     * @return 结果
     */
    public int deleteDormRoomByRoomId(Long roomId);
}
