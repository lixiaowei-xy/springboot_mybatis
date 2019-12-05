package com.lxiaow.controller;

import com.lxiaow.entity.Admin;
import com.lxiaow.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ThymeleafController
 * @Description: TODO
 * @Author lxw
 * @Date 2019/12/5
 **/
@RestController
public class ThymeleafController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "show.html", method = RequestMethod.GET)
    public Model show(Model model){
        Admin admin = adminService.selectByPrimaryKey(47);
        model.addAttribute("adminEmail",admin.getAdmAdminEmail());
        model.addAttribute("adminName",admin.getAdmAdminName());
        return model;
    }
}
