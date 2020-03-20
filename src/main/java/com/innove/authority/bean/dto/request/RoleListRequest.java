package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "账户信息列表查询请求包")
@Data
public class RoleListRequest extends QueryListWithPageRequest {
}
