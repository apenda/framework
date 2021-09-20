package com.apenda.framework.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rui.zhou
 **/
@SpringBootApplication
@MapperScan("com.apenda.framework.dao")
@ComponentScan("com.apenda.framework")
@ServletComponentScan(basePackages="com.apenda.framework.common.filter")
public class FrameworkCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkCoreApplication.class, args);
    }

}
