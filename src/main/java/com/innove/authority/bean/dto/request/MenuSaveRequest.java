package com.innove.authority.bean.dto.request;

import com.innove.authority.bean.entity.MenuEntity;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

@ApiModel(description = "新增菜单请求包")
@Data
public class MenuSaveRequest extends MenuEntity {

    @Ignore
    private Integer id;

    @Ignore
    private Integer menuKind;

    private static final long serialVersionUID = 1L;
}
