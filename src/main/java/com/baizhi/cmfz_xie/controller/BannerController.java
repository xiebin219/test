package com.baizhi.cmfz_xie.controller;

import com.baizhi.cmfz_xie.entity.Banner;
import com.baizhi.cmfz_xie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) throws Exception {
        Map<String, Object> maps = new HashMap<String, Object>();
        //获取当前页的数据
        List<Banner> employees = bannerService.queryAll(page, rows);
        //设置当前页的数据
        maps.put("rows", employees);
        //设置当前页号
        maps.put("page", page);
        //设置总条数
        //获取总条数
        Integer totalcount = bannerService.queryOne();
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
    public String edit(Banner banner, String oper) {

        String id = null;

        //添加
        if (oper.equals("add")) {
            System.out.println(banner + "xie");
            id = bannerService.add(banner);
        }
        //修改
        if (oper.equals("edit")) {
            bannerService.update(banner);
        }

        //删除
        if (oper.equals("del")) {
            bannerService.del(banner);
        }
        return id;
    }

    @RequestMapping("uploadBanner")
    public void uploadBanner(MultipartFile src_img, String id, HttpServletRequest request) {

        bannerService.bannerUpload(src_img, id, request);

    }

}
