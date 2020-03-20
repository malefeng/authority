package com.innove.authority.bean.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innove.authority.bean.entity.UserRoleEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "账户角色关系删除请求包")
@Data
public class UserRoleDeletRequest extends UserRoleEntity {

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private String openUser;
}
