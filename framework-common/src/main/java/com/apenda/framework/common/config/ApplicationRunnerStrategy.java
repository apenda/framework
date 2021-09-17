package com.apenda.framework.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化
 *
 * @author rui.zhou
 * @date 2021年09月11日 21:22
 */
@Slf4j
@Component
public class ApplicationRunnerStrategy implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
    }

}
