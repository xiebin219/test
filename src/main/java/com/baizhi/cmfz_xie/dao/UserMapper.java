package com.baizhi.cmfz_xie.dao;

import com.baizhi.cmfz_xie.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    void update(User user);
    List<User> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);
    Integer totalcount();
}