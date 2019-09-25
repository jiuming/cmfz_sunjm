package com.baizhi;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Admin;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzSunjmApplication.class)
public class CmfzSunjmApplicationTests {

    @Autowired
    public BannerDao bannerDao;

    @Test
    public void poitest1() {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("工作簿1");

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);

        cell.setCellValue("这是第一行第一个单元格");
        try {
            workbook.write(new FileOutputStream(new File("D://TestPoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void poiTest2(){
        Admin admin1 = new Admin("1", "小可爱", "123456");
        Admin admin2 = new Admin("2", "小黑子", "456123");
        Admin admin3 = new Admin("3", "小贱人", "125346");
        Admin admin4 = new Admin("4", "小狗蛋", "145236");
        Admin admin5 = new Admin("5", "小屁孩", "412356");
        List<Admin> admins = Arrays.asList(admin1, admin2, admin3, admin4, admin5);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet0");
        CellRangeAddress addresses = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(addresses);
        sheet.setColumnWidth(3,256*15);
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);

        Font font = workbook.createFont();
        font.setBold(true); //字体加粗
        font.setColor(Font.COLOR_RED); //设置字体颜色
        font.setFontName("微软雅黑");  //设置字体
        font.setFontHeightInPoints((short) 20); //设置字号
        font.setItalic(true);    //斜体
        font.setUnderline(FontFormatting.U_SINGLE);  //下划线

        //创建样式对象
        CellStyle cellStyle1 = workbook.createCellStyle();
        //将字体样式放入样式对象
        cellStyle1.setFont(font);
        //设置居中
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);  //文字居中
        //创建标题行
        Row rows = sheet.createRow(0);

        //创建标题行并设置内容
        Cell cell2 = rows.createCell(0);
        //设置样式
        cell2.setCellStyle(cellStyle1);
        cell2.setCellValue("学生信息表");

        //创建一行    参数：行下标（下标从0开始）
        Row row = sheet.createRow(1);

        //设置行高
        row.setHeight((short)(20*50));

        //创建一个目录行
        String[] titles={"ID","姓名","年龄","生日"};

        //遍历目录行
        for (int i = 0; i < titles.length; i++) {

            //创建一个单元格   参数:单元格下标（下标从0开始）
            Cell cell = row.createCell(i);
            //给单元格设置样式  字体样式
            cell.setCellStyle(cellStyle1);
            //给单元格设置内容
            cell.setCellValue(titles[i]);
        }

        //处理数据行
        for (int i = 0; i < admins.size(); i++) {

            //创建数据行
            Row row1 = sheet.createRow(i + 2);

            //处理第一个单元格的数据
            Cell cell = row1.createCell(0);
            Admin admin = admins.get(i);
            String id = admin.getId();
            cell.setCellValue(id);
            //处理第二个单元格的数据
            row1.createCell(1).setCellValue(admins.get(i).getUsername());
            row1.createCell(2).setCellValue(admins.get(i).getPassword());

            Cell cell1 = row1.createCell(3);
            //给单元格设置样式   日期格式
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(new Date());

        }

        try {
            //导出Excel
            workbook.write(new FileOutputStream(new File("D://TestPoi2.xls")));

            //释放资源
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void poiTest3(){
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("D://TestPoi2.xls")));
            Sheet sheet = workbook.getSheet("sheet0");
            for (int i = 2; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String id = row.getCell(0).getStringCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                System.out.println(new Admin(id,username,password));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goeasyTest(){
        for (int i = 0; i < 10; i++) {

            System.out.println(i);

            //创建随机数
            Random random = new Random();

            Integer[] boysRandoms={random.nextInt(100),random.nextInt(800),random.nextInt(900),
                    random.nextInt(200),random.nextInt(300),random.nextInt(700)
            };
            Integer[] girlsRandoms={random.nextInt(100),random.nextInt(800),random.nextInt(900),
                    random.nextInt(200),random.nextInt(300),random.nextInt(700)
            };

            //创建一个json对象
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
            jsonObject.put("boys", boysRandoms);
            jsonObject.put("girls", girlsRandoms);

            //将json对象转化为json字符串
            String content = jsonObject.toJSONString();

            //配置必要参数    参数： restHost,自己的appkey
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");

            //发布消息   参数：channel管道名称 ,发送的内容
            goEasy.publish("cmfz-sjm", content);

            try {
                //线程休息
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
