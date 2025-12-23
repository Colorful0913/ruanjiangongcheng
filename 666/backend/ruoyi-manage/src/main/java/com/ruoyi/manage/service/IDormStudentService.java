package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.DormStudent;




/**
 * 学生信息扩展Service接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
public interface IDormStudentService 
{


    /**
     * 根据学生学号查询学生信息
     *
     * @param studentNumber 学生学号
     * @return 学生信息
     */
    public DormStudent selectDormStudentByStudentNumber(String studentNumber);




    /**
     * 查询学生信息扩展
     * 
     * @param studentId 学生信息扩展主键
     * @return 学生信息扩展
     */
    public DormStudent selectDormStudentByStudentId(Long studentId);

    /**
     * 查询学生信息扩展列表
     * 
     * @param dormStudent 学生信息扩展
     * @return 学生信息扩展集合
     */
    public List<DormStudent> selectDormStudentList(DormStudent dormStudent);

    /**
     * 新增学生信息扩展
     * 
     * @param dormStudent 学生信息扩展
     * @return 结果
     */
    public int insertDormStudent(DormStudent dormStudent);

    /**
     * 修改学生信息扩展
     * 
     * @param dormStudent 学生信息扩展
     * @return 结果
     */
    public int updateDormStudent(DormStudent dormStudent);

    /**
     * 批量删除学生信息扩展
     * 
     * @param studentIds 需要删除的学生信息扩展主键集合
     * @return 结果
     */
    public int deleteDormStudentByStudentIds(Long[] studentIds);

    /**
     * 删除学生信息扩展信息
     * 
     * @param studentId 学生信息扩展主键
     * @return 结果
     */
    public int deleteDormStudentByStudentId(Long studentId);
}
