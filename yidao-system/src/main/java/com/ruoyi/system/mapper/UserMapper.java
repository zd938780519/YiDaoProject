package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.User;

import java.util.List;

/**
 * 学生 数据层
 * 
 * @author yannan
 * @date 2019-01-28
 */
public interface UserMapper
{
	/**
     * id查询学生信息
     * 
     * @param id 学生ID
     * @return 学生信息
     */
	public User selectStudentById(Integer id);

	/**
	 * 账号查询学生信息
	 *
	 * @param username 学生账号
	 * @return 学生信息
	 */
	public User selectStudentByName(String username);

	/**
	 * 账号查询学生信息
	 *
	 * @param stuNum 学号
	 * @param stuNum 真实姓名
	 * @return 学生信息
	 */
	public User selectStudentBy(String stuNum, String realName);

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
	 * 检测账号是否存在
	 * @param username
	 * @return
	 */
	public int isExist(String username);

	/**
	 * 修改密码
	 * @param student
	 * @return
	 */
	public int updatePwd(User student);

	/**
     * 修改学生
     * 
     * @param student 学生信息
     * @return 结果
     */
	public int updateStudent(User student);
	
	/**
     * 删除学生
     * 
     * @param id 学生ID
     * @return 结果
     */
	public int deleteStudentById(Integer id);
	
	/**
     * 批量删除学生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStudentByIds(String[] ids);

	public int checkExist(String stuNum);

}