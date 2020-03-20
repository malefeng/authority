package com.innove.authority.dao.mapper;

import com.innove.authority.bean.entity.EnterWechatEntity;
import com.innove.authority.dao.GenericMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EnterWehatDao extends GenericMapper<EnterWechatEntity> {

    @Select("select enter_code,corp_id,corp_secret,agent_id from t_enter_wechat where enter_code = '${enterCode}'")
    @Results(id="tree",value = {
            @Result(property = "enterCode",column = "enter_code"),
            @Result(property = "corpId",column = "corp_id"),
            @Result(property = "corpSecret",column = "corp_secret"),
            @Result(property = "agentId",column = "agent_id"),
    })
    EnterWechatEntity selectByEnterCode(String enterCode);
}
