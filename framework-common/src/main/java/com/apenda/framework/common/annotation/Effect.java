package com.apenda.framework.common.annotation;

import java.lang.annotation.*;

/**
 * 与 @Sensitive 注解一起使用, 表示校验子对象
 *
 * @author rui.zhou
 * @date 2021年09月12日 16:41
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Effect {
}
