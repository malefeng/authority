package com.innove.authority.dao.mapper;

import com.innove.authority.bean.dto.response.OrgListOfUserResponse;
import com.innove.authority.bean.entity.UserDataEntity;
import com.innove.authority.dao.GenericMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserDataDao extends GenericMapper<UserDataEntity> {

    @SelectProvider(type=UserDataProvider.class,method="listSql")
    String[] selectByUserCode(String userCode);

    @DeleteProvider(type=UserDataProvider.class,method="deleteSql")
    int deleteByUserCode(String userCode);

    @DeleteProvider(type=UserDataProvider.class,method="deletes")
    int deletes(Map param);

    @Select("SELECT " +
            " org_code, " +
            " org_name, " +
            " org_type, " +
            " org_qualit, " +
            " perm_code, " +
            " perm_value, " +
            " parent_org_code  " +
            "FROM " +
            " t_org o  " +
            "WHERE " +
            " EXISTS ( SELECT 1 FROM t_user_data ud WHERE ud.org_code = o.org_code AND user_code = '${userCode}' )  " +
            " AND org_status = 1")
    @Results({
            @Result(property = "orgCode", column = "org_code"),
            @Result(property = "orgName", column = "org_name"),
            @Result(property = "orgType", column = "org_type"),
            @Result(property = "orgQualit", column = "org_qualit"),
            @Result(property = "permCode", column = "perm_code"),
            @Result(property = "permValue", column = "perm_value"),
            @Result(property = "parentOrgCode", column = "parent_org_code"),
    })
    List<OrgListOfUserResponse> orgCodelistByUserCode(String userCode);



    class UserDataProvider{

        public String listSql(String userCode){
            return "select org_code from t_user_data where user_code = '"+userCode+"'";
        }

        public String deleteSql(String userCode){
            return "delete from t_user_data where user_code = '"+userCode+"'";
        }

        public String deletes(Map param){
            String userCode = (String) param.get("userCode");
            List<String> orgCodes = (List<String>) param.get("orgCodes");
            StringBuilder sql = new StringBuilder("delete from t_user_data where 1=1")
                    .append(" and user_code = '").append(userCode).append("'")
                    .append(" and org_code in ('").append(String.join("','",orgCodes)).append("')");
            return sql.toString();
        }
    }
}
