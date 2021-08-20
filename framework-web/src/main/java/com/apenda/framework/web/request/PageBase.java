package com.apenda.framework.web.request;

import lombok.Data;

/**
 * 分页基础实体类
 *
 * @author rui.zhou
 * @date 2021/08/13 16:28
 **/
@Data
public class PageBase {

    /**
    * 当前页
    */
    private int current;

    /**
    * 页数
    */
    private int size;

}
