package com.baizhi.action;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banner")
public class BannerAction {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(int page,int rows) {
        List<Banner> banners = bannerService.getAll(page,rows);
        int count = bannerService.getCount();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("rows", banners);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Banner banner,String oper){
        String id=null;
        if(banner.getImg_path()!=null&&"add".equals(oper)) {
            id = bannerService.addBanner(banner);
        }
        if("edit".equals(oper)){
            if ("".equals(banner.getImg_path()))
                banner.setImg_path(null);
            bannerService.updateBanner(banner);
            id=banner.getId();
        }
        if ("del".equals(oper)){
            bannerService.deleteBanner(banner.getId());
            id=banner.getId();
        }
        return id;
    }

    @RequestMapping("/upload")
    public void upload(MultipartFile img_path,String id,HttpSession session){
        if(!img_path.isEmpty()){
            Banner banner =new Banner();
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
            banner.setImg_path(name);
            banner.setId(id);
            bannerService.updateBanner(banner);
        }
    }

    @RequestMapping("/modify")
    public void modify(Banner banner){
        banner.setStatus(banner.getStatus()*(-1));
        bannerService.updateBanner(banner);
    }
}
