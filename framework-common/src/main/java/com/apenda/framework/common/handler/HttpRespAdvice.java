package com.apenda.framework.common.handler;

import com.apenda.framework.common.annotation.Effect;
import com.apenda.framework.common.annotation.Sensitive;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.common.sensitive.GlobalSensitiveStrategy;
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
import java.util.*;

/**
 * @author rui.zhou
 * @date 2021/06/01 16:05
 */
@Slf4j
@RestControllerAdvice
public class HttpRespAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    private GlobalSensitiveStrategy globalSensitiveStrategy;


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseData){
            try{
                //1. 脱敏
                Object data = ((ResponseData<?>) body).getData();
                if (data != null) {
                    Class<?> aClass = data.getClass();
                    Type genericSuperclass = aClass.getGenericSuperclass();
                    // 是否泛型 ResponseData<List<?>> 中的 List<?>
                    if (genericSuperclass instanceof ParameterizedType){
                        // data原始类型是否集合
                        if (AbstractCollection.class.isAssignableFrom(aClass)) {
                            // 不为 map
                            if (!AbstractMap.class.isAssignableFrom(aClass)) {
                                for(Object object : (Collection)data){
                                    sensitiveObject(object);
                                }
                            }
                        }
                    } else {
                        sensitiveObject(data);
                    }
                }

                //2. 打印响应日志
                //log.info("response | {}", objectMapper.writeValueAsString(body));
            }catch(Exception e){
                log.warn("response data json process fail",e);
            }
        }
        return body;
    }

    /**
     * 递归对象对有注解的字段脱敏
     *
     * @param object 需脱敏对象
     * @throws Exception 异常
     */
    private void sensitiveObject(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldObject = field.get(object);
            Type genericType = field.getGenericType();
            // 字段为泛型
            if(genericType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Class genericClazz = (Class)parameterizedType.getActualTypeArguments()[0];
                Field[] declaredFields = genericClazz.getDeclaredFields();
                if (fieldObject instanceof List || fieldObject instanceof Set){
                    // 如果想让泛型加上注解才能生效 这里判断就好
                    for (Object obj : (Collection) fieldObject) {
                        if (obj == null) {
                            continue;
                        }
                        for (Field declaredField : declaredFields) {
                            this.sensitive(obj, declaredField);
                        }
                    }
                }
            // 字段非泛型
            } else {
                this.sensitive(object, field);
            }
        }
    }

    /**
     * 针对对象中字段脱敏
     *
     * @param data 脱敏数据对象
     * @param field 脱敏字段
     * @throws IllegalAccessException 非法访问异常
     */
    private void sensitive(Object data, Field field) throws Exception {
        // 注解为 Sensitive 对当前字段脱敏
        Sensitive sensitive = field.getAnnotation(Sensitive.class);
        field.setAccessible(true);
        Object obj = field.get(data);
        if (obj == null) {
            return;
        }
        if (sensitive != null) {
            field.set(data, globalSensitiveStrategy.sensitive(sensitive.value(), obj.toString()));
        }

        // 注解为 Valid 对当前对象里面的字段递归脱敏
        Effect effect = field.getAnnotation(Effect.class);
        if (effect != null) {
            this.sensitiveObject(obj);
        }
    }

}
