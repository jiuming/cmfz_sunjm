package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {
    private String id;
    private String title;
    private String cover;
    private String author;
    private Integer score;
    private String broadcast;
    private int count;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date crea_date;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", author='" + author + '\'' +
                ", score=" + score +
                ", broadcast='" + broadcast + '\'' +
                ", count=" + count +
                ", content='" + content + '\'' +
                ", crea_date=" + crea_date +
                '}';
    }
}
