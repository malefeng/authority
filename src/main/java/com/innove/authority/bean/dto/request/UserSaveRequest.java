package com.innove.authority.bean.dto.request;

import com.innove.authority.bean.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "新增账户信息请求包")
@Data
public class UserSaveRequest extends UserEntity {
    @ApiModelProperty(value = "角色编号")
    private List<String> roleCode;
}
