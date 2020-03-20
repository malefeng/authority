package com.innove.authority.service.impl;

import com.innove.authority.dao.mapper.RoleMenuDao;
import com.innove.authority.bean.dto.request.RoleMenuDeleteRequest;
import com.innove.authority.bean.entity.RoleMenuEntity;
import com.innove.authority.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao dao;

    @PostMapping
    @Override
    public int save(@RequestBody List<RoleMenuEntity> roleMenuEntities) {
        return dao.insertList(roleMenuEntities);
    }

    @DeleteMapping("/{roleCode}")
    @Override
    public int deleteByRoleCode(@PathVariable("roleCode") String roleCode) {
        return dao.deleteByRoleCode(roleCode);
    }

    @DeleteMapping
    @Override
    public int delete(@RequestBody RoleMenuDeleteRequest request){
        return dao.deletes(request);
    }

    @Override
    @GetMapping("/{roleCode}")
    public String[] selectMenuUrlByRoleCode(@PathVariable("roleCode") String roleCode){
        return dao.selectByRoleCode(roleCode);
    }

    @Override
    public String[] selectMenuUrlByUserCode(String userCode) {
        return dao.selectMenuUrlByUserCode(userCode);
    }
}
