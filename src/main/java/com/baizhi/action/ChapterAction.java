package com.baizhi.action;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterAction {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(String albumId,int page,int rows) {
        List<Chapter> chapters = chapterService.getAll(albumId,page,rows);
        int count = chapterService.getCount(albumId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("rows", chapters);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Chapter chapter,String oper,HttpSession session){
        String id=null;
        if("add".equals(oper)) {
            id = chapterService.addChapter(chapter);
        }
        if("edit".equals(oper)){
            if ("".equals(chapter.getUrl()))
                chapter.setUrl(null);
            id=chapter.getId();
            chapterService.updateChapter(chapter);
        }
        if ("del".equals(oper)){
            String url = chapterService.deleteChapter(chapter.getId());
            if (!("".equals(url)||url==null)){
                String realPath = session.getServletContext().getRealPath("/upload/audio");
                File file = new File(realPath,url);
                if (file.exists())
                    file.delete();
            }
        }
        return id;
    }

    @RequestMapping("/upload")
    public Map<String,Object> upload(MultipartFile url,String id,HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            if(!url.isEmpty()){
                Chapter chapter = new Chapter();
                String realPath = session.getServletContext().getRealPath("/upload/audio");
                File file =new File(realPath);
                if(!file.exists()){
                    file.mkdirs();
                }
                String filename = url.getOriginalFilename();
                String name = new Date().getTime() + filename;
                try {
                    url.transferTo(new File(realPath,name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                chapter.setUrl(name);
                Double size = (double) url.getSize()/1024/1024;
                DecimalFormat decimalFormat =new DecimalFormat("0.00");
                chapter.setSize(decimalFormat.format(size)+"MB");
                //获取文件时长
                AudioFile audioFile = AudioFileIO.read(new File(realPath, name));
                //获取时长 秒
                int length = audioFile.getAudioHeader().getTrackLength();
                chapter.setDuration(length/60+"分"+length%60+"秒");
                chapter.setId(id);
                chapterService.updateChapter(chapter);
                map.put("success","200");
                map.put("message","上传成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400");
            map.put("message","上传失败");
        }
        return map;
    }

    @RequestMapping("/download")
    public void download(String fileName,HttpSession session, HttpServletResponse response){
        String realPath = session.getServletContext().getRealPath("/upload/audio");
        try {
            FileInputStream is = new FileInputStream(new File(realPath, fileName));
            ServletOutputStream os = response.getOutputStream();
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
            IOUtils.copy(is,os);
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
