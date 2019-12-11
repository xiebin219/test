package com.baizhi.cmfz_xie.dao;

import com.baizhi.cmfz_xie.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {


    Admin queryByUsername(String username);
}