package com.apenda.framework.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author rui.zhou
 * @date 2021/05/21 15:05
 */
@Slf4j
public class ResponseData<T> {

    /**
     * 错误或者成功代码
     */
    private int code;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 响应结果
     */
    private T data;

    /**
     * 总数
     */
    private Integer count;

    /**
     * 构造函数
     */
    public ResponseData() {
    }

    /**
     * 构造函数
     *
     * @param code    错误或者成功代码
     * @param message 错误描述
     */
    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param code    错误或者成功代码
     * @param message 错误描述
     * @param data    响应结果
     */
    public ResponseData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造函数
     */
    public ResponseData(MessageCode messageCode) {
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
    }

    /**
     * 构造函数
     */
    public ResponseData(MessageCode messageCode, T data) {
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return CommonMessageCode.SUCCESS.getCode() == this.code;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
