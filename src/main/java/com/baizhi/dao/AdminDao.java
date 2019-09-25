package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDao {
    public Admin selectOne(String username);
}
