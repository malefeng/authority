package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_enter_wechat
 * @author mlf
 * @Date 2020-03-06
 */
@ApiModel(description = "")
@Data
public class EnterWechatEntity implements Serializable {
    /**
     * t_enter_wechat.id
     */
    @ApiModelProperty(value = "",example = "0")
    private Integer id;

    /**
     * t_enter_wechat.enter_code
     */
    @ApiModelProperty(value = "企业微信编号")
    private String enterCode;

    /**
     * t_enter_wechat.enter_name
     */
    @ApiModelProperty(value = "企业名称")
    private String enterName;

    /**
     * t_enter_wechat.corp_id
     */
    @ApiModelProperty(value = "授权方企业微信id")
    private String corpId;

    /**
     * t_enter_wechat.corp_secret
     */
    @ApiModelProperty(value = "授权方企业微信密钥")
    private String corpSecret;

    /**
     * t_enter_wechat.agent_id
     */
    @ApiModelProperty(value = "授权方应用id")
    private String agentId;

    /**
     * t_enter_wechat.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_enter_wechat.update_time
     */
    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    /**
     * t_enter_wechat.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    /**
     * t_enter_wechat.remark
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}