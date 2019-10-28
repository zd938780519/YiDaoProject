package com.ruoyi.system.service;


import com.ruoyi.system.domain.User;

import java.util.List;

/**
 * 学生 服务层
 * 
 * @author yannan
 * @date 2019-01-28
 */
public interface IUserService
{
	/**
	 * 账号密码登录
	 * @param username
	 * @return
	 */
	public User selectStudentByName(String username);

	/**
	 * 检测账号是否存在
	 * @param username
	 * @return
	 */
	public int isExist(String username);

	/**
	 * 登录前忘记密码
	 * @param stuNum
	 * @param realName
	 * @param passwordNew
	 * @return
	 */
	public int updatePwd(User student);

	/**
     * 查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
	public User selectStudentById(Integer id);
	
	/**
     * 查询学生列表
     * 
     * @param student 学生信息
     * @return 学生集合
     */
	public List<User> selectStudentList(User student);
	
	/**
     * 新增学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int insertStudent(User student);
	
	/**
     * 修改学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int updateStudent(User student);
		
	/**
     * 删除学生信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStudentByIds(String ids);

    public int checkExist(String stuNum);

}
