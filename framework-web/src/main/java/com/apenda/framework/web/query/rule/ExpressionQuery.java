package com.apenda.framework.web.query.rule;

import lombok.Data;

import java.util.Map;

/**
 * @author rui.zhou
 * @date 2021/10/04 18:35
 **/
@Data
public class ExpressionQuery {
    
    /**
    * 表达式
    */
    private String expression;

    /**
     * 表达式使用语言
     */
    private String expressionLanguage;

    /**
     * 表达式因子值
     */
    private Map<String, Object> factorMap;

}
