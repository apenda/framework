package com.apenda.framework.common.annotation;

import com.apenda.framework.common.constant.SensitiveType;
import java.lang.annotation.*;

/**
 * 自定义脱敏注解
 *
 * @author rui.zhou
 * @date 2021年09月11日 21:52
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sensitive {

    /**
     * 脱敏字段枚举
     */
    String value() default SensitiveType.ID_CARD;

}
