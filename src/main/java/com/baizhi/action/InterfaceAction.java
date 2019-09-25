package com.baizhi.action;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 孙九明
 * 手机App接口
 */
@RestController
public class InterfaceAction {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;

    @RequestMapping("/first_page")
    public Map<String,Object> first_page(String uid, String type, String sub_type){
        Map<String,Object> map = new HashMap<>();
        if (uid!=null){
            if ("all".equals(type)){
                List<Banner> banners = bannerService.getAll(-1, -1);
                List<Album> albums = albumService.getAll(1, 6);
                List<Article> articles = articleService.getAll(1, 2);
                map.put("banner",banners);
                map.put("album",albums);
                map.put("article",articles);
            }else if ("wen".equals(type)){
                List<Album> albums = albumService.getAll(1, 20);
                map.put("album",albums);
            }else if ("si".equals(type)){
                if (sub_type!=null){
                    List<Article> articles = articleService.getAllByGuru(sub_type);
                    map.put("article",articles);
                }else {
                    List<Article> articles = articleService.getAll(1, 2);
                    map.put("article",articles);
                }
            }

        }
        return map;
    }

    @RequestMapping("/detail/wen")
    public Map<String,Object> showOneAlbum(String uid,String id){
        Map<String,Object> map = new HashMap<>();
        if (uid!=null){
            Album album = albumService.getOne(id);
            List<Chapter> chapters = chapterService.getAll(id, 1, 2);
            map.put("introduction",album);
            map.put("list",chapters);
        }
        return map;
    }

    @RequestMapping("/account/login")
    public String login(String phone,String password){
        String message = userService.login(phone, password);
        return message;
    }

    @RequestMapping("/account/regist")
    public Map<String,String> regist(String phone,String password){
        Map message = userService.regist(phone, password);
        return message;
    }

    @RequestMapping("/identify/obtain")
    public void check(String phone, HttpSession session){
        session.setAttribute(phone,userService.getCode(phone));
    }

    @RequestMapping("/identify/check")
    public Map<String,String> check(String phone,String code,HttpSession session){
        Map<String,String> map =new HashMap<>();
        if (session.getAttribute(phone).equals(code)){
            map.put("result","success");
            session.removeAttribute(phone);
        }
        else map.put("result","fail");
        return map;
    }
}
