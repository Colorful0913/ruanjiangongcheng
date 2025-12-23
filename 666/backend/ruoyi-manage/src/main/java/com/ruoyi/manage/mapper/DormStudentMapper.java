package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.DormStudent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生信息扩展Mapper接口
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Mapper
public interface DormStudentMapper 
{
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
     * 删除学生信息扩展
     * 
     * @param studentId 学生信息扩展主键
     * @return 结果
     */
    public int deleteDormStudentByStudentId(Long studentId);

    /**
     * 批量删除学生信息扩展
     * 
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDormStudentByStudentIds(Long[] studentIds);
}
