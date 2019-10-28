package com.ruoyi.system.service.impl;


import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.YdCode;
import com.ruoyi.system.mapper.YdCodeMapper;
import com.ruoyi.system.service.IYdCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短信验证码 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
@Service
public class YdCodeServiceImpl implements IYdCodeService
{
	@Autowired
	private YdCodeMapper ydCodeMapper;

	/**
     * 查询短信验证码信息
     * 
     * @param phone 短信验证码ID
     * @return 短信验证码信息
     */
    @Override
	public YdCode selectYdCodeById(String phone)
	{
	    return ydCodeMapper.selectYdCodeById(phone);
	}

	@Override
	public String selectYdCode(String phone) {
		return ydCodeMapper.selectYdCode(phone);
	}

	/**
     * 查询短信验证码列表
     * 
     * @param ydCode 短信验证码信息
     * @return 短信验证码集合
     */
	@Override
	public List<YdCode> selectYdCodeList(YdCode ydCode)
	{
	    return ydCodeMapper.selectYdCodeList(ydCode);
	}
	
    /**
     * 新增短信验证码
     * 
     * @param ydCode 短信验证码信息
     * @return 结果
     */
	@Override
	public int insertYdCode(YdCode ydCode)
	{
	    return ydCodeMapper.insertYdCode(ydCode);
	}
	
	/**
     * 修改短信验证码
     * 
     * @param ydCode 短信验证码信息
     * @return 结果
     */
	@Override
	public int updateYdCode(YdCode ydCode)
	{
	    return ydCodeMapper.updateYdCode(ydCode);
	}

	/**
     * 删除短信验证码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteYdCodeByIds(String ids)
	{
		return ydCodeMapper.deleteYdCodeByIds(Convert.toStrArray(ids));
	}
	
}
