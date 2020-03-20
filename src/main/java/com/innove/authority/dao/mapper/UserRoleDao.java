package com.innove.authority.dao.mapper;

import com.innove.authority.bean.entity.UserRoleEntity;
import com.innove.authority.dao.GenericMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleDao extends GenericMapper<UserRoleEntity> {

    @Select("select role_code from t_user_role where user_code = '${userCode}'")
    List<String> selectRoleCodeByUserCode(String userCode);

    @Select("select r.role_name from t_role r where EXISTS (select 1 from t_user_role where role_code = r.role_code and user_code = '${userCode}')")
    List<String> selectRoleNameByUserCode(String userCode);
}
