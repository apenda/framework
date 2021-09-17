package com.apenda.framework.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rui.zhou
 **/
@MapperScan("com.apenda.framework.dao")
@ComponentScan("com.apenda.framework")
@SpringBootApplication
public class FrameworkCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkCoreApplication.class, args);
    }

}
