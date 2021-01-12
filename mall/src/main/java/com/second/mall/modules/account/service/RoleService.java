package com.second.mall.modules.account.service;

import com.second.mall.modules.account.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    List<Role> getRolesByUserId(int userId);
}
