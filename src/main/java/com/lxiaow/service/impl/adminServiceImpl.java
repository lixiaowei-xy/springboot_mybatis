package com.lxiaow.service.impl;

import com.lxiaow.entity.admin;
import com.lxiaow.mapper.adminMapper;
import com.lxiaow.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImpl implements adminService {

    @Autowired
    adminMapper adminMapper;

    @Override
    public admin selectByPrimaryKey(Integer admAdminId) {
        admin admin = adminMapper.selectByPrimaryKey(admAdminId);
        return admin;
    }
}
