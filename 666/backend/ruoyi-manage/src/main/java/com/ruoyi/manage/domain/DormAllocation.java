package com.ruoyi.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 住宿分配对象 tb_dorm_allocation
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public class DormAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分配ID */
    private Long allocationId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 房间ID */
    @Excel(name = "房间ID")
    private Long roomId;

    /** 学生学号 */
    private String studentNumber;

    /** 学生姓名 */
    private String studentName;

    /** 房间号 */
    private String roomNumber;

    /** 楼栋编号 */
    private String buildingCode;

    /** 楼栋ID */
    private Long buildingId;


    /** 床位编号（如: A, 1） */
    @Excel(name = "床位编号", readConverterExp = "如=:,A=,,1=")
    private String bedNumber;

    /** 入住日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInDate;

    /** 退宿日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退宿日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkOutDate;

    /** 状态（0入住中, 1已退宿） */
    @Excel(name = "状态", readConverterExp = "0=入住中,,1=已退宿")
    private String status;

    /** 删除标志（0:未删除;1:已删除） */
    private String delFlag;

    public void setAllocationId(Long allocationId) 
    {
        this.allocationId = allocationId;
    }

    public Long getAllocationId() 
    {
        return allocationId;
    }

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }



    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }



    public void setBedNumber(String bedNumber) 
    {
        this.bedNumber = bedNumber;
    }

    public String getBedNumber() 
    {
        return bedNumber;
    }

    public void setCheckInDate(Date checkInDate) 
    {
        this.checkInDate = checkInDate;
    }

    public Date getCheckInDate() 
    {
        return checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) 
    {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckOutDate() 
    {
        return checkOutDate;
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
            .append("allocationId", getAllocationId())
            .append("studentId", getStudentId())
            .append("roomId", getRoomId())
            .append("bedNumber", getBedNumber())
            .append("checkInDate", getCheckInDate())
            .append("checkOutDate", getCheckOutDate())
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
