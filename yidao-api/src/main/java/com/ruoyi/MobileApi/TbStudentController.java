package com.ruoyi.MobileApi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TbStudent;
import com.ruoyi.system.service.ITbStudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
@Controller
@CrossOrigin
@RequestMapping("/system/tbStudent")
public class TbStudentController extends BaseController
{
    private String prefix = "system/tbStudent";
	
	@Autowired
	private ITbStudentService tbStudentService;
	
	@RequiresPermissions("system:tbStudent:view")
	@GetMapping()
	public String tbStudent()
	{
	    return prefix + "/tbStudent";
	}
	
	/**
	 * 查询学生列表
	 */
//	@RequiresPermissions("system:tbStudent:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbStudent tbStudent)
	{
		startPage();
        List<TbStudent> list = tbStudentService.selectTbStudentList(tbStudent);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出学生列表
	 */
//	@RequiresPermissions("system:tbStudent:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbStudent tbStudent)
    {
    	List<TbStudent> list = tbStudentService.selectTbStudentList(tbStudent);
        ExcelUtil<TbStudent> util = new ExcelUtil<TbStudent>(TbStudent.class);
        return util.exportExcel(list, "tbStudent");
    }
	
	/**
	 * 新增学生
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存学生
	 */
	@RequiresPermissions("system:tbStudent:add")
	@Log(title = "学生", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TbStudent tbStudent)
	{		
		return toAjax(tbStudentService.insertTbStudent(tbStudent));
	}

	/**
	 * 修改学生
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TbStudent tbStudent = tbStudentService.selectTbStudentById(id);
		mmap.put("tbStudent", tbStudent);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存学生
	 */
	@RequiresPermissions("system:tbStudent:edit")
	@Log(title = "学生", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbStudent tbStudent)
	{		
		return toAjax(tbStudentService.updateTbStudent(tbStudent));
	}
	
	/**
	 * 删除学生
	 */
	@RequiresPermissions("system:tbStudent:remove")
	@Log(title = "学生", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbStudentService.deleteTbStudentByIds(ids));
	}
	
}
