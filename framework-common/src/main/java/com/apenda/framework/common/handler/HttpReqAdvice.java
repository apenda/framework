package com.apenda.framework.common.handler;

import com.apenda.framework.common.annotation.Encrypt;
import com.apenda.framework.common.constant.EncryptTypeEnum;
import com.apenda.framework.common.util.RsaUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 *
 * @author rui.zhou
 * @date 2021/06/01 16:05
 */
@Slf4j
@RestControllerAdvice
public class HttpReqAdvice implements RequestBodyAdvice {

    @Resource
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try{
            Field[] fields = parameter.getParameterType().getDeclaredFields();
            for (Field field : fields) {
                //1. 字段没有Encrypt注解则跳过
                Encrypt encrypt = field.getAnnotation(Encrypt.class);
                if (encrypt == null){
                    continue;
                }

                //2. 字段有Encrypt注解并且type为DECRYPT则解密
                field.setAccessible(true);
                Object obj = field.get(body);
                if (obj != null) {
                    EncryptTypeEnum type = encrypt.type();
                    if (EncryptTypeEnum.DECRYPT.equals(type)) {
                        String value = RsaUtil.decrypt(obj.toString());
                        field.set(body, value);
                    }
                }
            }
            log.info("request data | {}",objectMapper.writeValueAsString(body));
        }catch(Exception e){
            log.warn("request params json process fail",e);
        }
        return body;
    }
}
