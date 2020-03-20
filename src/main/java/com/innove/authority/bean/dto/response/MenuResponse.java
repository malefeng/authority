package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author dijiangtao
 * @Date 2020-02-20 08:11
 * @Description:
 */
@ApiModel(description = "菜单信息")
@Data
public class MenuResponse {


    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单代码")
    private String menuCode;

    @ApiModelProperty(value = "菜单链接")
    private String menuUrl;

    @ApiModelProperty(value = "附加信息")
    private String meta;

    @ApiModelProperty(value = "子菜单")
    private List<MenuResponse> childrenMenu;
}
