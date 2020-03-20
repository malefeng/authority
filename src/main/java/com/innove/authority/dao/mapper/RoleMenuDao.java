package com.innove.authority.dao.mapper;

import com.innove.authority.bean.dto.request.RoleMenuDeleteRequest;
import com.innove.authority.bean.entity.RoleMenuEntity;
import com.innove.authority.dao.GenericMapper;
import com.innove.authority.util.StringUtil;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMenuDao extends GenericMapper<RoleMenuEntity> {

    @Select("select menu_url from t_menu m where EXISTS (select 1 from t_role_menu where menu_code = m.menu_code and role_code = '${roleCode}') and menu_status = 1 and menu_url is not null and menu_url <> ''")
    String[] selectByRoleCode(String roleCode);

    @Select("SELECT " +
            " menu_url  " +
            "FROM " +
            " t_menu m  " +
            "WHERE " +
            " EXISTS ( " +
            " SELECT " +
            "  1  " +
            " FROM " +
            "  t_role_menu rm  " +
            " WHERE " +
            " rm.menu_code = m.menu_code  " +
            " AND EXISTS ( SELECT 1 FROM t_user_role ur WHERE ur.role_code = rm.role_code AND user_code = '${userCode}' ))")
    String[] selectMenuUrlByUserCode(String userCode);

    @Select("select menu_code from t_role_menu where role_code = '${roleCode}'")
    String[] selectMenuCodeByRoleCode(String roleCode);

    @Delete("delete from t_role_menu where role_code = '${roleCode}'")
    int deleteByRoleCode(String roleCode);

    @DeleteProvider(type=RoleMenuProvider.class,method="deletesSql")
    int deletes(RoleMenuDeleteRequest request);

    class RoleMenuProvider{

        public String deletesSql(RoleMenuDeleteRequest request){
            String roleCode = request.getRoleCode();
            List<String> menuCodes = request.getMenuCodes();
            if(StringUtil.isBlank(roleCode)){
                return "select 0";
            }
            StringBuilder sql = new StringBuilder();
            sql.append("delete from t_role_menu where ")
                    .append(" role_code = '").append(roleCode).append("'");
            if(menuCodes!=null&&menuCodes.size()>0){
                sql.append(" and menu_code in ('")
                        .append(String.join("','",menuCodes))
                        .append("')");
            }
            return sql.toString();
        }
    }
}
