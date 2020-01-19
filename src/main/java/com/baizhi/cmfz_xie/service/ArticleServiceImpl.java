package com.baizhi.cmfz_xie.service;


import com.baizhi.cmfz_xie.dao.ArticleMapper;
import com.baizhi.cmfz_xie.entity.Article;
import com.baizhi.cmfz_xie.entity.ArticleExample;
import com.baizhi.cmfz_xie.entity.UserExample;
import com.baizhi.cmfz_xie.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        //总条数  records
        UserExample example = new UserExample();
        Integer records = articleMapper.selectCountByExample(example);

        map.put("records", records);

        //总页数  total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);

        //当前页  page
        map.put("page", page);

        //数据  rows
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleMapper.selectByRowBounds(new Article(), rowBounds);
        map.put("rows", articles);

        return map;
    }

    @Override
    public void delete(Article article) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(article.getId());
        articleMapper.deleteByExample(example);
    }

    @Override
    public void add(Article article) {

        article.setId(UUIDUtil.getUUID());
        article.setCrea_date(new Date());
        articleMapper.insertSelective(article);
    }

    @Override
    public void update(Article article) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(article.getId());
        articleMapper.updateByExampleSelective(article, example);
    }

    @Override
    public void del(Article article) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(article.getId());
        articleMapper.deleteByExample(example);
    }
}
