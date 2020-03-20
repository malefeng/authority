package com.innove.authority.service.impl;

import com.innove.authority.bean.dto.request.UserRoleDeletRequest;
import com.innove.authority.bean.entity.UserRoleEntity;
import com.innove.authority.dao.mapper.UserRoleDao;
import com.innove.authority.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao dao;


    @Override
    public int save(UserRoleEntity userRoleEntity) {
        return dao.insert(userRoleEntity);
    }

    @Override
    public int del(UserRoleDeletRequest request) {
        return dao.delete(request);
    }
}
