package com.baizhi.cmfz_xie.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;

import java.util.List;

public class Teacher {

    @Excel(name = "ID")
    private String id;
    @Excel(name="名字")
    private String name;
    @ExcelCollection(name="学生")
    private List<Student> students;
}
