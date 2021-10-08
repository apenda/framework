package com.apenda.framework.web.query.rule;

import lombok.Data;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/10/01 22:14
 **/
@Data
public class RuleQuery {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 规则名
     */
    private String ruleName;

    /**
     * 规则组
     */
    private String ruleGroup;

    /**
     * 是否公共规则
     */
    private Boolean commonRule;

    /**
     * 执行表达式
     */
    private String expression;

    /**
     * 表达式语言
     */
    private String expressionLanguage;

    /**
    * 因子集合
    */
    private List<RuleFactorQuery> factorList;

}
