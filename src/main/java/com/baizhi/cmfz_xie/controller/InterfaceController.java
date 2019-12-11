/*
package com.baizhi.cmfz_xie.controller;

import com.baizhi.cmfz_xie.entity.Albu;
import com.baizhi.cmfz_xie.entity.Article;
import com.baizhi.cmfz_xie.entity.Banner;
import com.baizhi.cmfz_xie.service.AlbumService;
import com.baizhi.cmfz_xie.service.ArticleService;
import com.baizhi.cmfz_xie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class InterfaceController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @RequestMapping("first_page")
    public HashMap<String,Object>firstpage(String uid, String type, String sub_type){

        HashMap<Object, Object> map = new HashMap<>();
        //判断type
        if(type.equals("all")){
            //展示首页数据
            List<Banner> banners = bannerService.queryAll(1, 100);
            List<Albu> albus = albumService.queryAll(1, 100);
            HashMap<String, Object> articleMap = articleService.queryByPage(1, 100);
            ArrayList<Article> articlerows = (ArrayList)articleMap.get("rows");
            map.put("article",articlerows);
        }
        if(type.equals("wen")){
            //展示闻数据
            List<Albu> albus = albumService.queryAll(1, 100);
        }
        if(type.equals("si")){
            if(sub_type.equals("ssyj")){
                //查询自己上师的文章

                HashMap<String, Object> articleMap = articleService.queryByPage(1, 100);
                ArrayList<Article> articlerows = (ArrayList)articleMap.get("rows");
                map.put("article",articlerows);
            }
            if(sub_type.equals("xmfy")){
                //查询其他上师的文章

                HashMap<String, Object> articleMap = articleService.queryByPage(1, 100);
                ArrayList<Article> articlerows = (ArrayList)articleMap.get("rows");
                map.put("article",articlerows);

            }

        }

        return map;    //由于用了map写法和list的写法所以不能用hanshmap返回
    }

}
*/
