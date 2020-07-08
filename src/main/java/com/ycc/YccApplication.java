package com.ycc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author ccc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ycc.admin.mapper")
public class YccApplication {
    public static void main(String[] args) {
        SpringApplication.run(YccApplication.class,args);
    }
}
