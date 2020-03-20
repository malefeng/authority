package com.innove.authority.dao.mapper;

import com.innove.authority.dao.GenericMapper;
import com.innove.authority.bean.dto.request.RoleListRequest;
import com.innove.authority.bean.dto.response.RoleDetailResponse;
import com.innove.authority.bean.dto.response.RoleListResponse;
import com.innove.authority.bean.entity.RoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao extends GenericMapper<RoleEntity> {

    @SelectProvider(type=RoleProvider.class,method="listSql")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "roleCode", column = "role_code"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "menuName", column = "menu_name")
    })
    List<RoleListResponse> list(RoleListRequest request);

    @Select("select * from t_role where id = #{id}")
    @Results({
            @Result(property = "roleCode", column = "role_code"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleStatus", column = "role_status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "openUser", column = "open_user"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "menuCodes", column = "role_code",many = @Many(select = "com.innove.authority.dao.mapper.RoleMenuDao.selectMenuCodeByRoleCode"))
    })
    RoleDetailResponse detail(int id);

    class RoleProvider{
        public String listSql(RoleListRequest request){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ")
                    .append(" max(r.id) id,r.role_code role_code ,max(r.role_name) roleName,GROUP_CONCAT(m.menu_name) menu_name ")
                    .append(" FROM " )
                    .append(" t_role r " )
                    .append(" LEFT JOIN t_role_menu rm ON r.role_code = rm.role_code " )
                    .append(" LEFT JOIN t_menu m ON rm.menu_code = m.menu_code  " )
                    .append(" GROUP BY " )
                    .append(" r.role_code");
            return sql.toString();
        }
    }

}
