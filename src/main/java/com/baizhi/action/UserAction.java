package com.baizhi.action;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(int page,int rows) {
        List<User> users = userService.getAll(page,rows);
        int count = userService.getCount();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("rows", users);
        return map;
    }

    @RequestMapping("/showUserCount")
    public Map<String, Object> showUserCount() {
        Map<String, Object> map = userService.countUserBySex();
        return map;
    }

    @RequestMapping("/upload")
    public void upload(MultipartFile img_path,String id,HttpSession session){
        User user =new User();
        if(!img_path.isEmpty()){
            String realPath = session.getServletContext().getRealPath("/upload/photo");
            File file =new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            String filename = img_path.getOriginalFilename();
            String name = new Date().getTime() + filename;
            try {
                img_path.transferTo(new File(realPath,name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setAvatar(name);
        }
        user.setId(id);
        userService.updateUser(user);
    }

    @RequestMapping("/modify")
    public void modify(User user){
        user.setStatus(user.getStatus()*(-1));
        userService.updateUser(user);
    }

    @RequestMapping("/outExcel")
    public void outExcel(HttpServletResponse response,HttpSession session){
        userService.getExcel(response,session);
    }

    @RequestMapping("/showCityCount")
    public Map[] showCityCount(){
        Map[] maps = userService.countCityBySex();
        return maps;
    }
}
