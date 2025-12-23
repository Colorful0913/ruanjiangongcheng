package com.ruoyi.manage.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.domain.DormStudent;
import com.ruoyi.manage.service.IMyInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的信息Controller
 *
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/myInfo")
public class MyInfoController extends BaseController
{
    @Autowired
    private IMyInfoService myInfoService;

    /**
     * 查询我的学生信息
     */
    @GetMapping("/student")
    public AjaxResult getMyStudentInfo()
    {
        DormStudent student = myInfoService.selectMyStudentInfo();
        return success(student);
    }

    /**
     * 查询我的住宿分配信息
     */
    @GetMapping("/allocation")
    public AjaxResult getMyAllocationInfo()
    {
        DormAllocation allocation = myInfoService.selectMyAllocationInfo();
        return success(allocation);
    }

    /**
     * 查询我的完整信息（包含学生信息和住宿信息）
     */
    @GetMapping("/full")
    public AjaxResult getMyFullInfo()
    {
        return success(myInfoService.selectMyFullInfo());
    }
}