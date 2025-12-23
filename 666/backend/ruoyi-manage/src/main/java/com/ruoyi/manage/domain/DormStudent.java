package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息扩展对象 tb_dorm_student
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public class DormStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生ID */
    private Long studentId;

    /** 用户ID（关联sys_user用户ID） */
    @Excel(name = "用户ID", readConverterExp = "关=联sys_user用户ID")
    private Long userId;

    /** 学号（如: 202405001） */
    @Excel(name = "学号", readConverterExp = "如=:,2=02405001")
    private String studentNumber;

    /** 所属院系 */
    @Excel(name = "所属院系")
    private String faculty;

    /** 班级 */
    @Excel(name = "班级")
    private String className;

    /** 删除标志（0:未删除;1:已删除） */
    private String delFlag;

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setStudentNumber(String studentNumber) 
    {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() 
    {
        return studentNumber;
    }

    public void setFaculty(String faculty) 
    {
        this.faculty = faculty;
    }

    public String getFaculty() 
    {
        return faculty;
    }

    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
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
            .append("studentId", getStudentId())
            .append("userId", getUserId())
            .append("studentNumber", getStudentNumber())
            .append("faculty", getFaculty())
            .append("className", getClassName())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
