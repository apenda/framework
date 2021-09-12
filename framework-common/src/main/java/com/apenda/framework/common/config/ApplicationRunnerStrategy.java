package com.apenda.framework.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import com.apenda.framework.common.sensitive.GlobalSensitiveStrategy;

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
        GlobalSensitiveStrategy.getInstance().init();
        String sensitive = GlobalSensitiveStrategy.getInstance().sensitive("idCard", "440182198012163035");
        log.info("idCard | {}", sensitive);
    }

}
