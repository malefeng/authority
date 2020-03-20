package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "组织机构权限数据列表")
@Data
public class OrgListOfUserResponse {

    @ApiModelProperty(value = "机构编号")
    private String orgCode;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "机构类型(1-品牌，2-销售大区，3-营销区域，4-代理，5-门店)",example = "1")
    private Integer orgType;

    @ApiModelProperty(value = "机构性质（1-直营，2-加盟商）",example = "1")
    private Integer orgQualit;

    @ApiModelProperty(value = "")
    private String permCode;

    @ApiModelProperty(value = "")
    private String permValue;

    @ApiModelProperty(value = "父级机构编号")
    private String parentOrgCode;
}
