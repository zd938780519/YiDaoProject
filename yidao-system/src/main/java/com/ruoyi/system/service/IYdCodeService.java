package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdCode;
import java.util.List;

/**
 * 短信验证码 服务层
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
public interface IYdCodeService 
{
	/**
     * 查询短信验证码信息
     * 
     * @param phone 短信验证码ID
     * @return 短信验证码信息
     */
	public YdCode selectYdCodeById(String phone);




	/**
	 * 查询短信验证码
	 *
	 * @param phone 短信验证码ID
	 * @return 短信验证码
	 */
	public String selectYdCode(String phone);
	
	/**
     * 查询短信验证码列表
     * 
     * @param ydCode 短信验证码信息
     * @return 短信验证码集合
     */
	public List<YdCode> selectYdCodeList(YdCode ydCode);
	
	/**
     * 新增短信验证码
     * 
     * @param ydCode 短信验证码信息
     * @return 结果
     */
	public int insertYdCode(YdCode ydCode);
	
	/**
     * 修改短信验证码
     * 
     * @param ydCode 短信验证码信息
     * @return 结果
     */
	public int updateYdCode(YdCode ydCode);
		
	/**
     * 删除短信验证码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteYdCodeByIds(String ids);
	
}
