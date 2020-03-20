package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**  
* @ClassName: Request.java  
* @Description: 请求封装类
* @author paradise  
* @date 2019年8月30日    
*/
@ApiModel(description="请求包")
public class Request implements Serializable{

	private static final long serialVersionUID = -2235317308621950617L;
	
	@ApiModelProperty(value = "请求序列号，格式为：yyMMddHHmmssSSS + 6位随机数字")
	private String serialNumber;
	
	@ApiModelProperty(value = "客户端版本号")
	private String clientVersion;
	
	@ApiModelProperty(value = "客户端类型 1：lis  2：其他")
	private String clientType;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

}
