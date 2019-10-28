package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学生表 tb_student
 * 
 * @author yannan
 * @date 2019-01-28
 */
public class User extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 学号 */
	@Excel(name = "学号")
	private String stuNum;
	/** 用户名 */
	@Excel(name = "用户名")
	private String username;
	/** 密码 */
	private String password;
	/** 头像 */
	private String headImg;
	/** 真实姓名 */
	@Excel(name = "真实姓名")
	private String realName;
	/** 性别 */
	@Excel(name = "性别",readConverterExp = "0=男,1=女")
	private Integer sex;
	/** 手机号 */
	@Excel(name = "手机号")
	private String phone;
	/** 学部 */
	@Excel(name = "学部",readConverterExp = "0=国内部,1=国际部")
	private Integer department;
	/** 年级id */
	private Integer gradeId;
	/** 年级名 */
	@Excel(name = "年级名")
	private String gradeName;
	/** 创建时间 */
	@Excel(name = "创建时间")
	private Date createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setStuNum(String stuNum) 
	{
		this.stuNum = stuNum;
	}

	public String getStuNum() 
	{
		return stuNum;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getUsername() 
	{
		return username;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setHeadImg(String headImg) 
	{
		this.headImg = headImg;
	}

	public String getHeadImg() 
	{
		return headImg;
	}
	public void setRealName(String realName) 
	{
		this.realName = realName;
	}

	public String getRealName() 
	{
		return realName;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	public Integer getDepartment()
	{
		return department;
	}
	public void setGradeId(Integer gradeId) 
	{
		this.gradeId = gradeId;
	}

	public Integer getGradeId() 
	{
		return gradeId;
	}
	public void setGradeName(String gradeName) 
	{
		this.gradeName = gradeName;
	}

	public String getGradeName() 
	{
		return gradeName;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stuNum", getStuNum())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("headImg", getHeadImg())
            .append("realName", getRealName())
            .append("sex", getSex())
            .append("phone", getPhone())
            .append("department", getDepartment())
            .append("gradeId", getGradeId())
            .append("gradeName", getGradeName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
