package com.lxiaow.controller;

import com.lxiaow.entity.admin;
import com.lxiaow.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testBoot")
public class adminController {

    @Autowired
    private  adminService adminService;

    @RequestMapping(value = "selectAdmin")
    public String selectAdmin(){
        admin admin = adminService.selectByPrimaryKey(47);
        System.out.println(admin);
        return admin.toString();
    }
}
