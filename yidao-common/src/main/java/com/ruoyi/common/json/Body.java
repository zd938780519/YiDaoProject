package com.ruoyi.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * json响应中的body
 *
 * @author yannan
 * @date 2018/12/28
 */
public class Body {
	
	/**
	 * 状态码
	 */
	private int code;
	/**
	 * 状态信息
	 */
	private String msg;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	/**
	 * 请求成功
	 */
	public static Body BODY_200 = new Body(200, "SUCCESS");
	/**
	 * 参数验证错误
	 */
	public static Body BODY_400 = new Body(400, "参数验证错误");
	/**
	 * 请求不存在
	 */
	public static Body BODY_404 = new Body(404, "请求不存在");
	/**
	 * token验证错误
	 */
	public static Body BODY_460 = new Body(460, "token验证错误");
	/**
	 * token格式错误
	 */
	public static Body BODY_461 = new Body(461, "token格式错误");
	/**
	 * token过期
	 */
	public static Body BODY_462 = new Body(462, "token过期");
	/**
	 * 服务器内部错误
	 */
	public static Body BODY_500 = new Body(500, "服务器异常");
	/**
	 * 服务器内部错误
	 */
	public static Body BODY_501 = new Body(501, "操作失败");

	public Body() {
		super();
	}

	public Body(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Body(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/*
	 * public static Body newInstance(Meta meta) { return new Body(meta); }
	 */

	public static Body newInstance() { return new Body(200, "SUCCESS"); }
	
	public static Body newInstance(int code, String msg) {
		return new Body(code, msg);
	}
	
	public static Body newInstance(Object data) { return new Body(200, "SUCCESS", data); }

	public Object getData() {
		return data;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
