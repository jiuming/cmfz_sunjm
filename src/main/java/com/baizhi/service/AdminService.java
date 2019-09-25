package com.baizhi.service;

import com.baizhi.entity.Album;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminService {

    public Map<String,Object> login(String enCode, String username, String password, HttpSession session);

}
