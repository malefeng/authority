package com.innove.authority.bean.dto.response;

import com.innove.authority.bean.entity.OrgEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "组织机构列表")
@Data
public class OrgListResponse extends OrgEntity {

    @ApiModelProperty(value = "子机构列表")
    private List<OrgEntity> childrenList;

}
