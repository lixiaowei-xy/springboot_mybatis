package com.lxiaow.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @ClassName ExcelAdmin
 * @Description: 使用easyExcel操作excel
 * @Author lxw
 * @Date 2019/12/9
 **/
@Data
@EqualsAndHashCode
public class EasyExcelAdmin {

    @ExcelProperty(value = "管理员ID",index = 0)
    private Integer admAdminId;

    @ExcelProperty(value = "管理员姓名",index = 1)
    private String admAdminName;

    @ExcelProperty(value = "管理员密码",index = 2)
    private String admAdminPassword;

    @ExcelProperty(value = "是否超级管理员",index = 3)
    private Integer admIsSuperAdmin;

    @ExcelProperty(value = "是否失效",index = 4)
    private Integer admStatus;

    @ExcelProperty(value = "错误次数",index = 5)
    private Integer admErrorCount;

    @ExcelProperty(value = "最后登陆时间",index = 6)
    private Date admLastLoginTime;

    @ExcelProperty(value = "管理员权限省份",index = 7)
    private String admProvince;

    @ExcelProperty(value = "管理员手机号码",index = 8)
    private String admAdminPhone;

    @ExcelProperty(value = "管理员邮箱",index = 9)
    private String admAdminEmail;

    @ExcelProperty(value = "管理员分组",index = 10)
    private Integer groupId;
}
