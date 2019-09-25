package com.baizhi.action;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/article")
public class ArticleAction {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(int page,int rows) {
        List<Article> articles = articleService.getAll(page,rows);
        int count = articleService.getCount();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("rows", articles);
        return map;
    }

    @RequestMapping("/addArticle")
    public Map addArticle(Article article){
        articleService.addArticle(article);
        return new HashMap();
    }

    @RequestMapping("/updateArticle")
    public Map updateArticle(Article article){
        articleService.updateArticle(article);
        return new HashMap();
    }

    @RequestMapping("/delArticle")
    public Map delArticle(String id){
        articleService.deleteArticle(id);
        return new HashMap();
    }
}
