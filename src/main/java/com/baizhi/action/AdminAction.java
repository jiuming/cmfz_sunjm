package com.baizhi.action;

import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/getImageCode")
    public void getImageCode(HttpSession session, HttpServletResponse response){

        //1.获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();

        //2.存储验证码字符
        session.setAttribute("imagCode",code);

        //3.将验证码字符生成图片
        BufferedImage image = ImageCodeUtil.createImage(code);

        //4.设置响应类型
        response.setContentType("image/png");

        //5.将图片响应到页面
        try {
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String enCode, String username, String password, HttpSession session){
        Map<String, Object> map = adminService.login(enCode, username, password, session);
        return map;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }
}
