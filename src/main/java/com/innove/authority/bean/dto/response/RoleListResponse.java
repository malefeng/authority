package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "角色列表信息查询响应包")
public class RoleListResponse {

    @ApiModelProperty(value = "角色信息ID",example = "0")
    private Integer id;

    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;
}
