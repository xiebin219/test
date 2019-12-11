package com.baizhi.cmfz_xie.controller;

import com.baizhi.cmfz_xie.entity.Albu;
import com.baizhi.cmfz_xie.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbuController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) throws Exception {
        Map<String, Object> maps = new HashMap<String, Object>();
        //获取当前页的数据
        List<Albu> employees = albumService.queryAll(page,rows);
        //设置当前页的数据
        maps.put("rows", employees);
        //设置当前页号
        maps.put("page", page);
        //设置总条数
        //获取总条数
        Integer totalcount = albumService.queryOne();
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


}
