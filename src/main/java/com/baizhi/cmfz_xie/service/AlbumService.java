package com.baizhi.cmfz_xie.service;

import com.baizhi.cmfz_xie.entity.Albu;

import java.util.List;

public interface AlbumService {
    List<Albu> queryAll(Integer page, Integer rows);
    Integer queryOne();
}
