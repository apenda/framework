package com.apenda.framework.web.query.rule;

import lombok.Data;

/**
 * @author rui.zhou
 * @date 2021/10/01 22:41
 **/
@Data
public class RuleFactorQuery {

    /**
     * 外键ID
     */
    private Long rid;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 因子
     */
    private String factor;

    /**
     * 查询因子方法
     */
    private String factorMethod;

}
