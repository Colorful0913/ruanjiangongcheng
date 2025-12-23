package com.ruoyi.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.manage.domain.DormRepair;
import com.ruoyi.manage.service.IMyRepairService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的报修Controller
 *
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/myRepair")
public class MyRepairController extends BaseController
{
    @Autowired
    private IMyRepairService myRepairService;

    /**
     * 查询我的报修列表
     */
    @GetMapping("/list")
    public TableDataInfo list(DormRepair dormRepair)
    {
        startPage();
        List<DormRepair> list = myRepairService.selectMyRepairList(dormRepair);
        return getDataTable(list);
    }

    /**
     * 获取我的报修详细信息
     */
    @GetMapping(value = "/{repairId}")
    public AjaxResult getInfo(@PathVariable("repairId") Long repairId)
    {
        // 验证该报修记录是否属于当前用户
        DormRepair dormRepair = myRepairService.selectMyRepairByRepairId(repairId);
        if (dormRepair == null) {
            return error("报修记录不存在或无权访问");
        }
        return success(dormRepair);
    }

    /**
     * 新增我的报修
     */
    @Log(title = "我的报修", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DormRepair dormRepair)
    {
        // 设置报修人为当前用户
        dormRepair.setReportUserId(getUserId());
        return toAjax(myRepairService.insertMyRepair(dormRepair));
    }

    /**
     * 修改我的报修（只能修改自己的报修）
     */
    @Log(title = "我的报修", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DormRepair dormRepair)
    {
        // 验证该报修记录是否属于当前用户
        DormRepair existingRepair = myRepairService.selectMyRepairByRepairId(dormRepair.getRepairId());
        if (existingRepair == null) {
            return error("报修记录不存在或无权修改");
        }
        return toAjax(myRepairService.updateMyRepair(dormRepair));
    }

    /**
     * 删除我的报修（只能删除自己的报修）
     */
    @Log(title = "我的报修", businessType = BusinessType.DELETE)
    @DeleteMapping("/{repairIds}")
    public AjaxResult remove(@PathVariable Long[] repairIds)
    {
        // 验证所有要删除的报修记录是否都属于当前用户
        for (Long repairId : repairIds) {
            DormRepair existingRepair = myRepairService.selectMyRepairByRepairId(repairId);
            if (existingRepair == null) {
                return error("报修记录不存在或无权删除");
            }
        }
        return toAjax(myRepairService.deleteMyRepairByRepairIds(repairIds));
    }
}