package com.innove.authority.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.innove.authority.dao.mapper.RoleDao;
import com.innove.authority.dao.mapper.RoleMenuDao;
import com.innove.authority.bean.dto.request.RoleListRequest;
import com.innove.authority.bean.dto.request.RoleMenuDeleteRequest;
import com.innove.authority.bean.dto.request.RoleSaveRequest;
import com.innove.authority.bean.dto.response.RoleDetailResponse;
import com.innove.authority.bean.dto.response.RoleListResponse;
import com.innove.authority.bean.entity.RoleEntity;
import com.innove.authority.bean.entity.RoleMenuEntity;
import com.innove.authority.bean.enums.BusinessCodeHeadEnum;
import com.innove.authority.service.RoleService;
import com.innove.authority.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao dao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @PostMapping("/list")
    @Override
    public PageInfo<RoleListResponse> list(@RequestBody RoleListRequest request) {
        PageHelper.startPage(request.getPageNo(),request.getPageCount());
        return new PageInfo(dao.list(request));
    }

    @GetMapping("/{id}")
    @Override
    public RoleDetailResponse detail(@PathVariable("id") int id) {
        return dao.detail(id);
    }

    @PostMapping
    @Override
    @Transient
    public boolean save(@RequestBody RoleSaveRequest request) {
        RoleEntity role = request.getRole();
        String roleCode = StringUtil.businessCode(BusinessCodeHeadEnum.ROLE_CODE.getCode());
        role.setRoleCode(roleCode);
         dao.insert(role);
        List<String> menuCodes = request.getMenuCodes();
         if(menuCodes!=null&&menuCodes.size()>0){
             List list = new ArrayList(menuCodes.size());
             menuCodes.forEach(m->{
                 RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                 roleMenuEntity.setCreateTime(new Date());
                 roleMenuEntity.setMenuCode(m);
                 roleMenuEntity.setRoleCode(String.valueOf(roleCode));
                 list.add(roleMenuEntity);
             });
             roleMenuDao.insertList(list);
         }
         return true;
    }

    @PutMapping
    @Override
    @Transactional
    public int update(@RequestBody RoleSaveRequest request) {
        RoleEntity role = request.getRole();
        //更新数据
        List<String> menuCodeList_new = request.getMenuCodes();
        List<String> menuCodeList_new_cash = new ArrayList<>(menuCodeList_new);
        String roleCode = role.getRoleCode();
        //原始数据
        String[] menuCodes_old = roleMenuDao.selectMenuCodeByRoleCode(roleCode);
        List<String> menuCodeList_old = Arrays.asList(menuCodes_old);
        //获取新增内容
        menuCodeList_new.removeAll(menuCodeList_old);
        if(menuCodeList_new.size()>0){
            List<RoleMenuEntity> toSaveList = new ArrayList<>();
            menuCodeList_new.forEach(menuCode->{
                toSaveList.add(new RoleMenuEntity(roleCode,menuCode,role.getUpdateTime(),null));
            });
            roleMenuDao.insertList(toSaveList);
        }
        //获取移除内容
        menuCodeList_old = menuCodeList_old.stream().filter(item->{
            return !menuCodeList_new_cash.contains(item);
        }).collect(Collectors.toList());
        if(menuCodeList_old.size()>0){
            RoleMenuDeleteRequest deleteRequest = new RoleMenuDeleteRequest();
            deleteRequest.setRoleCode(roleCode);
            deleteRequest.setMenuCodes(menuCodeList_old);
            roleMenuDao.deletes(deleteRequest);
        }
        //更新主表
        return dao.updateByPrimaryKey(role);
    }

    @DeleteMapping("/{id}")
    @Override
    @Transient
    public int delete(@PathVariable("id") int id) {
        RoleEntity role = dao.selectByPrimaryKey(id);
        roleMenuDao.deleteByRoleCode(role.getRoleCode());
        return dao.deleteByPrimaryKey(id);
    }
}
