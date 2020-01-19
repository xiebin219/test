package com.baizhi.cmfz_xie.service;

import com.baizhi.cmfz_xie.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BannerService {
    List<Banner> queryAll(Integer page, Integer rows);

    void del(Banner banner);

    void update(Banner banner);

    Integer queryOne();

    String add(Banner banner);

    void bannerUpload(MultipartFile srcImg, String id, HttpServletRequest request);
}
