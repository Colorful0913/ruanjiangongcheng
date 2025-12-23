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
import com.ruoyi.manage.domain.DormRoom;
import com.ruoyi.manage.service.IDormRoomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宿舍房间Controller
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/room")
public class DormRoomController extends BaseController
{
    @Autowired
    private IDormRoomService dormRoomService;

    /**
     * 查询宿舍房间列表
     */
        @GetMapping("/list")
    public TableDataInfo list(DormRoom dormRoom)
    {
        startPage();
        List<DormRoom> list = dormRoomService.selectDormRoomList(dormRoom);
        return getDataTable(list);
    }

    /**
     * 导出宿舍房间列表
     */
        @Log(title = "宿舍房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DormRoom dormRoom)
    {
        List<DormRoom> list = dormRoomService.selectDormRoomList(dormRoom);
        ExcelUtil<DormRoom> util = new ExcelUtil<DormRoom>(DormRoom.class);
        util.exportExcel(response, list, "宿舍房间数据");
    }

    /**
     * 获取宿舍房间详细信息
     */
        @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(dormRoomService.selectDormRoomByRoomId(roomId));
    }

    /**
     * 新增宿舍房间
     */
        @Log(title = "宿舍房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DormRoom dormRoom)
    {
        return toAjax(dormRoomService.insertDormRoom(dormRoom));
    }

    /**
     * 修改宿舍房间
     */
        @Log(title = "宿舍房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DormRoom dormRoom)
    {
        return toAjax(dormRoomService.updateDormRoom(dormRoom));
    }

    /**
     * 删除宿舍房间
     */
        @Log(title = "宿舍房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(dormRoomService.deleteDormRoomByRoomIds(roomIds));
    }
}
