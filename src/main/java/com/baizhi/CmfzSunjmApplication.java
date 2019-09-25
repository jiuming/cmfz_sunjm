package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class CmfzSunjmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzSunjmApplication.class, args);
    }

}