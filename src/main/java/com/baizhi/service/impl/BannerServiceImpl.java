package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Banner> getAll(int page,int rows) {
        return bannerDao.selectAll(page*rows-rows,rows);
    }

    @Override
    public int getCount() {
        return bannerDao.countAll();
    }

    @Override
    public String addBanner(Banner banner) {
        String id = UUIDUtil.getUUID();
        banner.setId(id);
        banner.setStatus(1);
        banner.setUp_date(new Date());
        bannerDao.addOne(banner);
        return id;
    }

    @Override
    public void updateBanner(Banner banner) {
        System.out.println(banner);
        bannerDao.updateOne(banner);
    }

    @Override
    public void deleteBanner(String id) {

    }
}
