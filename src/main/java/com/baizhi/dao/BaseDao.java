package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    public List<T> selectAll(@Param("start") int start, @Param("rows") int rows);

    public int countAll();

    public void addOne(T t);

    public void updateOne(T t);

    public void deleteOne(String id);
}
