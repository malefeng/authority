package com.innove.authority.controller;

import com.github.pagehelper.PageInfo;
import com.innove.authority.bean.dto.request.UserListRequest;
import com.innove.authority.bean.dto.request.UserSaveRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.dto.response.UserListResponse;
import com.innove.authority.bean.entity.UserEntity;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.service.UserService;
import com.innove.authority.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "user", tags = { "账户管理" })
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private HttpServletResponse servletResponse;

    @Autowired
    private HttpServletRequest servletRequest;

    @PostMapping("/list")
    @ApiImplicitParam(name = "UserListRequest", value = "账户信息列表查询请求包")
    @ApiOperation(value = "账户信息列表", notes = "账户信息列表")
    public Response<UserListResponse> list(@RequestBody UserListRequest request){
        PageInfo pageInfo = service.list(request);
        return new Response(pageInfo.getList(),pageInfo.getTotal());
    }

    @GetMapping("/{userCode}")
    @ApiImplicitParam(name = "userCode", value = "用户账号")
    @ApiOperation(value = "查看账户信息", notes = "查看账户信息")
    public Response<UserDetailResponse> detail(@PathVariable("userCode") String userCode){
        return new Response(service.detail(userCode));
    }

    @GetMapping("/getByToken")
    @ApiOperation(value = "根据token查看账户信息")
    public Response<UserDetailResponse> getByToken() throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser==null||currentUser.getPrincipal()==null){
            //未登录，跳转登录接口
            WebUtils.issueRedirect(servletRequest, servletResponse, "/unAuth");
        }
        UserEntity user = (UserEntity) currentUser.getPrincipal();
        if(user==null|| StringUtil.isBlank(user.getUserCode())){
            return new Response().error(RuntimeErrorEnum.NAME_NOT_NULL);
        }
        return new Response(service.detail(user.getUserCode()));
    }

    @PostMapping
    @ApiImplicitParam(name = "UserSaveRequest", value = "新增账户信息请求包")
    @ApiOperation(value = "新增账户信息", notes = "新增账户信息")
    public Response add(@RequestBody UserSaveRequest request) throws Exception {
        Response response = service.save(request);
        return new Response(response.getData()).success(response.getCode(),response.getMsg());
    }

    @PutMapping
    @ApiImplicitParam(name = "UserSaveRequest", value = "修改账户信息请求包")
    @ApiOperation(value = "修改账户信息", notes = "修改账户信息")
    public Response update(@RequestBody UserSaveRequest request) throws Exception {
        Response response = service.update(request);
        return new Response(response.getData()).success(response.getCode(),response.getMsg());
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "账户信息ID")
    @ApiOperation(value = "删除账户信息", notes = "删除账户信息")
    public Response delete(@PathVariable("id") int id){
        return new Response(service.delete(id));
    }

    @GetMapping("/selectByName/{name}")
    public Response<UserEntity> selectByName(@PathVariable("name") String name){
        return new Response(service.selectByName(name));
    }

}
