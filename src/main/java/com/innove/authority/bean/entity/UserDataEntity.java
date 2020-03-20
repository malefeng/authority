package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_user_data
 * @author mlf
 * @Date 2020-2-20
 */
@ApiModel(description = "用户数据权限表")
@Table(name = "t_user_data")
@Data
public class UserDataEntity implements Serializable {
    /**
     * t_user_data.id
     */
    @ApiModelProperty(value = "",example = "0")
    @Id
    private Integer id;

    /**
     * t_user_data.user_code
     */
    @ApiModelProperty(value = "用户编号")
    private String userCode;

    /**
     * t_user_data.org_code
     */
    @ApiModelProperty(value = "机构编号")
    private String orgCode;

    /**
     * t_user_data.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_user_data.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    public UserDataEntity(String userCode, String orgCode, Date createTime, String openUser) {
        this.userCode = userCode;
        this.orgCode = orgCode;
        this.createTime = createTime;
        this.openUser = openUser;
    }

    public UserDataEntity() {
    }

    private static final long serialVersionUID = 1L;
}