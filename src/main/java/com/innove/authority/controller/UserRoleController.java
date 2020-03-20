package com.innove.authority.controller;

import com.innove.authority.bean.dto.request.UserRoleDeletRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.entity.UserRoleEntity;
import com.innove.authority.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api( tags = { "账户-角色关系" })
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService service;

    @PostMapping
    @ApiOperation(value = "保存账号-角色关系信息")
    @ApiImplicitParam(name = "UserRoleEntity", value = "请求包")
    public Response save(UserRoleEntity userRoleEntity){
        return new Response(service.save(userRoleEntity));
    }

    @DeleteMapping
    @ApiOperation(value = "删除账号-角色关系信息")
    @ApiImplicitParam(name = "UserRoleDeletRequest", value = "请求包")
    public Response del(UserRoleDeletRequest request){
        return new Response(service.del(request));
    }
}
