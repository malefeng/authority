package com.innove.authority.controller;

import com.innove.authority.bean.dto.request.OrgSaveRequest;
import com.innove.authority.bean.dto.response.OrgListResponse;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.entity.OrgEntity;
import com.innove.authority.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
@Api(value = "org", tags = { "组织机构管理" })
public class OrgController {

    @Autowired
    private OrgService service;

    //顶层组织机构代码
    private final String TOP_ORG_CODE = "top";

    @PostMapping
    @ApiOperation(value = "保存机构信息")
    @ApiImplicitParam(name = "OrgSaveRequest", value = "保存机构信息请求包")
    public Response save(@RequestBody OrgSaveRequest request){
        return new Response(service.save(request));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据机构信息id刪除机构信息")
    @ApiImplicitParam(name = "id", value = "机构信息id")
    public Response delete(@PathVariable("id")String id){
        return new Response(service.delete(id));
    }

    @PutMapping
    @ApiOperation(value = "修改机构信息")
    @ApiImplicitParam(name = "OrgEntity", value = "机构代码")
    public Response update(OrgEntity orgEntity){
        return new Response(service.update(orgEntity));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据机构id查询机构信息")
    @ApiImplicitParam(name = "id", value = "机构代码")
    public Response<OrgEntity> detail(@PathVariable("id")String id){
        return new Response(service.detail(id));
    }


    @GetMapping("/list")
    @ApiOperation(value = "查询顶层组织机构")
    public Response<List<OrgListResponse>> list(){
        return new Response(service.childrenList(TOP_ORG_CODE));
    }

    @GetMapping("/childrenList")
    @ApiOperation(value = "查询子级组织机构")
    @ApiImplicitParam(name = "orgCode", value = "机构代码")
    public Response<List<OrgListResponse>> childrenList(String orgCode){
        return new Response<>(service.childrenList(orgCode));
    }
}
