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
import com.ruoyi.manage.domain.DormStudent;
import com.ruoyi.manage.service.IDormStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生信息扩展Controller
 * 
 * @author 余杨俊
 * @date 2025-10-16
 */
@RestController
@RequestMapping("/manage/student")
public class DormStudentController extends BaseController
{
    @Autowired
    private IDormStudentService dormStudentService;

    /**
     * 查询学生信息扩展列表
     */
        @GetMapping("/list")
    public TableDataInfo list(DormStudent dormStudent)
    {
        startPage();
        List<DormStudent> list = dormStudentService.selectDormStudentList(dormStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生信息扩展列表
     */
        @Log(title = "学生信息扩展", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DormStudent dormStudent)
    {
        List<DormStudent> list = dormStudentService.selectDormStudentList(dormStudent);
        ExcelUtil<DormStudent> util = new ExcelUtil<DormStudent>(DormStudent.class);
        util.exportExcel(response, list, "学生信息扩展数据");
    }

    /**
     * 获取学生信息扩展详细信息
     */
        @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return success(dormStudentService.selectDormStudentByStudentId(studentId));
    }

    /**
     * 新增学生信息扩展
     */
        @Log(title = "学生信息扩展", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DormStudent dormStudent)
    {
        return toAjax(dormStudentService.insertDormStudent(dormStudent));
    }

    /**
     * 修改学生信息扩展
     */
        @Log(title = "学生信息扩展", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DormStudent dormStudent)
    {
        return toAjax(dormStudentService.updateDormStudent(dormStudent));
    }

    /**
     * 删除学生信息扩展
     */
        @Log(title = "学生信息扩展", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(dormStudentService.deleteDormStudentByStudentIds(studentIds));
    }
}
