package com.innove.authority.bean.enums;

/**  
* @ClassName: RuntimeErrorEnum.java  
* @Description: 运行异常enum
* @author paradise  
* @date 2019年8月30日    
*/
public enum RuntimeErrorEnum {
	
	/*
	 * code规则如下：必须为非0开头的数字
	 * 第一位：2-数据交换服务错误 3-数据业务服务错误 9-通用
	 * 第二位：0-数据交换service 1-数据业务service
	 * 第三、四、五位：001-999错误编码
	 */

	SUCCESS(200, "请求成功"),
	FAILED(-100, "请求失败"),
	ERROR(65336, "服务端错误"),

	ROLE_NOT_NULL_ERROR(31036,"未找到角色信息"),
	ROLE_RELATI_USER_NULL(31037,"存在使用中的人员，无法删除角色信息"),

	REQUEST_PARAM_ERROR(90001,"请求参数错误"),
	OPERATE_FAIL(90002,"操作失败"),
	INVALID_USER_NAME_ERROR(90003,"账号无效"),
	INVALID_PSW_ERROR(90004,"密码无效"),
	INVALID_VER_CODE_ERROR(90005,"验证码无效"),
	INVALID_UNLOGIN_ERROR(80001,"当前未登录"),
	UNAUTH_ERROR(90007,"无访问权限"),
	WECHAT_SERVER_ERROR(90008,"微信数据获取异常"),
	WECHAT_RESPONSE_ERROR(90009,"微信服务返回信息异常"),
	WECHAT_GET_ID_ERROR(90010,"获取微信账户信息异常"),
	NAME_NOT_NULL(90011,"用户信息不存在，登陆失败"),
	USER_CANNOT_LOGIN(90012,"用户无权登陆"),
	WECHAT_ENTER_CODE_INVALID(90013,"企业代码无效"),
	SYSTEM_ERROR(99999,"系统错误");
	
	private int code;
	
	private String msg;

	RuntimeErrorEnum(final int code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static RuntimeErrorEnum fromValue(String value) {
		for (RuntimeErrorEnum at : RuntimeErrorEnum.values()) {
			if (at.getMsg().equals(value)) {
				return at;
			}
		}
		return null;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
