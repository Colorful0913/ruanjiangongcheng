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
import com.ruoyi.manage.service.IDormRepairService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 维修报修Controller
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/repair")
public class DormRepairController extends BaseController
{
    @Autowired
    private IDormRepairService dormRepairService;

    /**
     * 查询维修报修列表
     */
        @GetMapping("/list")
    public TableDataInfo list(DormRepair dormRepair)
    {
        startPage();
        List<DormRepair> list = dormRepairService.selectDormRepairList(dormRepair);
        return getDataTable(list);
    }

    /**
     * 导出维修报修列表
     */
        @Log(title = "维修报修", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DormRepair dormRepair)
    {
        List<DormRepair> list = dormRepairService.selectDormRepairList(dormRepair);
        ExcelUtil<DormRepair> util = new ExcelUtil<DormRepair>(DormRepair.class);
        util.exportExcel(response, list, "维修报修数据");
    }

    /**
     * 获取维修报修详细信息
     */
        @GetMapping(value = "/{repairId}")
    public AjaxResult getInfo(@PathVariable("repairId") Long repairId)
    {
        return success(dormRepairService.selectDormRepairByRepairId(repairId));
    }

    /**
     * 新增维修报修
     */
        @Log(title = "维修报修", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DormRepair dormRepair)
    {
        return toAjax(dormRepairService.insertDormRepair(dormRepair));
    }

    /**
     * 修改维修报修
     */
        @Log(title = "维修报修", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DormRepair dormRepair)
    {
        return toAjax(dormRepairService.updateDormRepair(dormRepair));
    }

    /**
     * 删除维修报修
     */
        @Log(title = "维修报修", businessType = BusinessType.DELETE)
	@DeleteMapping("/{repairIds}")
    public AjaxResult remove(@PathVariable Long[] repairIds)
    {
        return toAjax(dormRepairService.deleteDormRepairByRepairIds(repairIds));
    }
}
