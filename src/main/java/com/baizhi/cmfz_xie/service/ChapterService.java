package com.baizhi.cmfz_xie.service;

import com.baizhi.cmfz_xie.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ChapterService {
    List<Chapter> queryAll(Integer page, Integer rows, String albumId);

    Integer queryOne();

    String add(Chapter chapter);

    void uploadChapter(MultipartFile src, String id, HttpServletRequest request);

    void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response);
}
