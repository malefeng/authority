package com.innove.authority.service;


import com.github.pagehelper.PageInfo;
import com.innove.authority.bean.dto.request.RoleListRequest;
import com.innove.authority.bean.dto.request.RoleSaveRequest;
import com.innove.authority.bean.dto.response.RoleDetailResponse;
import com.innove.authority.bean.dto.response.RoleListResponse;

public interface RoleService {
    PageInfo<RoleListResponse> list(RoleListRequest request);

    RoleDetailResponse detail(int id);

    boolean save(RoleSaveRequest request);

    int update(RoleSaveRequest request);

    int delete(int id);
}
