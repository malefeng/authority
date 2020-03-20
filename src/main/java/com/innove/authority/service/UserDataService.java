package com.innove.authority.service;

import com.innove.authority.bean.dto.response.OrgListOfUserResponse;
import com.innove.authority.bean.entity.UserDataEntity;

import java.util.List;

public interface UserDataService {

    int save(List<UserDataEntity> userDatas);

    int delete(String userCode);

    List<OrgListOfUserResponse> selectOrgCodeListByUserCode(String userCode);
}
