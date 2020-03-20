package com.innove.authority.bean.dto.response;

import com.innove.authority.bean.entity.RoleEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "角色信息查询响应包")
@Data
public class RoleDetailResponse extends RoleEntity {

    @ApiModelProperty(value = "菜单信息列表")
    private List<String> menuCodes;
}
