package com.apenda.framework.web.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/10/02 01:31
 **/
@Data
public class RuleDTO {

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
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
    * 规则因子集合
    */
    private List<RuleFactorDTO> ruleFactorList;

}
