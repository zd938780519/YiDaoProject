package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.User;
import com.ruoyi.system.mapper.UserMapper;
import com.ruoyi.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生 服务层实现
 * 
 * @author yannan
 * @date 2019-01-28
 */
@Service
public class UserServiceImpl implements IUserService
{
	@Autowired
	private UserMapper studentMapper;

	/**
	 * 账号密码登录
	 *
	 * @param username
	 * @return
	 */
	@Override
	public User selectStudentByName(String username) {

		return studentMapper.selectStudentByName(username);
	}

	/**
	 * 检测账号是否存在
	 *
	 * @param username
	 * @return
	 */
	@Override
	public int isExist(String username) {
		return studentMapper.isExist(username);
	}

	/**
	 * 登录前忘记密码
	 * @param student
	 * @return
	 */
	@Override
	public int updatePwd(User student) {
		int flag = studentMapper.updatePwd(student);
		return flag;
	}

	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
    @Override
	public User selectStudentById(Integer id)
	{
	    return studentMapper.selectStudentById(id);
	}
	
	/**
     * 查询学生列表
     * 
     * @param student 学生信息
     * @return 学生集合
     */
	@Override
	public List<User> selectStudentList(User student)
	{
	    return studentMapper.selectStudentList(student);
	}
	
    /**
     * 新增学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	@Override
	public int insertStudent(User student)
	{
	    return studentMapper.insertStudent(student);
	}
	
	/**
     * 修改学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	@Override
	public int updateStudent(User student)
	{
	    return studentMapper.updateStudent(student);
	}

	/**
     * 删除学生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStudentByIds(String ids)
	{
		return studentMapper.deleteStudentByIds(Convert.toStrArray(ids));
	}

	@Override
	public int checkExist(String stuNum) {
		return studentMapper.checkExist(stuNum);
	}

}
