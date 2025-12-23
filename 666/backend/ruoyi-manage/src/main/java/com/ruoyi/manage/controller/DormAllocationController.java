package com.ruoyi.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.manage.domain.DormBuilding;
import com.ruoyi.manage.domain.DormRoom;
import com.ruoyi.manage.service.IDormBuildingService;
import com.ruoyi.manage.service.IDormRoomService;
import com.ruoyi.manage.service.IDormStudentService;
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
import com.ruoyi.manage.domain.DormAllocation;
import com.ruoyi.manage.service.IDormAllocationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住宿分配Controller
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/allocation")
public class DormAllocationController extends BaseController
{
    @Autowired
    private IDormAllocationService dormAllocationService;
    // 需要添加依赖注入
    @Autowired
    private IDormBuildingService dormBuildingService;

    @Autowired
    private IDormRoomService dormRoomService;

    // 添加学生服务注入
    @Autowired
    private IDormStudentService dormStudentService;



    // 添加获取可用楼栋列表接口
    @GetMapping("/buildings/available")
    public AjaxResult getAvailableBuildings() {
        DormBuilding building = new DormBuilding();
        building.setStatus("0"); // 只查询正常状态的楼栋
        List<DormBuilding> list = dormBuildingService.selectDormBuildingList(building);
        return AjaxResult.success(list);
    }

    // 添加根据楼栋ID获取可用房间列表接口
    @GetMapping("/rooms/available/{buildingId}")
    public AjaxResult getAvailableRoomsByBuildingId(@PathVariable("buildingId") Long buildingId) {
        DormRoom room = new DormRoom();
        room.setBuildingId(buildingId);
        room.setStatus("0"); // 只查询可入住状态的房间
        List<DormRoom> list = dormRoomService.selectDormRoomList(room);
        return AjaxResult.success(list);
    }




    /**
     * 查询住宿分配列表
     */
        @GetMapping("/list")
    public TableDataInfo list(DormAllocation dormAllocation)
    {
        startPage();
        List<DormAllocation> list = dormAllocationService.selectDormAllocationList(dormAllocation);
        return getDataTable(list);
    }

    /**
     * 导出住宿分配列表
     */
        @Log(title = "住宿分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DormAllocation dormAllocation)
    {
        List<DormAllocation> list = dormAllocationService.selectDormAllocationList(dormAllocation);
        ExcelUtil<DormAllocation> util = new ExcelUtil<DormAllocation>(DormAllocation.class);
        util.exportExcel(response, list, "住宿分配数据");
    }

    /**
     * 获取住宿分配详细信息
     */
        @GetMapping(value = "/{allocationId}")
    public AjaxResult getInfo(@PathVariable("allocationId") Long allocationId)
    {
        return success(dormAllocationService.selectDormAllocationByAllocationId(allocationId));
    }

    /**
     * 新增住宿分配
     */
        @Log(title = "住宿分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DormAllocation dormAllocation)
    {
        return toAjax(dormAllocationService.insertDormAllocation(dormAllocation));
    }

    /**
     * 修改住宿分配
     */
        @Log(title = "住宿分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DormAllocation dormAllocation)
    {
        return toAjax(dormAllocationService.updateDormAllocation(dormAllocation));
    }

    /**
     * 删除住宿分配
     */
        @Log(title = "住宿分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{allocationIds}")
    public AjaxResult remove(@PathVariable Long[] allocationIds)
    {
        return toAjax(dormAllocationService.deleteDormAllocationByAllocationIds(allocationIds));
    }
}
