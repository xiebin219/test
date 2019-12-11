package com.baizhi.cmfz_xie.controller;


import com.baizhi.cmfz_xie.entity.Article;
import com.baizhi.cmfz_xie.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("queryByPage")
    public HashMap<String,Object> queryByPage(Integer page,Integer rows){

        HashMap<String, Object> map = articleService.queryByPage(page, rows);

        return map;
    }

    @RequestMapping("edit")
    public String edit(Article article, String oper){

        System.out.println("==controller: user== "+article);

        String id=null;

        //添加
        if(oper.equals("add")){
            //id= articleService.add(banner);
        }
        //修改
        if(oper.equals("edit")){
            //articleService.update(user);
        }
        //删除
        if(oper.equals("dell")){
           // articleService.delete(article);
        }
        return id;
    }

    @RequestMapping("add")
    public String add(Article article){

        System.out.println("article==="+article);
        articleService.add(article);

        return "";
    }
    @RequestMapping("del")
    public String del(Article article){

        System.out.println("article==="+article);
        articleService.del(article);

        return "";
    }



    @RequestMapping("update")
    public HashMap<String,Object> update(Article article){

        System.out.println("article==="+article);

        HashMap<String, Object> map = new HashMap<>();
        articleService.update(article);

        map.put("success","200");
        return map;
    }



}
