package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宿舍楼对象 tb_dorm_building
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public class DormBuilding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 楼栋ID */
    private Long buildingId;

    /** 楼栋名称（如: 1号楼） */
    @Excel(name = "楼栋名称", readConverterExp = "如=:,1=号楼")
    private String buildingName;

    /** 楼栋编号（如: BLD01） */
    @Excel(name = "楼栋编号", readConverterExp = "如=:,B=LD01")
    private String buildingCode;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 管理员ID（关联sys_user用户ID） */
    @Excel(name = "管理员ID", readConverterExp = "关=联sys_user用户ID")
    private Long adminId;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 状态（0正常, 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,,1=停用")
    private String status;

    /** 删除标志（0:未删除;1:已删除） */
    private String delFlag;

    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }

    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }

    public void setBuildingCode(String buildingCode) 
    {
        this.buildingCode = buildingCode;
    }

    public String getBuildingCode() 
    {
        return buildingCode;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
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
            .append("buildingId", getBuildingId())
            .append("buildingName", getBuildingName())
            .append("buildingCode", getBuildingCode())
            .append("address", getAddress())
            .append("adminId", getAdminId())
            .append("contactPhone", getContactPhone())
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
