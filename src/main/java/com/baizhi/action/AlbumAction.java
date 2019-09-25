package com.baizhi.action;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumAction {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(int page,int rows) {
        List<Album> albums = albumService.getAll(page,rows);
        int count = albumService.getCount();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("rows", albums);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Album album,String oper,HttpSession session){
        String id=null;
        if("add".equals(oper)) {
            id = albumService.addAlbum(album);
        }
        if("edit".equals(oper)){
            if ("".equals(album.getCover()))
                album.setCover(null);
            albumService.updateAlbum(album);
            id=album.getId();
        }
        if ("del".equals(oper)){
            String cover = albumService.deleteAlbum(album.getId());
            if (!("".equals(cover)||cover==null)){
                String realPath = session.getServletContext().getRealPath("/upload/cover");
                File file = new File(realPath,cover);
                if (file.exists())
                    file.delete();
            }
        }
        return id;
    }

    @RequestMapping("/upload")
    public void upload(MultipartFile cover,String id,HttpSession session){
        if(!cover.isEmpty()){
            Album album =new Album();
            String realPath = session.getServletContext().getRealPath("/upload/cover");
            File file =new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            String filename = cover.getOriginalFilename();
            String name = new Date().getTime() + filename;
            try {
                cover.transferTo(new File(realPath,name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            album.setCover(name);
            album.setId(id);
            albumService.updateAlbum(album);
        }

    }

}
