package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(description = "账户信息查询响应包")
@Data
public class UserDetailResponse {

    @ApiModelProperty(value = "账户ID",example = "0")
    private Integer id;

    @ApiModelProperty(value = "用户账号")
    private String userCode;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "微信注册码")
    private String userId;

    @ApiModelProperty(value = "用户电话")
    private String userTel;

    @ApiModelProperty(value = "角色编号")
    private List<String> roleCode;

    @ApiModelProperty(value = "用户状态(1-正常 -1-删除)",example = "1")
    private Integer userStatus;

    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否允许登陆（1-是，2-否）")
    private int canLogin;

    @ApiModelProperty(value = "菜单名称列表")
    private List<MenuResponse> menuNames;
}
