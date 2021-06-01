package com.apenda.framework.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rui.zhou
 **/
@MapperScan("com.apenda.framework.dao")
//@ComponentScan("com.cignacmb.ibss")
@SpringBootApplication
public class FrameworkCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkCoreApplication.class, args);
    }

}
