package com.innove.authority.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.innove.authority.bean.dto.request.UserListRequest;
import com.innove.authority.bean.dto.request.UserSaveRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.entity.UserEntity;
import com.innove.authority.bean.entity.UserRoleEntity;
import com.innove.authority.bean.enums.ConstantCodeEnum;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.dao.mapper.UserDao;
import com.innove.authority.dao.mapper.UserRoleDao;
import com.innove.authority.service.UserService;
import com.innove.authority.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private HttpServletRequest request;

    @Override
    public PageInfo list(@RequestBody UserListRequest request) {
        PageHelper.startPage(request.getPageNo(),request.getPageCount());
        return new PageInfo(dao.list(request));
    }

    @Override
    public UserDetailResponse detail(@PathVariable("userCode")String userCode) {
        int menuKind = (int) request.getSession().getAttribute(ConstantCodeEnum.CLIENT_KIND.getCode());
        return dao.detail(userCode,menuKind);
    }

    @Override
    public Response save(@RequestBody UserSaveRequest request) throws Exception {
        //校验账号唯一性
        if(!checkUniq(request.getUserCode(),request.getId())){
            return new Response(false).error(RuntimeErrorEnum.FAILED.getCode(),"账号重复");
        }
        String tel = request.getUserTel();
        String newPsw = "";
        if(StringUtil.isNotBlank(request.getPassword())){
            newPsw = request.getPassword();
        }else if(StringUtil.isNotBlank(tel)&&tel.length()>=6){
            newPsw = tel.substring(tel.length()-6);
        }
        if(StringUtil.isNotBlank(newPsw)){
            //密码加密
            newPsw = StringUtil.MD5(newPsw);
            request.setPassword(newPsw);
        }
        dao.insert(request);
        //保存账户角色对应关系
        List<String> roleCodes = request.getRoleCode();
        if(roleCodes!=null&&roleCodes.size()>0){
            List roleList = new ArrayList();
            roleCodes.forEach(roleCode->{
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setRoleCode(roleCode);
                userRoleEntity.setUserCode(request.getUserCode());
                userRoleEntity.setCreateTime(new Date());
                roleList.add(userRoleEntity);
            });
            userRoleDao.insertList(roleList);
        }
        return new Response(true);
    }

    @Override
    @Transactional
    public Response update(@RequestBody UserSaveRequest request) throws Exception {
        //校验账号唯一性
        if(!checkUniq(request.getUserCode(),request.getId())){
            return new Response(false).error(RuntimeErrorEnum.FAILED.getCode(),"账号重复");
        }
        //判断是否修改密码
        UserEntity userOld = dao.selectByPrimaryKey(request.getId());
        if(StringUtil.isNotBlank(request.getPassword())){
            //密码加密
            String newPsw = StringUtil.MD5(request.getPassword());
            if(!StringUtils.equals(newPsw,userOld.getPassword())){
                request.setPassword(newPsw);
            }
        }else{
            request.setPassword(userOld.getPassword());
        }
        dao.updateByPrimaryKey(request);
        //更新账户角色关系
        List<String> oldRoles = userRoleDao.selectRoleCodeByUserCode(request.getUserCode());
        List<String> newRoles = request.getRoleCode();
        List<String> oldRolesCopy = new ArrayList<>(oldRoles);
        List toAdd = new ArrayList();
        //删除缺失的
        if(oldRolesCopy!=null&&oldRolesCopy.size()>0){
            oldRolesCopy.removeAll(newRoles);
            if(oldRolesCopy.size()>0){
                oldRolesCopy.forEach(roleCode->{
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setRoleCode(roleCode);
                    userRoleEntity.setUserCode(request.getUserCode());
                    userRoleDao.delete(userRoleEntity);
                });
            }
        }
        //插入新增的
        if(newRoles!=null&&newRoles.size()>0){
            newRoles.removeAll(oldRoles);
            if(newRoles.size()>0){
                newRoles.forEach(roleCode->{
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setRoleCode(roleCode);
                    userRoleEntity.setUserCode(request.getUserCode());
                    toAdd.add(userRoleEntity);
                });
                userRoleDao.insertList(toAdd);
            }
        }
        return new Response(true);
    }

    @Override
    @Transient
    public boolean delete(@PathVariable("id")int id) {
        dao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public UserEntity selectByName(@PathVariable("name")String name) {
        UserEntity user = new UserEntity();
        user.setUserCode(name);
        return dao.selectOne(user);
    }

    @Override
    @GetMapping("/selectByRoleCode/{roleCode}")
    public int countByRoleCode(@PathVariable("roleCode")String roleCode) {
        return dao.countByRoleCode(roleCode);
    }

    /**
     * @Author mlf
     * @Description //判断账号是否唯一
     * @Date 下午 5:42 2020/1/16
     * @Param [userCode, id]
     * @return boolean
     **/
    public boolean checkUniq(String userCode,Integer id){
        UserEntity user = new UserEntity();
        user.setUserCode(userCode);
        List<UserEntity> userEntityList = dao.select(user);
        if(userEntityList!=null&&userEntityList.size()>0){
            if(userEntityList.size()>1){
                return false;
            }
            UserEntity userEntity = userEntityList.get(0);
            if(userEntity.getId()!=id){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<UserEntity> select(UserEntity param){
        return dao.select(param);
    }

}
