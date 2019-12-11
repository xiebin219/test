package com.baizhi.cmfz_xie.dao;

import com.baizhi.cmfz_xie.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {

    void update(Banner banner);
    List<Banner> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    Integer totalcount();
}