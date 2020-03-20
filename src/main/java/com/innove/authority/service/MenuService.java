package com.innove.authority.service;


import com.innove.authority.bean.entity.MenuEntity;

import java.util.List;

public interface MenuService {

    List<MenuEntity> list(int menuKind);

    int add(MenuEntity entity);

    int delete(String id);

    int update(MenuEntity entity);

    MenuEntity detail(String id);
}
