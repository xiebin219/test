package com.baizhi.cmfz_xie.service;

import com.baizhi.cmfz_xie.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAll(Integer page, Integer rows);

    void update(User user);

    Integer queryOne();

    List<User> select();
}
