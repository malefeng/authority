package com.innove.authority.controller;

import com.innove.authority.bean.dto.request.RoleMenuDeleteRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.entity.RoleMenuEntity;
import com.innove.authority.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roleMenu")
@Api(value = "userData", tags = { "角色-菜单关系管理" })
public class RoleMenuController {

    @Autowired
    private RoleMenuService service;

    @ApiImplicitParam(name = "List<RoleMenuEntity>", value = "新增角色-菜单关系信息请求包")
    @ApiOperation(value = "新增角色-菜单关系信息", notes = "新增角色-菜单关系信息")
    @PostMapping
    public Response<Integer> save(@RequestBody List<RoleMenuEntity> roleMenuEntities){
        return new Response(service.save(roleMenuEntities));
    }

    @ApiImplicitParam(name = "String", value = "删除角色-菜单关系信息请求包")
    @ApiOperation(value = "删除角色-菜单关系信息", notes = "删除角色-菜单关系信息")
    @DeleteMapping("/{roleCode}")
    public Response<Integer> deleteByRoleCode(@PathVariable("roleCode") String roleCode){
        return new Response(service.deleteByRoleCode(roleCode));
    }

    @ApiImplicitParam(name = "RoleMenuDeleteRequest", value = "批量删除角色-菜单关系信息请求包")
    @ApiOperation(value = "批量删除角色-菜单关系信息", notes = "批量删除角色-菜单关系信息")
    @DeleteMapping
    public Response<Integer> delete(@RequestBody RoleMenuDeleteRequest request){
        return new Response(service.delete(request));
    }
}
