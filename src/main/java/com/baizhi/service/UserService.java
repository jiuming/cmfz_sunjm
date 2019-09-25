package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    public List<User> getAll(int page, int rows);

    public int getCount();

    public String login(String phone,String password);

    public Map<String,String> regist(String phone,String password);

    public void updateUser(User user);

    public Map<String,Object> countUserBySex();

    public Map[] countCityBySex();

    public void getExcel(HttpServletResponse response, HttpSession session);

    public String getCode(String phone);

}
