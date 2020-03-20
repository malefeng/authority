package com.innove.authority.service.impl;

import com.innove.authority.bean.dto.response.OrgListResponse;
import com.innove.authority.bean.entity.OrgEntity;
import com.innove.authority.bean.enums.BusinessCodeHeadEnum;
import com.innove.authority.dao.mapper.OrgDao;
import com.innove.authority.service.OrgService;
import com.innove.authority.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgDao dao;

    @Override
    public int save(OrgEntity orgEntity) {
        if(StringUtil.isBlank(orgEntity.getOrgCode())){
            String orgCode = StringUtil.businessCode(BusinessCodeHeadEnum.ORG_CODE.getCode());
            orgEntity.setOrgCode(orgCode);
        }
        return dao.insert(orgEntity);
    }

    @Override
    public int delete(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int update(OrgEntity orgEntity) {
        return dao.updateByPrimaryKey(orgEntity);
    }

    @Override
    public OrgEntity detail(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<OrgListResponse> childrenList(String orgCode) {
        return dao.listByCode(orgCode);
    }
}
