package com.baizhi.cmfz_xie.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "头像", type = 2, width = 30, height = 20)
    private String avatar;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "加密")
    private String salt;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "法名")
    private String law_name;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "出生日期", format = "yyyy年MM月dd日")
    private Date crea_date;
    @Excel(name = "上师id")
    private String guru_id;

}