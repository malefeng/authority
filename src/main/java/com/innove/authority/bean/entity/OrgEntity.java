package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_org
 * @author mlf
 * @Date 2020-02-21
 */
@ApiModel(description = "组织机构表")
@Table(name = "t_org")
@Data
public class OrgEntity implements Serializable {
    /**
     * t_org.id
     */
    @Id
    @ApiModelProperty(value = "",example = "0")
    private Integer id;

    /**
     * t_org.org_code
     */
    @ApiModelProperty(value = "机构编号")
    private String orgCode;

    /**
     * t_org.org_name
     */
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    /**
     * t_org.org_addr
     */
    @ApiModelProperty(value = "机构地址")
    private String orgAddr;

    /**
     * t_org.org_status
     */
    @ApiModelProperty(value = "机构状态(1-正常 ,2-删除)",example = "1")
    private Integer orgStatus;

    /**
     * t_org.org_type
     */
    @ApiModelProperty(value = "机构类型(1-品牌，2-销售大区，3-营销区域，4-代理，5-门店)",example = "1")
    private Integer orgType;

    /**
     * t_org.org_qualit
     */
    @ApiModelProperty(value = "机构性质（1-直营，2-加盟商）",example = "1")
    private Integer orgQualit;

    /**
     * t_org.perm_code
     */
    @ApiModelProperty(value = "")
    private String permCode;

    /**
     * t_org.perm_value
     */
    @ApiModelProperty(value = "")
    private String permValue;

    /**
     * t_org.parent_org_code
     */
    @ApiModelProperty(value = "父级机构编号")
    private String parentOrgCode;

    /**
     * t_org.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_org.update_time
     */
    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    /**
     * t_org.user_code
     */
    @ApiModelProperty(value = "操作用户编号")
    private String userCode;

    /**
     * t_org.remark
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}