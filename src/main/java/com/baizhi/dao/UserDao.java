package com.baizhi.dao;

import com.baizhi.dto.City;
import com.baizhi.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    public String[] selectMonthCountBySex(String sex);

    public List<City> selectCityCountBySex(String sex);

    public User selectOneByPhone(String phone);
}
