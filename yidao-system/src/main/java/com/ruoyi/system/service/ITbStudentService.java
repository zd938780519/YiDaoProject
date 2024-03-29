package com.ruoyi.system.service;

import com.ruoyi.system.domain.TbStudent;
import java.util.List;

/**
 * 学生 服务层
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
public interface ITbStudentService 
{
	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
	public TbStudent selectTbStudentById(Integer id);
	
	/**
     * 查询学生列表
     * 
     * @param tbStudent 学生信息
     * @return 学生集合
     */
	public List<TbStudent> selectTbStudentList(TbStudent tbStudent);
	
	/**
     * 新增学生
     * 
     * @param tbStudent 学生信息
     * @return 结果
     */
	public int insertTbStudent(TbStudent tbStudent);
	
	/**
     * 修改学生
     * 
     * @param tbStudent 学生信息
     * @return 结果
     */
	public int updateTbStudent(TbStudent tbStudent);
		
	/**
     * 删除学生信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbStudentByIds(String ids);
	
}
