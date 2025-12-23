package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宿舍房间对象 tb_dorm_room
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public class DormRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房间ID */
    private Long roomId;

    /** 所属楼栋ID */
    @Excel(name = "所属楼栋ID")
    private Long buildingId;

    /** 房间号（如: 101） */
    @Excel(name = "房间号", readConverterExp = "如=:,1=01")
    private String roomNumber;

    /** 房间类型（1=4人间, 2=6人间） */
    @Excel(name = "房间类型", readConverterExp = "1==4人间,,2==6人间")
    private String type;

    /** 已住人数 */
    @Excel(name = "已住人数")
    private Long currentCount;

    /** 床位数量 */
    @Excel(name = "床位数量")
    private Long maxCount;

    /** 状态（0可入住, 1已满员, 2维修中） */
    @Excel(name = "状态", readConverterExp = "0=可入住,,1=已满员,,2=维修中")
    private String status;

    /** 删除标志（0:未删除;1:已删除） */
    private String delFlag;

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }

    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }

    public void setRoomNumber(String roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() 
    {
        return roomNumber;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setCurrentCount(Long currentCount) 
    {
        this.currentCount = currentCount;
    }

    public Long getCurrentCount() 
    {
        return currentCount;
    }

    public void setMaxCount(Long maxCount) 
    {
        this.maxCount = maxCount;
    }

    public Long getMaxCount() 
    {
        return maxCount;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("buildingId", getBuildingId())
            .append("roomNumber", getRoomNumber())
            .append("type", getType())
            .append("currentCount", getCurrentCount())
            .append("maxCount", getMaxCount())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
