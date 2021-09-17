package com.apenda.framework.common.config;

import com.apenda.framework.common.sensitive.GlobalSensitiveStrategy;
import com.apenda.framework.common.sensitive.SensitiveStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author rui.zhou
 * @date 2021年09月11日 20:27
 */
@Configuration
public class SensitiveStrategyConfig {

    @Resource
    private GlobalSensitiveStrategy globalSensitiveStrategy;

    /**
     * 自定义脱敏策略
     */
    @Bean
    public SensitiveStrategy sensitiveStrategy() {
        return globalSensitiveStrategy
                .addStrategy("idCard", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard2", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard3", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard4", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard5", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard6", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard7", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard8", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard9", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard10", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("idCard11", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("自定义脱敏策略", s -> "******" + s);
    }

}
