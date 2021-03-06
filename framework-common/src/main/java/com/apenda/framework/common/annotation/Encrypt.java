package com.apenda.framework.common.annotation;


import com.apenda.framework.common.constant.EncryptTypeEnum;
import java.lang.annotation.*;

/**
 * 自定义注解对参数进行脱敏、加解密
 *
 * @author rui.zhou
 * @date 2021/08/02 15:20
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

    /**
     * 判断脱敏还是加解密类型
     */
    EncryptTypeEnum type() default EncryptTypeEnum.DESENSITIZATION;
}
