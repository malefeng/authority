package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_user_role
 * @author mlf
 * @Date 2020-03-05
 */
@ApiModel(description = "")
@Table(name="t_user_role")
@Data
public class UserRoleEntity implements Serializable {
    /**
     * t_user_role.id
     */
    @ApiModelProperty(value = "",example = "0")
    private Integer id;

    /**
     * t_user_role.user_code
     */
    @ApiModelProperty(value = "用户账号")
    private String userCode;

    /**
     * t_user_role.role_code
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    /**
     * t_user_role.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_user_role.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    private static final long serialVersionUID = 1L;
}