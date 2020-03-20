package com.innove.authority.dao.mapper;

import com.innove.authority.bean.dto.response.OrgListResponse;
import com.innove.authority.bean.entity.OrgEntity;
import com.innove.authority.dao.GenericMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrgDao extends GenericMapper<OrgEntity> {

    @Select("select * from t_org where parent_org_code = #{orgCode}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orgCode",column = "org_code"),
            @Result(property = "orgName",column = "org_name"),
            @Result(property = "orgAddr",column = "org_addr"),
            @Result(property = "orgStatus",column = "org_status"),
            @Result(property = "orgType",column = "org_type"),
            @Result(property = "orgQualit",column = "org_qualit"),
            @Result(property = "permCode",column = "perm_code"),
            @Result(property = "permValue",column = "perm_value"),
            @Result(property = "parentOrgCode",column = "parent_org_code"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "userCode",column = "user_code"),
            @Result(property = "remark",column = "remark"),
//            @Result(property = "childrenList", column = "org_code",many = @Many(select = "com.innove.authority.dao.mapper.OrgDao.listByCode"))
    })
    List<OrgListResponse> listByCode(String orgCode);
}
