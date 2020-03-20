package com.innove.authority.service.impl;

import com.innove.authority.bean.dto.response.OrgListOfUserResponse;
import com.innove.authority.bean.entity.UserDataEntity;
import com.innove.authority.dao.mapper.UserDataDao;
import com.innove.authority.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserDataDao dataDao;

    @Override
    public int save(List<UserDataEntity> userDatas) {
        return dataDao.insertList(userDatas);
    }

    @Override
    public int delete(String userCode) {
        return dataDao.deleteByUserCode(userCode);
    }

    @Override
    public List<OrgListOfUserResponse> selectOrgCodeListByUserCode(String userCode) {
        return dataDao.orgCodelistByUserCode(userCode);
    }
}
