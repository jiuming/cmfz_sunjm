package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {

    public List<Article> getAll(int page, int rows);

    public List<Article> getAllByGuru(String guru_id);

    public int getCount();

    public void addArticle(Article article);

    public void updateArticle(Article article);

    public void deleteArticle(String id);

    public List<Article> getSearch(String word);
}
