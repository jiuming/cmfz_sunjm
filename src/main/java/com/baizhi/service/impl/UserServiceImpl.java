package com.baizhi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.cache.AddCache;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.Md5Utils;
import com.baizhi.util.PhoneMsgUtil;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @AddCache
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> getAll(int page,int rows) {
        return userDao.selectAll(page*rows-rows,rows);
    }

    @AddCache
    @Override
    public int getCount() {
        return userDao.countAll();
    }

    @Override
    public String login(String phone, String password) {
        User user = userDao.selectOneByPhone(phone);
        if (user!=null&&user.getPassword().equals(Md5Utils.getMd5Code(password+user.getSalt())))
            return JSON.toJSONString(user);
        else {
            Map<String,String> map = new HashMap<>();
            map.put("error","-200");
            map.put("errmsg","密码错误");
            return JSON.toJSONString(map);
        }
    }

    @Override
    public Map<String,String> regist(String phone,String password) {
        Map<String,String> map = new HashMap<>();
        if (userDao.selectOneByPhone(phone)==null){
            User user = new User();
            String id = UUIDUtil.getUUID();
            String salt = Md5Utils.getSalt(8);
            String md5Code = Md5Utils.getMd5Code(password+salt);
            user.setId(id);
            user.setPassword(md5Code);
            user.setSalt(salt);
            user.setStatus(1);
            user.setCrea_date(new Date());
            user.setPhone(phone);
            user.setPassword(password);
            userDao.addOne(user);
            map.put("password",md5Code);
            map.put("uid",id);
            map.put("phone",phone);
        }else {
            map.put("error","-200");
            map.put("error_msg","该手机号已经存在");
        }
        return map;
    }

    @Override
    public void updateUser(User user) {
        userDao.updateOne(user);
    }

    @Override
    public Map<String,Object> countUserBySex() {
        Map<String,Object> map = new HashMap<>();
        String[] months = {"一月", "二月", "三月", "四月", "五月", "六月"};
        map.put("month",months);
        map.put("boys",userDao.selectMonthCountBySex("男"));
        map.put("girls",userDao.selectMonthCountBySex("女"));
        return map;
    }

    @Override
    public void getExcel(HttpServletResponse response, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/upload/image");
        String fileName = new Date().getTime()+"用户信息表.xls";
        List<User> users = userDao.selectAll(0,0);
        for (User user : users) {
            user.setAvatar(realPath+"/"+user.getAvatar());
        }
        //配置相关参数  参数：title:文档的标题,sheetName:工作簿的名字,要导出的实体类对象,要导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持明法洲用户统计","用户信息表"),User.class, users);
        try {
            ServletOutputStream os = response.getOutputStream();
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
            //导出Excel
            workbook.write(os);
            os.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map[] countCityBySex() {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("title","男");
        map1.put("cities",userDao.selectCityCountBySex("男"));
        Map<String,Object> map2 = new HashMap<>();
        map2.put("title","女");
        map2.put("cities",userDao.selectCityCountBySex("女"));
        Map[] maps = new Map[]{map1,map2};
        return maps;
    }

    @Override
    public String getCode(String phone) {
        String random = PhoneMsgUtil.getRandom(6);
        PhoneMsgUtil.getPhones(phone,random);
        return random;
    }

}
