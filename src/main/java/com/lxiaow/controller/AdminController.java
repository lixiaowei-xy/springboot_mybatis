package com.lxiaow.controller;

import com.lxiaow.entity.Admin;
import com.lxiaow.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testBoot")
public class AdminController {

    private static final Log LOGGER = LogFactory.getLog(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/selectAdmin")
    public Admin selectAdmin(@RequestParam(name = "primaryKey") int primaryKey){
        Admin admin = adminService.selectByPrimaryKey(primaryKey);
        LOGGER.info(admin);
        return admin;
    }
}
