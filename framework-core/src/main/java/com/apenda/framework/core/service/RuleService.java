package com.apenda.framework.core.service;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.web.dto.BindRuleResponseDTO;
import com.apenda.framework.web.dto.RuleResponseDTO;
import com.apenda.framework.web.query.rule.BindRuleQuery;
import com.apenda.framework.web.query.rule.ExpressionQuery;
import com.apenda.framework.web.query.rule.RuleQuery;

/**
 * @author rui.zhou
 * @date 2021/10/01 22:47
 **/
public interface RuleService {

    /**
     * 添加规则
     *
     * @param ruleQuery 入参
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> addRule(RuleQuery ruleQuery);

    /**
     * g更新规则
     *
     * @param ruleQuery 入参
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> updateRule(RuleQuery ruleQuery);

    /**
     * 删除规则
     *
     * @param id 规则唯一标识
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> deleteRule(Long id);

    /**
     * 查询所有规则
     *
     * @return ResponseData<RuleResponseDTO>
     */
    ResponseData<RuleResponseDTO> queryAllRule();

    /**
     * 业务绑定规则
     *
     * @param bindRuleQuery 绑定入参
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> bindRule(BindRuleQuery bindRuleQuery);

    /**
     * 修改绑定规则
     *
     * @param bindRuleQuery 绑定入参
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> updateBindRule(BindRuleQuery bindRuleQuery);

    /**
     * 删除绑定规则
     *
     * @param id id
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> deleteBindRule(Long id);

    /**
     * 查询绑定规则
     *
     * @param business 具体业务
     * @return ResponseData<BindRuleResponseDTO>
     */
    ResponseData<BindRuleResponseDTO> queryBindRule(String business);

    /**
     * 测试表达式是否正确
     *
     * @param expressionQuery 入参
     * @return ResponseData<Boolean>
     */
    ResponseData<Boolean> expressionTest(ExpressionQuery expressionQuery);

}
