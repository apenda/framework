package com.apenda.framework.common.log;

import lombok.Data;

/**
 * web请求日志对象
 *
 * @author rui.zhou
 * @date 2021/09/20 14:03
 **/
@Data
public class LogBody {

    /**
    * url
    */
    private String url;

    /**
    * 请求参数
    */
    private Object request;

    /**
    * 返回对象
    */
    private Object response;

}
