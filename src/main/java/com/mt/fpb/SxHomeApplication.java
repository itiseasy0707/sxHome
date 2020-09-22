package com.mt.fpb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author
 * @date 2020-02-03 14:12
 */
@SpringBootApplication
@MapperScan("com.mt.fpb.mapper")

public class SxHomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SxHomeApplication.class, args);
    }
}
