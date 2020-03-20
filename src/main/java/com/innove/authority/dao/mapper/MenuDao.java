package com.innove.authority.dao.mapper;

import com.innove.authority.bean.dto.response.MenuResponse;
import com.innove.authority.dao.GenericMapper;
import com.innove.authority.bean.entity.MenuEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface MenuDao extends GenericMapper<MenuEntity> {

    @SelectProvider(type=MenuDao.Provider.class,method="selectNamesByUserCode")
    @Results(id="tree",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "menuName",column = "menu_name"),
            @Result(property = "menuCode",column = "menu_code"),
            @Result(property = "menuUrl",column = "menu_url"),
            @Result(property = "meta",column = "meta"),
            @Result(property = "childrenMenu",column = "{menuCode=menu_code,menuKind=menu_kind}",many = @Many(select = "com.innove.authority.dao.mapper.MenuDao.getTreeByParentMenu")),
    })
    List<MenuResponse> selectNamesByUserCode(@Param("userCode") String userCode,@Param("menuKind") int menuKind);


    @SelectProvider(type=MenuDao.Provider.class,method="getTreeByParentMenu")
    @ResultMap("tree")
    List<MenuResponse> getTreeByParentMenu(Map<String, Object> param);


    @Update("update t_menu set parent_menu = '${newCode}' where parent_menu = '${oldCode}' ")
    int updateParentMenuCode(@Param("oldCode") String oldCode,@Param("newCode") String newCode);

    class Provider{

        public String selectNamesByUserCode(Map<String, Object> param){
            String userCode = (String) param.get("userCode");
            Object menuKind = param.get("menuKind");
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ")
                    .append(String.format("%d",menuKind)).append(" menu_kind,")
                    .append(" m.menu_code,m.menu_name, m.menu_url, m.meta ")
                    .append("FROM")
                    .append(String.format(" ( SELECT role_code FROM t_user_role WHERE user_code = '%s' ) u",userCode))
                    .append(" LEFT JOIN t_role_menu rm ON u.role_code = rm.role_code")
                    .append(" LEFT JOIN t_menu m ON m.menu_code = rm.menu_code ")
                    .append(" WHERE m.parent_menu = 'top' and menu_status = 1 " )
                    .append(String.format(" and m.menu_kind = %d",menuKind));
            return sql.toString();
        }

        public String getTreeByParentMenu(Map<String, Object> param){
            String menuCode = (String) param.get("menuCode");
            Object menuKind = param.get("menuKind");
            StringBuilder sql = new StringBuilder();
            sql.append(String.format("SELECT menu_name,menu_code,meta, menu_url,menu_kind from t_menu where parent_menu = '%s' and menu_status = 1 and menu_kind = %d",menuCode,menuKind));
            return sql.toString();
        }
    }

}
