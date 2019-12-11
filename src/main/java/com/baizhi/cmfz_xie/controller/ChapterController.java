package com.baizhi.cmfz_xie.controller;

import com.baizhi.cmfz_xie.entity.Chapter;
import com.baizhi.cmfz_xie.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows,String albumId) throws Exception {
        Map<String, Object> maps = new HashMap<String, Object>();
        //获取当前页的数据
        List<Chapter> employees = chapterService.queryAll(page,rows,albumId);
        //设置当前页的数据
        maps.put("rows", employees);
        //设置当前页号
        maps.put("page", page);
        //设置总条数
        //获取总条数
        Integer totalcount = chapterService.queryOne();
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
    public String edit(Chapter chapter, String oper, String albumId) {

        String id=null;
        if(oper.equals("add")){
            System.out.println(albumId+"wwwww");

            chapter.setAlbum_id(albumId);
            id = chapterService.add(chapter);
        }

        if(oper.equals("edit")){

        }

        if(oper.equals("del")){

        }

        return id;
    }

    @RequestMapping("uploadChapter")
    public void uploadChapter(MultipartFile src, String id, HttpServletRequest request){

        chapterService.uploadChapter(src,id,request);

    }

    @RequestMapping("audioDownload")
    public void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response){
        chapterService.audioDownload(audioName,request,response);
    }

}
