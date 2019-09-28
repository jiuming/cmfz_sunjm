package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
@Document(indexName = "cmfz",type = "article")   //索引名   索引类型
public class Article implements Serializable {

    @Id
    private String id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword)
    private String author;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date)
    private Date crea_date;
    @Field(type = FieldType.Keyword)
    private String guru_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCrea_date() {
        return crea_date;
    }

    public void setCrea_date(Date crea_date) {
        this.crea_date = crea_date;
    }

    public String getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(String guru_id) {
        this.guru_id = guru_id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", crea_date=" + crea_date +
                ", guru_id='" + guru_id + '\'' +
                '}';
    }

    public Article() {
    }

    public Article(String id, String title, String author, String content, Date crea_date, String guru_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.crea_date = crea_date;
        this.guru_id = guru_id;
    }
}
