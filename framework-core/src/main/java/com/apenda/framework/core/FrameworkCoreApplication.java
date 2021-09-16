package com.apenda.framework.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

/**
 * @author rui.zhou
 **/
@MapperScan("com.apenda.framework.dao")
@ComponentScan("com.apenda.framework")
@NacosPropertySource(dataId = "framework", autoRefreshed = true)
@SpringBootApplication
public class FrameworkCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkCoreApplication.class, args);
    }

}
