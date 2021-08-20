package com.apenda.framework.core.handler;

import com.apenda.framework.common.annotation.Encrypt;
import com.apenda.framework.common.constant.EncryptTypeEnum;
import com.apenda.framework.common.constant.FieldEnum;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.common.util.DesensitizationUtil;
import com.apenda.framework.common.util.RsaUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/06/01 16:05
 */
@Slf4j
@RestControllerAdvice
public class HttpRespAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    ObjectMapper objectMapper;


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseData){
            try{
                //1. 脱敏或加密
                Object data = ((ResponseData<?>) body).getData();
                if (data != null) {
                    Field[] fields = data.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        // 获取具有泛型的类型
                        Type genericType = field.getGenericType();
                        if(genericType instanceof ParameterizedType){
                            ParameterizedType parameterizedType = (ParameterizedType) genericType;
                            Class genericClazz = (Class)parameterizedType.getActualTypeArguments()[0];
                            Field[] declaredFields = genericClazz.getDeclaredFields();
                            field.setAccessible(true);
                            Object o = field.get(data);
                            if (o instanceof List){
                                for (Object obj : (List) o) {
                                    if (obj == null) {
                                        continue;
                                    }
                                    for (Field declaredField : declaredFields) {
                                        this.encrypt(obj, declaredField);
                                    }
                                }
                            }
                        } else {
                            this.encrypt(data, field);
                        }
                    }
                }

                //2. 打印响应日志
                log.info("response data | {}", objectMapper.writeValueAsString(body));
            }catch(Exception e){
                log.warn("response data json process fail",e);
            }
        }
        return body;
    }

    /**
     * 脱敏
     *
     * @param data 脱敏数据对象
     * @param field 脱敏字段
     * @throws IllegalAccessException 非法访问异常
     */
    private void encrypt(Object data, Field field) throws Exception {
        Encrypt encrypt = field.getAnnotation(Encrypt.class);
        if (encrypt != null) {
            FieldEnum fieldEnum = encrypt.field();
            field.setAccessible(true);
            Object obj = field.get(data);
            if (obj != null) {
                // 脱敏
                EncryptTypeEnum type = encrypt.type();
                if (EncryptTypeEnum.DESENSITIZATION.equals(type)) {
                    field.set(data, DesensitizationUtil.encrypt(fieldEnum, obj.toString()));
                }

                // 加密
                if (EncryptTypeEnum.ENCRYPT.equals(type)) {
                    String value = RsaUtil.encrypt(obj.toString());
                    field.set(data, value);
                }
            }
        }
    }
}
