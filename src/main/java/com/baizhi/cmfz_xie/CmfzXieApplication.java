package com.baizhi.cmfz_xie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@tk.mybatis.spring.annotation.MapperScan("com.baizhi.cmfz_xie.dao")
@SpringBootApplication
@MapperScan("com.baizhi.cmfz_xie.dao")

public class CmfzXieApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzXieApplication.class, args);
    }

}
