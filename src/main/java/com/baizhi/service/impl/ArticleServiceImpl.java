package com.baizhi.service.impl;

import com.baizhi.cache.AddCache;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.repository.ArticleRepository;
import com.baizhi.service.ArticleService;
import com.baizhi.service.ArticleService;
import com.baizhi.util.UUIDUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

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
        article.setGuru_id("2");
        article.setCrea_date(new Date());
        articleDao.addOne(article);
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateOne(article);
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(String id) {
        articleDao.deleteOne(id);
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getSearch(String word) {
        //处理高亮
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        field.preTags("<span style='color:red'>"); //前缀
        field.postTags("</span>");  //后缀

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("cmfz")  //索引名称
                .withTypes("article")  //索引类型
                .withQuery(QueryBuilders.queryStringQuery(word).field("title").field("content"))  //查询匹配条件
                .withHighlightFields(field)  //处理高亮
                .build();


        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(searchQuery, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                ArrayList<Article> articles = new ArrayList<>();

                SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {

                    //获取原始数据
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                    String id =sourceAsMap.containsKey("id")?sourceAsMap.get("id").toString():"";
                    String title =sourceAsMap.containsKey("title")?sourceAsMap.get("title").toString():"";
                    String author =sourceAsMap.containsKey("author")?sourceAsMap.get("author").toString():"";
                    String content =sourceAsMap.containsKey("content")?sourceAsMap.get("content").toString():"";
                    String guru_id =sourceAsMap.containsKey("guru_id")?sourceAsMap.get("guru_id").toString():"";

                    //判断日期是否存在
                    boolean creaDateFlag = sourceAsMap.containsKey("crea_date");

                    Date crea_dates = null;
                    if(creaDateFlag){
                        String crea_date = hit.getSourceAsMap().get("crea_date").toString();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            crea_dates = dateFormat.parse(crea_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    //将结果封装入对象   没有高亮的数据
                    Article article = new Article(id, title, author, content, crea_dates, guru_id);

                    //处理高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                    if(!title.equals("")){
                        if (highlightFields.get("title") != null) {
                            String titles = highlightFields.get("title").getFragments()[0].toString();
                            article.setTitle(titles);
                        }
                    }

                    if(!content.equals("")){
                        if (highlightFields.get("content") != null) {
                            String contents = highlightFields.get("content").getFragments()[0].toString();
                            article.setContent(contents);
                        }
                    }
                    //将封装好的对象添加到自定义集合中
                    articles.add(article);
                }
                //强转 返回
                return new AggregatedPageImpl<T>((List<T>) articles);
            }
        });
        return articles.getContent();
    }
}
