package com.baizhi.cmfz_xie.dao;

import com.baizhi.cmfz_xie.entity.Albu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbuMapper extends Mapper<Albu> {
    List<Albu> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    Integer totalcount();
}