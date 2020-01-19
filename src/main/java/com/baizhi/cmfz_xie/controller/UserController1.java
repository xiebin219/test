package com.baizhi.cmfz_xie.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmfz_xie.entity.User;
import com.baizhi.cmfz_xie.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user1")
public class UserController1 {
    @Resource
    UserService userService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) throws Exception {
        Map<String, Object> maps = new HashMap<String, Object>();
        //获取当前页的数据
        List<User> employees = userService.queryAll(page, rows);
        //设置当前页的数据
        maps.put("rows", employees);
        //设置当前页号
        maps.put("page", page);
        //设置总条数
        //获取总条数
        Integer totalcount = userService.queryOne();
        maps.put("records", totalcount);
        //设置总页数
        Integer pagecount = 0;
        if (totalcount % rows == 0) {
            pagecount = totalcount / rows;
        } else {
            pagecount = totalcount / rows + 1;
        }
        maps.put("total", pagecount);
        return maps;
    }

    @RequestMapping("edit")
    public String edit(User user, String oper) {

        String id = null;

        //添加
        if (oper.equals("add")) {


        }
        //修改
        if (oper.equals("edit")) {
            userService.update(user);
        }

        //删除
        if (oper.equals("del")) {

        }
        return id;
    }

    @RequestMapping("daoData")
    @ResponseBody
    public List<User> daoData() {
        List<User> list = userService.select();
        for (User user1 : list) {
            user1.setAvatar("src/main/webapp/upload/photo/" + user1.getAvatar());   //导出图片
        }
        //导出配置的参数
        ExportParams exportParams = new ExportParams("用户", "学生");
        //参数：导出的配置，到出数据对应的实体类
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, User.class, list);
        try {
            //导出
            sheets.write(new FileOutputStream(new File("c://daima/Testdao1.xls")));
            sheets.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
