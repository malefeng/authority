package com.innove.authority.service.impl;

import com.innove.authority.bean.entity.MenuEntity;
import com.innove.authority.bean.enums.ConstantCodeEnum;
import com.innove.authority.dao.mapper.MenuDao;
import com.innove.authority.service.MenuService;
import com.innove.authority.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao dao;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/list")
    @Override
    public List<MenuEntity> list(int menuKind) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuKind(menuKind);
        return dao.select(menuEntity);
    }

    @Override
    public int add(MenuEntity entity) {
        int menuKind = (int) httpServletRequest.getSession().getAttribute(ConstantCodeEnum.CLIENT_KIND.getCode());
        entity.setMenuKind(menuKind);
        return dao.insert(entity);
    }

    @Override
    public int delete(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(MenuEntity entity) {
        MenuEntity oldMenu = dao.selectByPrimaryKey(entity.getId());
        if(StringUtil.equals(oldMenu.getMenuCode(),entity.getMenuCode())){
            dao.updateParentMenuCode(oldMenu.getMenuCode(),entity.getMenuCode());
        }
        return dao.updateByPrimaryKey(entity);
    }

    @Override
    public MenuEntity detail(String id) {
        return dao.selectByPrimaryKey(id);
    }
}
