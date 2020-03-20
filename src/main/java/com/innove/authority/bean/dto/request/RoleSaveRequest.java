package com.innove.authority.bean.dto.request;

import com.innove.authority.bean.entity.RoleEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "新增角色信息请求包")
@Data
public class RoleSaveRequest {

    @ApiModelProperty(value = "角色信息")
    private RoleEntity role;

    @ApiModelProperty(value = "菜单代码列表")
    private List<String> menuCodes;
}
