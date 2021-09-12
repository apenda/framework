package com.apenda.framework.common.config;

import com.apenda.framework.common.sensitive.GlobalSensitiveStrategy;
import com.apenda.framework.common.sensitive.SensitiveStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rui.zhou
 * @date 2021年09月11日 20:27
 */
@Configuration
public class SensitiveStrategyConfig {

    /**
     * 自定义脱敏策略
     */
    @Bean
    public SensitiveStrategy sensitiveStrategy() {
        return GlobalSensitiveStrategy.getInstance()
                .addStrategy("idCard", s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"))
                .addStrategy("自定义脱敏策略", s -> "******" + s);
    }

}
