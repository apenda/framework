package com.apenda.framework.common.sensitive;

import java.util.function.UnaryOperator;

/**
 * 字段脱敏策略
 *
 * @author rui.zhou
 * @date 2021年09月11日 19:47
 */
public interface SensitiveStrategy {

    /**
     * 脱敏
     *
     * @param name 策略
     * @param value 待脱敏值
     * @return String
     */
    String sensitive(String name, String value);

    /**
     * 添加策略
     *
     * @param name 策略
     * @param function 策略实现
     * @return SensitiveStrategy
     */
    SensitiveStrategy addStrategy(String name, UnaryOperator<String> function);

}
