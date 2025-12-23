package com.ruoyi.manage.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.mapper.DormAllocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.DormStudentMapper;
import com.ruoyi.manage.domain.DormStudent;
import com.ruoyi.manage.service.IDormStudentService;



/**
 * 学生信息扩展Service业务层处理
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@Service
public class DormStudentServiceImpl implements IDormStudentService 
{
    @Autowired
    private DormStudentMapper dormStudentMapper;
    @Autowired
    private DormAllocationMapper dormAllocationMapper;


    /**
     * 根据学生学号查询学生信息
     *
     * @param studentNumber 学生学号
     * @return 学生信息
     */
//    @Override
//    public DormStudent selectDormStudentByStudentNumber(String studentNumber) {
//        DormStudent dormStudent = new DormStudent();
//        dormStudent.setStudentNumber(studentNumber);
//        // 查询条件设置
//        List<DormStudent> list = dormStudentMapper.selectDormStudentList(dormStudent);
//        return list.isEmpty() ? null : list.get(0);
//    }

    @Override
    public DormStudent selectDormStudentByStudentNumber(String studentNumber) {
        DormStudent dormStudent = new DormStudent();
        dormStudent.setStudentNumber(studentNumber);
        List<DormStudent> list = dormStudentMapper.selectDormStudentList(dormStudent);
        return list.isEmpty() ? null : list.get(0);
    }



    /**
     * 查询学生信息扩展
     * 
     * @param studentId 学生信息扩展主键
     * @return 学生信息扩展
     */
    @Override
    public DormStudent selectDormStudentByStudentId(Long studentId)
    {
        return dormStudentMapper.selectDormStudentByStudentId(studentId);
    }

    /**
     * 查询学生信息扩展列表
     * 
     * @param dormStudent 学生信息扩展
     * @return 学生信息扩展
     */
    @Override
    public List<DormStudent> selectDormStudentList(DormStudent dormStudent)
    {
        return dormStudentMapper.selectDormStudentList(dormStudent);
    }

    /**
     * 新增学生信息扩展
     * 
     * @param dormStudent 学生信息扩展
     * @return 结果
     */
    @Override
    public int insertDormStudent(DormStudent dormStudent)
    {
        // 检查学号是否已存在
        if (dormStudent.getStudentNumber() != null && !dormStudent.getStudentNumber().isEmpty()) {
            DormStudent existingStudent = selectDormStudentByStudentNumber(dormStudent.getStudentNumber());
            if (existingStudent != null) {
                throw new ServiceException("学号 " + dormStudent.getStudentNumber() + " 已存在，请勿重复添加");
            }
        }


        // 检查用户ID是否已存在
        if (dormStudent.getUserId() != null) {
            DormStudent query = new DormStudent();
            query.setUserId(dormStudent.getUserId());
            List<DormStudent> existingStudents = dormStudentMapper.selectDormStudentList(query);
            if (!existingStudents.isEmpty()) {
                throw new ServiceException("用户ID " + dormStudent.getUserId() + " 已存在对应的学生信息，请勿重复添加");
            }
        }


        dormStudent.setCreateTime(DateUtils.getNowDate());
        return dormStudentMapper.insertDormStudent(dormStudent);
    }

    /**
     * 修改学生信息扩展
     * 
     * @param dormStudent 学生信息扩展
     * @return 结果
     */
    @Override
    public int updateDormStudent(DormStudent dormStudent)
    {

        // 检查学号是否已存在（排除自己）
        if (dormStudent.getStudentNumber() != null && !dormStudent.getStudentNumber().isEmpty()) {
            DormStudent existingStudent = selectDormStudentByStudentNumber(dormStudent.getStudentNumber());
            if (existingStudent != null && !existingStudent.getStudentId().equals(dormStudent.getStudentId())) {
                throw new ServiceException("学号 " + dormStudent.getStudentNumber() + " 已存在，请勿重复添加");
            }
        }

        // 检查用户ID是否已存在（排除自己）
        if (dormStudent.getUserId() != null) {
            DormStudent query = new DormStudent();
            query.setUserId(dormStudent.getUserId());
            List<DormStudent> existingStudents = dormStudentMapper.selectDormStudentList(query);
            if (!existingStudents.isEmpty() && !existingStudents.get(0).getStudentId().equals(dormStudent.getStudentId())) {
                throw new ServiceException("用户ID " + dormStudent.getUserId() + " 已存在对应的学生信息，请勿重复添加");
            }
        }


        dormStudent.setUpdateTime(DateUtils.getNowDate());
        return dormStudentMapper.updateDormStudent(dormStudent);
    }

//    /**
//     * 批量删除学生信息扩展
//     *
//     * @param studentIds 需要删除的学生信息扩展主键
//     * @return 结果
//     */
//    @Override
//    public int deleteDormStudentByStudentIds(Long[] studentIds)
//    {
//        return dormStudentMapper.deleteDormStudentByStudentIds(studentIds);
//    }


    /**
     * 批量删除学生信息扩展
     *
     * @param studentIds 需要删除的学生信息扩展主键
     * @return 结果
     */
    @Override
    public int deleteDormStudentByStudentIds(Long[] studentIds)
    {
        // 检查是否有学生存在住宿分配记录
        for (Long studentId : studentIds) {
            // 查询该学生是否有住宿分配记录
            DormAllocation query = new DormAllocation();
            query.setStudentId(studentId);
            List<DormAllocation> allocations = dormAllocationMapper.selectDormAllocationList(query);

            if (!allocations.isEmpty()) {
                // 获取学生信息以显示学号
                DormStudent student = dormStudentMapper.selectDormStudentByStudentId(studentId);
                String studentInfo = student != null ? student.getStudentNumber() : studentId.toString();
                throw new ServiceException("无法删除学号为 " + studentInfo + " 的学生信息，该学生存在住宿分配记录，请先删除相关住宿分配记录");
            }
        }

        return dormStudentMapper.deleteDormStudentByStudentIds(studentIds);
    }





    /**
     * 删除学生信息扩展信息
     * 
     * @param studentId 学生信息扩展主键
     * @return 结果
     */
    @Override
    public int deleteDormStudentByStudentId(Long studentId)
    {
        return dormStudentMapper.deleteDormStudentByStudentId(studentId);
    }
}
