package com.apenda.framework.common.sensitive;

import java.util.Map;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import java.util.function.Function;
import com.apenda.framework.common.constant.SensitiveType;

/**
 * 脱敏策略
 *
 * @author rui.zhou
 * @date 2021年09月11日 20:10
 */
@Slf4j
public class GlobalSensitiveStrategy implements SensitiveStrategy{

    private static Map<String, Function<String, String>> strategyMap = new HashMap<>(16);

    private static class SingletonHolder {
        private static final GlobalSensitiveStrategy INSTANCE = new GlobalSensitiveStrategy();
    }

    private GlobalSensitiveStrategy (){}

    public static GlobalSensitiveStrategy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化脱敏策略
     */
    public void init(){
        GlobalSensitiveStrategy
                .getInstance()
                .addNameStrategy()
                .addEmailStrategy()
                .addMobileStrategy()
                .addIdCardStrategy()
                .addAddressStrategy()
                .addBankCardStrategy();
        log.info("sensitive strategy init finish");
    }

    @Override
    public String sensitive(String name, String value) {
        Function<String, String> function = strategyMap.get(name);
        if (function == null) {
            log.warn("sensitive strategy {} not found", name);
            return value;
        }
        return function.apply(value);
    }

    @Override
    public GlobalSensitiveStrategy addStrategy(String name, Function<String, String> function) {
        strategyMap.put(name, function);
        return GlobalSensitiveStrategy.getInstance();
    }

    /**
     * 中文名脱敏
     */
    private GlobalSensitiveStrategy addNameStrategy(){
        return GlobalSensitiveStrategy.getInstance().addStrategy(SensitiveType.CHINESE_NAME,s -> "待实现脱敏策略");
    }

    /**
     * 身份证脱敏
     */
    private GlobalSensitiveStrategy addIdCardStrategy(){
        return GlobalSensitiveStrategy.getInstance()
                .addStrategy(SensitiveType.ID_CARD, s -> s.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*"));
    }

    /**
     * 邮箱脱敏
     */
    private GlobalSensitiveStrategy addEmailStrategy(){
        return GlobalSensitiveStrategy.getInstance()
                .addStrategy(SensitiveType.EMAIL, s -> s.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2"));
    }

    /**
     * 银行卡脱敏
     */
    private GlobalSensitiveStrategy addBankCardStrategy(){
        return GlobalSensitiveStrategy.getInstance()
                .addStrategy(SensitiveType.BANK_CARD, s -> "待实现脱敏策略");
    }

    /**
     * 电话号码脱敏
     */
    private GlobalSensitiveStrategy addMobileStrategy(){
        return GlobalSensitiveStrategy.getInstance()
                .addStrategy(SensitiveType.MOBILE, s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }

    /**
     * 地址脱敏
     */
    private GlobalSensitiveStrategy addAddressStrategy(){
        return GlobalSensitiveStrategy.getInstance()
                .addStrategy(SensitiveType.ADDRESS, s -> "待实现脱敏策略");
    }

}
