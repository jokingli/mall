package com.second.mall.modules.account.service.impl;

import com.second.mall.modules.account.dao.ResourceDao;
import com.second.mall.modules.account.entity.Resource;
import com.second.mall.modules.account.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqs
 * @Comments
 * @Date 2021/1/8 16:51
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }
}
