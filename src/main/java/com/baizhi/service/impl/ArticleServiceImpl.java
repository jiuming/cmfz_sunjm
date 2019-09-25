package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.service.ArticleService;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Article> getAll(int page,int rows) {
        return articleDao.selectAll(page*rows-rows,rows);
    }

    @Override
    public List<Article> getAllByGuru(String guru_id) {
        return articleDao.selectAllByGuru(guru_id);
    }

    @Override
    public int getCount() {
        return articleDao.countAll();
    }

    @Override
    public void addArticle(Article article) {
        String id = UUIDUtil.getUUID();
        article.setId(id);
        article.setCrea_date(new Date());
        articleDao.addOne(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateOne(article);
    }

    @Override
    public void deleteArticle(String id) {
        articleDao.deleteOne(id);
    }
}
