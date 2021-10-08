package com.apenda.framework.core.rule;

import lombok.Data;

/**
 * @author rui.zhou
 * @date 2021/09/30 23:25
 **/
@Data
public class Rule {

    /**
     * 规则 ID
     */
    private Long id;

    /**
    * 规则名称
    */
    private String ruleName;

    /**
    * 规则组
    */
    private String group;

    /**
     * 条件表达式
     */
    private String expression;

    /**
     * 是否公共规则 0--普通规则, 1--公共规则
     */
    private Boolean commonRule;

    public Rule() {
    }

    public Rule(String ruleName, String group) {
        this.ruleName = ruleName;
        this.group = group;
    }
}
