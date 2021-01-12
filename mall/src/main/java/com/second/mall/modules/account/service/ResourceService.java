package com.second.mall.modules.account.service;

import com.second.mall.modules.account.entity.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> getResourcesByRoleId(int roleId);
}
