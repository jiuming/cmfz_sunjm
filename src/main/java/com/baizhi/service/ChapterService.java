package com.baizhi.service;

import com.baizhi.entity.Chapter;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ChapterService {

    public List<Chapter> getAll(String albumId,int page, int rows);

    public int getCount(String albumId);

    public String addChapter(Chapter chapter);

    public void updateChapter(Chapter chapter);

    public String deleteChapter(String id);
}
