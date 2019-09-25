package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> getAll(int page,int rows) {
        return albumDao.selectAll(page*rows-rows,rows);
    }

    @Override
    public int getCount() {
        return albumDao.countAll();
    }

    @Override
    public String addAlbum(Album album) {
        String id = UUIDUtil.getUUID();
        album.setId(id);
        album.setCrea_date(new Date());
        albumDao.addOne(album);
        return id;
    }

    @Override
    public void updateAlbum(Album album) {
        albumDao.updateOne(album);
    }

    @Override
    public String deleteAlbum(String id) {
        Album album = albumDao.selectOne(id);
        if(album.getCount()==0){
            albumDao.deleteOne(id);
        }
        return album.getCover();
    }

    @Override
    public Album getOne(String id) {
        return albumDao.selectOne(id);
    }
}
