package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao extends BaseDao<Chapter>{
    public List<Chapter> selectAllByAlbum(@Param("albumId") String albumId, @Param("start") int start, @Param("rows") int rows);

    public int countAllByAlbum(String albumId);

    public Chapter selectOne(String id);
}
