package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_menu
 * @author mlf
 * @Date 2020-01-15
 */
@ApiModel(description = "菜单表")
@Table(name = "t_menu")
@Data
public class MenuEntity implements Serializable {
    /**
     * t_menu.id
     */
    @ApiModelProperty(value = "",example = "0")
    @Id
    private Integer id;

    /**
     * t_menu.menu_code
     */
    @ApiModelProperty(value = "菜单编号")
    private String menuCode;

    /**
     * t_menu.menu_name
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * t_menu.menu_url
     */
    @ApiModelProperty(value = "菜单链接")
    private String menuUrl;

    /**
     * t_menu.parent_menu
     */
    @ApiModelProperty(value = "父菜单编号")
    private String parentMenu;

    /**
     * t_menu.menu_status
     */
    @ApiModelProperty(value = "菜单状态(1-正常 -1-删除)",example = "0")
    private Integer menuStatus;

    /**
     * t_menu.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_menu.update_time
     */
    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    /**
     * t_menu.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    /**
     * t_menu.meta
     */
    @ApiModelProperty(value = "附加信息")
    private String meta;

    /**
     * t_menu.remark
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "菜单类别（1-web端，2-企业微信）",example = "0")
    private Integer menuKind;

    private static final long serialVersionUID = 1L;
}