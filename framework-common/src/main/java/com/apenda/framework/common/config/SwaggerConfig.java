package com.apenda.framework.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author rui.zhou
 * @date 2021/06/1 16:50
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.apenda.framework"))
                //.apis(RequestHandlerSelectors.any())
                .paths(swaggerShow == true ? PathSelectors.any() : PathSelectors.none())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("framework")
                        .description("基于springboot+mybatis-plus+swagger2开发的一套快速开发框架")
                        .version("1.0.0")
                        .contact(new Contact("apenda", "http://10.142.146.37:8081/rule-engine/ibss-rule-core", "apenda@yeah.net"))
                        .build());
    }
}
