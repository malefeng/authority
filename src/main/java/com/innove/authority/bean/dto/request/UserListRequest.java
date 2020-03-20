package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "账户信息列表查询请求包")
@Data
public class UserListRequest extends QueryListWithPageRequest {

    @ApiModelProperty(value = "角色代码")
    private String roleCode;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "用户名-账号")
    private String userCode;

    @ApiModelProperty(value = "用户状态(1-正常 -2-删除)",example = "1")
    private Integer userStatus;
}
