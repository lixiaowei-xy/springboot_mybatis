package com.lxiaow.controller;

import com.lxiaow.entity.Admin;
import com.lxiaow.service.AdminService;
import com.lxiaow.utils.FileUpload;
import com.lxiaow.utils.excelUtil.ExcelConstant;
import com.lxiaow.utils.excelUtil.ExcelData;
import com.lxiaow.utils.excelUtil.ExcelUtils;
import com.lxiaow.utils.fileUtil.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/testBoot")
public class AdminController {

    private static final Log LOGGER = LogFactory.getLog(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    private ExcelUtils excelUtils;

    @GetMapping(value = "/selectAdmin")
    public Admin selectAdmin(@RequestParam(name = "primaryKey") int primaryKey){
        Admin admin = adminService.selectByPrimaryKey(primaryKey);
        LOGGER.info(admin);
        return admin;
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(name = "file") MultipartFile file){
        try {
            fileUpload.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "上传成功";
    }

    /**
     * @MethodName: excelUpload
     * @Description: 导出Excel
     * @Param:
     * @Return:
     * @Author: lxw
     * @Date: 16:55
    **/
    @PostMapping(value = "/excelUpload")
    public String excelUpload(){
        ExcelData data = new ExcelData();
        List<String> titles = new ArrayList<String>();
        List<List<Object>> rows = new ArrayList<List<Object>>();
        List<Object> adminList = new ArrayList<>();
        titles.add("admAdminId");
        titles.add("admAdminName");
        titles.add("admAdminPassword");
        titles.add("admIsSuperAdmin");
        titles.add("admStatus");
        titles.add("admErrorCount");
        titles.add("admLastLoginTime");
        titles.add("admProvince");
        titles.add("admAdminPhone");

        Admin admin = adminService.selectByPrimaryKey(47);
        adminList.add(admin.getAdmAdminId());
        adminList.add(admin.getAdmAdminName());
        adminList.add(admin.getAdmAdminPassword());
        adminList.add(admin.getAdmIsSuperAdmin());
        adminList.add(admin.getAdmStatus());
        adminList.add(admin.getAdmErrorCount());
        adminList.add(admin.getAdmLastLoginTime());
        adminList.add(admin.getAdmProvince());
        adminList.add(admin.getAdmAdminPhone());

        rows.add(adminList);
        data.setTitles(titles);
        data.setRows(rows);

        try {
            excelUtils.generateExcel(data, ExcelConstant.FILE_PATH+ExcelConstant.FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导出成功";
    }

    @PostMapping(value = "/uploadFile")
    public List<String> uploadFile(HttpServletRequest request){
        List<String> list = new ArrayList<>();
        try {
           list = FileUtil.uploadFile(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
