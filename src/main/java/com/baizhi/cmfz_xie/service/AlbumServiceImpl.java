package com.baizhi.cmfz_xie.service;

import com.baizhi.cmfz_xie.dao.AlbuMapper;
import com.baizhi.cmfz_xie.entity.Albu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Resource
    AlbuMapper albuMapper;
    @Override
    public List<Albu> queryAll(Integer page, Integer rows) {
        List<Albu> albus = albuMapper.queryAll(page, rows);
        return albus;
    }

    @Override
    public Integer queryOne() {
        Integer totalcount = albuMapper.totalcount();
        return totalcount;
    }
}
