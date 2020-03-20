package com.innove.authority.controller;

import com.innove.authority.bean.dto.request.MenuSaveRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.entity.MenuEntity;
import com.innove.authority.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@Api(value = "menu", tags = { "菜单管理" })
public class MenuController {

    @Autowired
    private MenuService service;

    @ApiOperation(value = "菜单列表信息查询", notes = "菜单列表信息查询")
    @ApiImplicitParam(name = "menuKind", value = "菜单类别（1-web端，2-企业微信）")
    @GetMapping("/list/{menuKind}")
    public Response<MenuEntity> list(@PathVariable("menuKind") int menuKind){
        return new Response(service.list(menuKind));
    }

    @ApiOperation(value = "新增菜单项")
    @ApiImplicitParam(name = "MenuSaveRequest", value = "新增菜单项请求包")
    @PostMapping
    public Response add(@RequestBody MenuSaveRequest request){
        return new Response(service.add(request));
    }

    @ApiOperation(value = "根据菜单id删除菜单项")
    @ApiImplicitParam(name = "id", value = "菜单id")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") String id){
        return new Response(service.delete(id));
    }

    @ApiOperation(value = "根据菜单id修改菜单项")
    @ApiImplicitParam(name = "MenuEntity", value = "菜单项详情")
    @PutMapping
    public Response update(@RequestBody MenuEntity entity){
        return new Response(service.update(entity));
    }

    @ApiOperation(value = "根据菜单id查询菜单项详情")
    @ApiImplicitParam(name = "id", value = "菜单id")
    @GetMapping("/{id}")
    public Response<MenuEntity> detail(@PathVariable("id")String id){
        return new Response(service.delete(id));
    }

}
