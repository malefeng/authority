package com.innove.authority.service;

import com.innove.authority.bean.dto.response.OrgListResponse;
import com.innove.authority.bean.entity.OrgEntity;

import java.util.List;

public interface OrgService {

    int save(OrgEntity orgEntity);

    int delete(String id);

    int update(OrgEntity orgEntity);

    OrgEntity detail(String id);

    List<OrgListResponse> childrenList(String orgCode);
}
