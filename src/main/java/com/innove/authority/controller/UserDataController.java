package com.innove.authority.controller;

import com.innove.authority.bean.dto.response.OrgListOfUserResponse;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.entity.UserDataEntity;
import com.innove.authority.bean.enums.ConstantCodeEnum;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.service.UserDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api( tags = { "账户-机构关系" })
@RequestMapping("/userData")
public class UserDataController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserDataService service;

    @PostMapping
    @ApiOperation(value = "保存账号-机构关系信息")
    @ApiImplicitParam(name = "List<UserDataEntity>", value = "请求包")
    public Response save(@RequestBody List<UserDataEntity> userDatas){
        return new Response(service.save(userDatas));
    }

    @DeleteMapping
    @ApiOperation(value = "根据用户编号删除账号-机构关系信息")
    @ApiImplicitParam(name = "userCode", value = "用户编号")
    public Response delete(String userCode){
        return new Response(service.delete(userCode));
    }

    @GetMapping("/selectOrgListByUserCode")
    @ApiOperation(value = "web端根据用户编号查询对应的机构代码")
    @ApiImplicitParam(name = "userCode", value = "账号管理步骤注册的user_code")
    public Response<List<OrgListOfUserResponse>> selectOrgListByUserCode(String userCode){
        return new Response(service.selectOrgCodeListByUserCode(userCode));
    }

    @GetMapping("/selectOrgOfCurrentUser")
    @ApiOperation(value = "获取当前登陆用户的机构代码")
    public Response<List<OrgListOfUserResponse>> selectOrgOfCurrentUser(){
        UserDetailResponse currentUser = (UserDetailResponse) httpServletRequest.getSession().getAttribute(ConstantCodeEnum.CURRENT_USER.getCode());
        if(currentUser==null){
            return new Response().error(RuntimeErrorEnum.INVALID_UNLOGIN_ERROR);
        }
        return new Response(service.selectOrgCodeListByUserCode(currentUser.getUserCode()));
    }
}
