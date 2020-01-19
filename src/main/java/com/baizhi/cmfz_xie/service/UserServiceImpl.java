package com.baizhi.cmfz_xie.service;


import com.baizhi.cmfz_xie.dao.UserMapper;
import com.baizhi.cmfz_xie.entity.User;
import com.baizhi.cmfz_xie.entity.UserExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<User> queryAll(Integer page, Integer rows) {
        List<User> users = userMapper.queryAll(page, rows);
        return users;
    }

    @Override
    public void update(User user) {

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());//获取id
        userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public Integer queryOne() {
        Integer totalcount = userMapper.totalcount();
        return totalcount;
    }

    public List<User> select() {
        List<User> select = userMapper.selectAll();
        return select;
    }
}
