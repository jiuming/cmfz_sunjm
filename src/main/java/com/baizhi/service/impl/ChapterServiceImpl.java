package com.baizhi.service.impl;

import com.baizhi.cache.AddCache;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @AddCache
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Chapter> getAll(String albumId,int page,int rows) {
        return chapterDao.selectAllByAlbum(albumId,page*rows-rows,rows);
    }

    @AddCache
    @Override
    public int getCount(String albumId) {
        return chapterDao.countAllByAlbum(albumId);
    }

    @Override
    public String addChapter(Chapter chapter) {
        String id = UUIDUtil.getUUID();
        chapter.setId(id);
        chapter.setUp_date(new Date());
        chapterDao.addOne(chapter);
        return id;
    }

    @Override
    public void updateChapter(Chapter chapter) {
        chapterDao.updateOne(chapter);
    }

    @Override
    public String deleteChapter(String id) {
        String url = chapterDao.selectOne(id).getUrl();
        chapterDao.deleteOne(id);
        return url;
    }
}
