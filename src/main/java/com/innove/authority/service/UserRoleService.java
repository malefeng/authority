package com.innove.authority.service;

import com.innove.authority.bean.dto.request.UserRoleDeletRequest;
import com.innove.authority.bean.entity.UserRoleEntity;

public interface UserRoleService {

    int save(UserRoleEntity userRoleEntity);

    int del(UserRoleDeletRequest request);
}
