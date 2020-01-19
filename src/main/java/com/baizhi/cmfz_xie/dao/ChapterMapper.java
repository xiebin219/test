package com.baizhi.cmfz_xie.dao;

import com.baizhi.cmfz_xie.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterMapper extends Mapper<Chapter> {
    List<Chapter> queryAll(@Param("page") Integer page, @Param("rows") Integer rows, @Param("albumId") String albumId);

    Integer totalcount();

}