package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用户登录请求包")
@Data
public class LoginRequest {

    @ApiModelProperty(value = "账号",dataType = "String",example = "test")
    private String userName;

    @ApiModelProperty(value = "密码",dataType = "String",example = "test")
    private String passWord;

    @ApiModelProperty(value = "验证码",dataType = "String",example = "")
    private String verCode;
}
