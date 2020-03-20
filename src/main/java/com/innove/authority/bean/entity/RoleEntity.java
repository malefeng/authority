package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_role
 * @author mlf
 * @Date 2020-01-15
 */
@ApiModel(description = "角色表")
@Table(name = "t_role")
@Data
public class RoleEntity implements Serializable {
    /**
     * t_role.id
     */
    @ApiModelProperty(value = "",example = "0")
    @Id
    private Integer id;

    /**
     * t_role.role_code
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    /**
     * t_role.role_name
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * t_role.role_status
     */
    @ApiModelProperty(value = "角色状态(1-正常 -2-删除)",example = "1")
    private Integer roleStatus;

    /**
     * t_role.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_role.update_time
     */
    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    /**
     * t_role.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    /**
     * t_role.remark
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}