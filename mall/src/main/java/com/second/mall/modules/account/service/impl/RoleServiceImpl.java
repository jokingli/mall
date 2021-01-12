package com.second.mall.modules.account.service.impl;

import com.second.mall.modules.account.dao.RoleDao;
import com.second.mall.modules.account.entity.Role;
import com.second.mall.modules.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author yqs
 * @Comments
 * @Date 2021/1/5 15:46
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
    }

    @Override
    public List<Role> getRolesByUserId(int userId) {
        return roleDao.getRolesByUserId(userId);
    }
}
