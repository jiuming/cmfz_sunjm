package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private String id;
    @Excel(name = "头像",type = 2,width = 100 ,height =100)
    private String avatar;
    @Excel(name = "手机")
    private String phone;
    private String password;
    private String salt;
    @Excel(name = "状态",replace = { "正常_1", "冻结_-1" })
    private int status;
    @Excel(name = "昵称")
    private String name;
    @Excel(name = "姓名")
    private String law_name;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "城市")
    private String city;
    private String sign;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",format = "yyyy-MM-dd",width = 20,height = 20)
    private Date crea_date;
    private String guru_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaw_name() {
        return law_name;
    }

    public void setLaw_name(String law_name) {
        this.law_name = law_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCrea_date() {
        return crea_date;
    }

    public void setCrea_date(Date crea_date) {
        this.crea_date = crea_date;
    }

    public String getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(String guru_id) {
        this.guru_id = guru_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", law_name='" + law_name + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", crea_date='" + crea_date + '\'' +
                ", guru_id='" + guru_id + '\'' +
                '}';
    }
}
