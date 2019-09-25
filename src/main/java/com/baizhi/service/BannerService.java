package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {

    public List<Banner> getAll(int page,int rows);

    public int getCount();

    public String addBanner(Banner banner);

    public void updateBanner(Banner banner);

    public void deleteBanner(String id);
}
