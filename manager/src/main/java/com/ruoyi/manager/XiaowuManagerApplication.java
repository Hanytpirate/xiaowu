package com.ruoyi.manager;

import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.ruoyi.manager.mapper")
@SpringBootApplication
@EnableRyFeignClients
public class XiaowuManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaowuManagerApplication.class, args);
    }
}
