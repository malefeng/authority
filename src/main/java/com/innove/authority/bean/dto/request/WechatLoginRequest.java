package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "微信登录请求包")
@Data
public class WechatLoginRequest {

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "state")
    private String state;

    @ApiModelProperty(value = "企业代码")
    private String enterCode;
}
