package com.baizhi.cmfz_xie.service;


import com.baizhi.cmfz_xie.entity.Article;

import java.util.HashMap;

public interface ArticleService {

    HashMap<String, Object> queryByPage(Integer page, Integer rows);

    void delete(Article article);

    void add(Article article);

    void update(Article article);

    void del(Article article);
}
