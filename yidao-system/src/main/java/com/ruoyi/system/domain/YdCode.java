package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 短信验证码表 yd_code
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
public class YdCode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 手机号 */
	private String phone;
	/** 验证码 */
	private String mCode;
	/**  */
	private Integer id;

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setMCode(String mCode) 
	{
		this.mCode = mCode;
	}

	public String getMCode() 
	{
		return mCode;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phone", getPhone())
            .append("mCode", getMCode())
            .append("id", getId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
