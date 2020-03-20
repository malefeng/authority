package com.innove.authority.service;


import com.innove.authority.bean.dto.request.RoleMenuDeleteRequest;
import com.innove.authority.bean.entity.RoleMenuEntity;

import java.util.List;

public interface RoleMenuService {

    int save(List<RoleMenuEntity> roleMenuEntities);

    int deleteByRoleCode(String roleCode);

    int delete(RoleMenuDeleteRequest request);

    String[] selectMenuUrlByRoleCode(String roleCode);

    String[] selectMenuUrlByUserCode(String userCode);
}
