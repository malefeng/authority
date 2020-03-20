package com.innove.authority.controller;

import com.github.pagehelper.PageInfo;
import com.innove.authority.bean.dto.request.RoleListRequest;
import com.innove.authority.bean.dto.request.RoleSaveRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.RoleDetailResponse;
import com.innove.authority.bean.dto.response.RoleListResponse;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.service.RoleService;
import com.innove.authority.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "role", tags = { "角色管理" })
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ApiImplicitParam(name = "RoleListRequest", value = "角色信息列表查询请求包")
    @ApiOperation(value = "角色信息列表", notes = "角色信息列表")
    public Response<List<RoleListResponse>> list(@RequestBody RoleListRequest request){
        PageInfo pageInfo = service.list(request);
        return new Response<List<RoleListResponse>>(pageInfo.getList(),pageInfo.getTotal());
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "角色信息ID")
    @ApiOperation(value = "查看角色信息", notes = "查看角色信息")
    public Response<RoleDetailResponse> detail(@PathVariable("id") int id){
        return new Response(service.detail(id));
    }

    @PostMapping
    @ApiImplicitParam(name = "UserEntity", value = "新增角色信息请求包")
    @ApiOperation(value = "新增角色信息", notes = "新增角色信息")
    public Response<Integer> add(@RequestBody RoleSaveRequest request){
        return new Response(service.save(request));
    }

    @PutMapping
    @ApiImplicitParam(name = "RoleSaveRequest", value = "修改角色信息请求包")
    @ApiOperation(value = "修改角色信息", notes = "修改角色信息")
    public Response<Integer> update(@RequestBody RoleSaveRequest request){
        return new Response(service.update(request));
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "角色信息ID")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息")
    public Response<Integer> delete(@PathVariable("id") int id){
        RoleDetailResponse detail = service.detail(id);
        if(detail==null){
            return new Response<>().error(RuntimeErrorEnum.ROLE_NOT_NULL_ERROR);
        }
        int userCount = userService.countByRoleCode(detail.getRoleCode());
        if(userCount>0){
            return new Response<>().error(RuntimeErrorEnum.ROLE_RELATI_USER_NULL);
        }
        return new Response(service.delete(id));
    }
}
