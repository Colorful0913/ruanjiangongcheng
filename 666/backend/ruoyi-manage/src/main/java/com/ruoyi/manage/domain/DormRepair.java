package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 维修报修对象 tb_dorm_repair
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public class DormRepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报修ID */
    private Long repairId;

    /** 报修房间ID */
    @Excel(name = "报修房间ID")
    private Long roomId;

    /** 报修人ID */
    @Excel(name = "报修人ID")
    private Long reportUserId;

    /** 房间号 */
    private String roomNumber;

    /** 楼栋编号 */
    private String buildingCode;

    /** 学生学号 */
    private String studentNumber;

    /** 学生姓名 */
    private String studentName;

    /** 维修工名称 */
    private String workerName;


    /** 问题描述 */
    @Excel(name = "问题描述")
    private String description;

    /** 报修图片URL（多个用逗号分隔） */
    @Excel(name = "报修图片URL", readConverterExp = "多=个用逗号分隔")
    private String images;

    /** 指派维修工ID */
    @Excel(name = "指派维修工ID")
    private Long workerUserId;

    /** 维修反馈 */
    @Excel(name = "维修反馈")
    private String feedback;

    /** 状态（0待处理, 1维修中, 2已完成） */
    @Excel(name = "状态", readConverterExp = "0=待处理,,1=维修中,,2=已完成")
    private String status;

    /** 删除标志（0:未删除;1:已删除） */
    private String delFlag;

    public void setRepairId(Long repairId) 
    {
        this.repairId = repairId;
    }

    public Long getRepairId() 
    {
        return repairId;
    }

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }

    public void setReportUserId(Long reportUserId) 
    {
        this.reportUserId = reportUserId;
    }

    public Long getReportUserId() 
    {
        return reportUserId;
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


    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }


    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getImages() 
    {
        return images;
    }

    public void setWorkerUserId(Long workerUserId) 
    {
        this.workerUserId = workerUserId;
    }

    public Long getWorkerUserId() 
    {
        return workerUserId;
    }

    public void setFeedback(String feedback) 
    {
        this.feedback = feedback;
    }

    public String getFeedback() 
    {
        return feedback;
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
            .append("repairId", getRepairId())
            .append("roomId", getRoomId())
            .append("reportUserId", getReportUserId())
            .append("description", getDescription())
            .append("images", getImages())
            .append("workerUserId", getWorkerUserId())
            .append("feedback", getFeedback())
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
