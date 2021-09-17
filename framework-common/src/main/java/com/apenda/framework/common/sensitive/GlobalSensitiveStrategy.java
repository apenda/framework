package com.apenda.framework.common.sensitive;

import com.apenda.framework.common.constant.SensitiveType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * 脱敏策略
 *
 * @author rui.zhou
 * @date 2021年09月11日 20:10
 */
@Slf4j
@Component
public class GlobalSensitiveStrategy implements SensitiveStrategy{

    private static final Map<String, UnaryOperator<String>> strategyMap = new HashMap<>(16);

    /**
     * 初始化脱敏策略
     */
    @PostConstruct
    public void init(){
                this.addNameStrategy().addEmailStrategy().addMobileStrategy().addIdCardStrategy().addAddressStrategy()
                    .addBankCardStrategy();
        log.info("sensitive strategy init finish");
    }

    @Override
    public String sensitive(String name, String value) {
        UnaryOperator<String> function = strategyMap.get(name);
        if (function == null) {
            log.warn("sensitive strategy {} not found", name);
            return value;
        }
        return function.apply(value);
    }

    @Override
    public GlobalSensitiveStrategy addStrategy(String name, UnaryOperator<String> function) {
        strategyMap.put(name, function);
        return this;
    }

    /**
     * 中文名脱敏
     */
    private GlobalSensitiveStrategy addNameStrategy(){
        return this.addStrategy(SensitiveType.CHINESE_NAME,s -> "待实现脱敏策略");
    }

    /**
     * 身份证脱敏
     */
    private GlobalSensitiveStrategy addIdCardStrategy(){
        return this.addStrategy(SensitiveType.ID_CARD, s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"));
    }

    /**
     * 邮箱脱敏
     */
    private GlobalSensitiveStrategy addEmailStrategy(){
        return this.addStrategy(SensitiveType.EMAIL, s -> s.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2"));
    }

    /**
     * 银行卡脱敏
     */
    private GlobalSensitiveStrategy addBankCardStrategy(){
        return this.addStrategy(SensitiveType.BANK_CARD, s -> "待实现脱敏策略");
    }

    /**
     * 电话号码脱敏
     */
    private GlobalSensitiveStrategy addMobileStrategy(){
        return this.addStrategy(SensitiveType.MOBILE, s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }

    /**
     * 地址脱敏
     */
    private GlobalSensitiveStrategy addAddressStrategy(){
        return this.addStrategy(SensitiveType.ADDRESS, s -> "待实现脱敏策略");
    }

}
