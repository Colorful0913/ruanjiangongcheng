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
import com.ruoyi.manage.domain.DormBuilding;
import com.ruoyi.manage.service.IDormBuildingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宿舍楼Controller
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/building")
public class DormBuildingController extends BaseController
{
    @Autowired
    private IDormBuildingService dormBuildingService;

    /**
     * 查询宿舍楼列表
     */
        @GetMapping("/list")
    public TableDataInfo list(DormBuilding dormBuilding)
    {
        startPage();
        List<DormBuilding> list = dormBuildingService.selectDormBuildingList(dormBuilding);
        return getDataTable(list);
    }

    /**
     * 导出宿舍楼列表
     */
        @Log(title = "宿舍楼", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DormBuilding dormBuilding)
    {
        List<DormBuilding> list = dormBuildingService.selectDormBuildingList(dormBuilding);
        ExcelUtil<DormBuilding> util = new ExcelUtil<DormBuilding>(DormBuilding.class);
        util.exportExcel(response, list, "宿舍楼数据");
    }

    /**
     * 获取宿舍楼详细信息
     */
        @GetMapping(value = "/{buildingId}")
    public AjaxResult getInfo(@PathVariable("buildingId") Long buildingId)
    {
        return success(dormBuildingService.selectDormBuildingByBuildingId(buildingId));
    }

    /**
     * 新增宿舍楼
     */
        @Log(title = "宿舍楼", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DormBuilding dormBuilding)
    {
        return toAjax(dormBuildingService.insertDormBuilding(dormBuilding));
    }

    /**
     * 修改宿舍楼
     */
        @Log(title = "宿舍楼", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DormBuilding dormBuilding)
    {
        return toAjax(dormBuildingService.updateDormBuilding(dormBuilding));
    }

    /**
     * 删除宿舍楼
     */
        @Log(title = "宿舍楼", businessType = BusinessType.DELETE)
	@DeleteMapping("/{buildingIds}")
    public AjaxResult remove(@PathVariable Long[] buildingIds)
    {
        return toAjax(dormBuildingService.deleteDormBuildingByBuildingIds(buildingIds));
    }
}
