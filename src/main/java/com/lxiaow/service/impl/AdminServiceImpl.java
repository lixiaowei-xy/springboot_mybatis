package com.lxiaow.service.impl;

import com.lxiaow.entity.Admin;
import com.lxiaow.mapper.AdminMapper;
import com.lxiaow.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin selectByPrimaryKey(Integer admAdminId) {
        Admin admin = adminMapper.selectByPrimaryKey(admAdminId);
        return admin;
    }
}
