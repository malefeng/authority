package com.innove.authority.bean.dto.request;

import com.innove.authority.bean.entity.OrgEntity;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "机构表")
@Data
public class OrgSaveRequest extends OrgEntity implements Serializable {

    @Ignore
    private Integer id;

    private static final long serialVersionUID = 1L;

}