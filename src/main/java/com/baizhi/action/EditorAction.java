package com.baizhi.action;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/editor")
public class EditorAction {

    @RequestMapping("/uploadEditor")
    public Map<String,Object> uploadEditor(MultipartFile photo, HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");
            File file = new File(realPath);
            if (!file.exists())
                file.mkdirs();
            String filename = photo.getOriginalFilename();
            String photoname = new Date().getTime() + "-" + filename;
            photo.transferTo(new File(realPath,photoname));
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();
            String url = scheme+"://"+serverName+":"+serverPort+contextPath+"/upload/editor/"+photoname;
            map.put("error",0);
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("message","上传失败");
        }
        return map;
    }

    @RequestMapping("/queryPhotos")
    public HashMap<String, Object> queryPhotos(HttpServletRequest request){

        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String url = scheme+"://"+serverName+":"+serverPort+contextPath+"/upload/editor/";


        HashMap<String, Object> maps = new HashMap<>();

        ArrayList<Object> lists = new ArrayList<>();

        //获取文件的绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");

        //获取文件
        File file = new File(realPath);

        //获取文件夹中所有的   文件名
        String[] names = file.list();

        //遍历文件名
        for (int i = 0; i < names.length; i++) {
            //文件名
            String name = names[i];

            /*
            *
                {
                  "is_dir": false,
                  "has_file": false,
                  "filesize": 14966,
                  "dir_path": "",
                  "is_photo": true,
                  "filetype": "jpg",
                  "filename": "1_192040_1.jpg",
                  "datetime": "2018-06-06 00:36:39"
                }
            * */
            HashMap<String, Object> map = new HashMap<>();
            map.put("is_dir",false);  //是否是文件夹
            map.put("has_file",false);  //是否有文件
            File file1 = new File(realPath, name);
            map.put("filesize",file1.length());  //文件的大小
            map.put("is_photo",true);  //是否是图片
            String extension = FilenameUtils.getExtension(name);
            map.put("filetype",extension);  //图片的类型
            map.put("filename",name);  //图片的名字

            //字符串拆分
            String[] strs = name.split("-");
            String times =strs[0];
            long time = Long.parseLong(times);
            //指定一个日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String datetime = dateFormat.format(time);

            /*"2018-06-06 00:36:39"*/
            map.put("datetime",datetime);  //图片上传时间

            //将数据放入集合
            lists.add(map);
        }

        /*
        *   "moveup_dir_path": "",
              "current_dir_path": "",
              "current_url": "/ke4/php/../attached/",
              "total_count": 5,
              "file_list":
        * */
        maps.put("current_url",url);
        maps.put("total_count",lists.size());
        maps.put("file_list",lists);
        return maps;
    }
}
