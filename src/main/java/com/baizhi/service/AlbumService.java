package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {

    public List<Album> getAll(int page, int rows);

    public Album getOne(String id);

    public int getCount();

    public String addAlbum(Album album);

    public void updateAlbum(Album album);

    public String deleteAlbum(String id);
}
