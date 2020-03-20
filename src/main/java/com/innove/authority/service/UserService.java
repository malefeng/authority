package com.innove.authority.service;

import com.github.pagehelper.PageInfo;
import com.innove.authority.bean.dto.request.UserListRequest;
import com.innove.authority.bean.dto.request.UserSaveRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.entity.UserEntity;

import java.util.List;

public interface UserService {

    PageInfo list(UserListRequest request);

    UserDetailResponse detail(String userCode);

    Response save(UserSaveRequest request) throws Exception;

    Response update(UserSaveRequest request) throws Exception;

    boolean delete(int id);

    UserEntity selectByName(String name);

    int countByRoleCode(String roleCode);

    List<UserEntity> select(UserEntity param);
}
