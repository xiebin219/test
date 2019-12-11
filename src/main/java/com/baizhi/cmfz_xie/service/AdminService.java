package com.baizhi.cmfz_xie.service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface AdminService {

    HashMap<String,Object> login(String enCode, String username, String password, HttpSession session);
}
