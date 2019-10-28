package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.TbStudent;
import com.ruoyi.system.mapper.TbStudentMapper;
import com.ruoyi.system.service.ITbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
@Service
public class TbStudentServiceImpl implements ITbStudentService 
{
	@Autowired
	private TbStudentMapper tbStudentMapper;

	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
    @Override
	public TbStudent selectTbStudentById(Integer id)
	{
	    return tbStudentMapper.selectTbStudentById(id);
	}
	
	/**
     * 查询学生列表
     * 
     * @param tbStudent 学生信息
     * @return 学生集合
     */
	@Override
	public List<TbStudent> selectTbStudentList(TbStudent tbStudent)
	{
	    return tbStudentMapper.selectTbStudentList(tbStudent);
	}
	
    /**
     * 新增学生
     * 
     * @param tbStudent 学生信息
     * @return 结果
     */
	@Override
	public int insertTbStudent(TbStudent tbStudent)
	{
	    return tbStudentMapper.insertTbStudent(tbStudent);
	}
	
	/**
     * 修改学生
     * 
     * @param tbStudent 学生信息
     * @return 结果
     */
	@Override
	public int updateTbStudent(TbStudent tbStudent)
	{
	    return tbStudentMapper.updateTbStudent(tbStudent);
	}

	/**
     * 删除学生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTbStudentByIds(String ids)
	{
		return tbStudentMapper.deleteTbStudentByIds(Convert.toStrArray(ids));
	}
	
}
