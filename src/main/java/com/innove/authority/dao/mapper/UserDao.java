package com.innove.authority.dao.mapper;

import com.innove.authority.dao.GenericMapper;
import com.innove.authority.bean.dto.request.UserListRequest;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.dto.response.UserListResponse;
import com.innove.authority.bean.entity.UserEntity;
import com.innove.authority.util.StringUtil;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao extends GenericMapper<UserEntity> {

    @SelectProvider(type=UserProvider.class,method="listSql")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "realName", column = "real_name"),
            @Result(property = "userTel", column = "user_tel"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "roleName", column = "user_code",many = @Many(select = "com.innove.authority.dao.mapper.UserRoleDao.selectRoleNameByUserCode"))
    })
    List<UserListResponse> list(UserListRequest request);

    @Select("select t.*,${menuKind} menu_kind from t_user t where t.user_code = #{userCode}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "realName", column = "real_name"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userTel", column = "user_tel"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "openUser", column = "open_user"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "canLogin", column = "can_login"),
            @Result(property = "roleCode", column = "user_code",many = @Many(select = "com.innove.authority.dao.mapper.UserRoleDao.selectRoleCodeByUserCode")),
            @Result(property = "menuNames", column = "{userCode=user_code,menuKind=menu_kind}",many = @Many(select = "com.innove.authority.dao.mapper.MenuDao.selectNamesByUserCode"))
    })
    UserDetailResponse detail(@Param("userCode") String userCode,@Param("menuKind") int menuKind);

    @Select("select t.*,#{menuKind} menuKind from t_user t where t.user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "realName", column = "real_name"),
            @Result(property = "userTel", column = "user_tel"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "openUser", column = "open_user"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "canLogin", column = "can_login"),
            @Result(property = "roleCode", column = "user_code",many = @Many(select = "com.innove.authority.dao.mapper.UserRoleDao.selectRoleCodeByUserCode")),
            @Result(property = "menuNames", column = "{userCode=user_code,menuKind=menuKind}",many = @Many(select = "com.innove.authority.dao.mapper.MenuDao.selectNamesByUserCode"))
    })
    UserDetailResponse getByWechatCode(@Param("userId")String userId,@Param("menuKind")int menuKind);

    @Select("select count(*) from t_user u where EXISTS (select 1 from t_user_role where user_code = u.user_code and role_code = '${roleCode}')")
    int countByRoleCode(String roleCode);


    class UserProvider{
        public String listSql(UserListRequest request){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ")
                    .append(" U.id, " )
                    .append(" U.user_code, ")
                    .append(" U.real_name, " )
                    .append(" U.user_tel, " )
                    .append(" U.user_status " )
                    .append("FROM ")
                    .append(" t_user U " )
                    .append("WHERE " )
                    .append(" 1 = 1  " );
            if(StringUtil.isNotBlank( request.getRoleCode())){
                sql.append( " AND U.role_code = '" ).append(request.getRoleCode()).append("'");
            }
            if(StringUtil.isNotBlank(request.getUserCode())){
                sql.append(String.format(" AND LOCATE( '%s' ,U.user_code) > 0 ",request.getUserCode()) );
            }
            if(request.getUserStatus()!=null){
                sql.append(" AND U.user_status =").append(request.getUserStatus());
            }
            if(StringUtil.isNotBlank(request.getRealName())){
                sql.append(" AND  LOCATE('").append(request.getRealName()).append("' ,U.real_name) > 0 ");
            }
            return sql.toString();
        }
    }
}
