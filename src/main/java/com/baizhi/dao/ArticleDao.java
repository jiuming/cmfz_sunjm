package com.baizhi.dao;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleDao extends BaseDao<Article>{

    public List<Article> selectAllByGuru(String guru_id);
}
