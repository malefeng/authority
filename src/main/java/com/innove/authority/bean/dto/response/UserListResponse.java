package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "账户列表信息查询响应包")
public class UserListResponse {

    @ApiModelProperty(value = "账户ID",example = "0")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userCode;

    @ApiModelProperty(value = "角色")
    private List<String> roleName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户电话")
    private String userTel;

    @ApiModelProperty(value = "用户状态(1-正常 -2-删除)",example = "1")
    private Integer userStatus;

}
