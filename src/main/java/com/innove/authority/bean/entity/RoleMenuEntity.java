package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_role_menu
 * @author mlf
 * @Date 2020-01-03
 */
@ApiModel(description = "角色菜单权限表")
@Table(name = "t_role_menu")
@Data
public class RoleMenuEntity implements Serializable {
    /**
     * t_role_menu.id
     */
    @ApiModelProperty(value = "",example = "0")
    @Id
    private Integer id;

    /**
     * t_role_menu.role_code
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    /**
     * t_role_menu.menu_code
     */
    @ApiModelProperty(value = "菜单编号")
    private String menuCode;

    /**
     * t_role_menu.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_role_menu.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    private static final long serialVersionUID = 1L;

    public RoleMenuEntity() {
    }

    public RoleMenuEntity(String roleCode, String menuCode, Date createTime, String openUser) {
        this.roleCode = roleCode;
        this.menuCode = menuCode;
        this.createTime = createTime;
        this.openUser = openUser;
    }

    public boolean compare(RoleMenuEntity roleMenuEntity){
        if(this.getMenuCode()==null||this.getRoleCode()==null||
                roleMenuEntity.getMenuCode()==null||roleMenuEntity.getRoleCode()==null){
            return false;
        }
        return this.getMenuCode().equals(roleMenuEntity.getMenuCode())&&this.getRoleCode().equals(roleMenuEntity.getRoleCode());
    }
}