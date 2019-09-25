package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String, Object> login(String enCode, String username, String password, HttpSession session) {
        Map<String,Object> map = new HashMap<String, Object>();
        String imagCode = (String)session.getAttribute("imagCode");
        Admin admin = adminDao.selectOne(username);
        if (imagCode.equals(enCode)){
            if (admin!=null){
                if (password.equals(admin.getPassword())){
                    session.setAttribute("admin",admin);
                    map.put("code","200");
                    map.put("message","登陆成功");
                }else {
                    map.put("code","400");
                    map.put("message","密码错误");
                }
            }else {
                map.put("code","400");
                map.put("message","用户名不存在");
            }
        }else {
            map.put("code","400");
            map.put("message","验证码错误");
        }
        return map;
    }
}
