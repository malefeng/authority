package com.innove.authority.bean.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description="分页查询请求包")
@Data
public class QueryListWithPageRequest extends Request {

	private static final long serialVersionUID = -254024855493052058L;

	@ApiModelProperty(value = "页码")
    private int pageNo;

    @ApiModelProperty(value = "每页数量")
    private int pageCount;
}
