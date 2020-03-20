package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "删除角色-菜单信息请求包")
@Data
public class RoleMenuDeleteRequest {

    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    @ApiModelProperty(value = "菜单编号列表")
    private List<String> menuCodes;
}
