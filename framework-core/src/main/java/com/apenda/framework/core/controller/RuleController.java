package com.apenda.framework.core.controller;


import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.core.service.RuleService;
import com.apenda.framework.web.controller.IRuleController;
import com.apenda.framework.web.dto.BindRuleResponseDTO;
import com.apenda.framework.web.dto.RuleResponseDTO;
import com.apenda.framework.web.dto.UserResponseDTO;
import com.apenda.framework.web.query.rule.BindRuleQuery;
import com.apenda.framework.web.query.rule.ExpressionQuery;
import com.apenda.framework.web.query.rule.RuleQuery;
import com.apenda.framework.web.request.UserUpdateRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rui.zhou
 * @since 2021-10-01
 */
@RestController
public class RuleController implements IRuleController {
    
    @Resource
    private RuleService ruleService;

    @Override
    public ResponseData<Boolean> expressionTest(@RequestBody ExpressionQuery expressionQuery) {
        return ruleService.expressionTest(expressionQuery);
    }

    @Override
    public ResponseData<Boolean> addRule(@RequestBody RuleQuery ruleQuery) {
        return ruleService.addRule(ruleQuery);
    }

    @Override
    public ResponseData<Boolean> updateRule(@RequestBody RuleQuery ruleQuery) {
        return ruleService.updateRule(ruleQuery);
    }

    @Override
    public ResponseData<Boolean> deleteRule(Long id) {
        return ruleService.deleteRule(id);
    }

    @Override
    public ResponseData<RuleResponseDTO> queryAllRule() {
        return ruleService.queryAllRule();
    }

    @Override
    public ResponseData<Boolean> bindRule(@RequestBody BindRuleQuery bindRuleQuery) {
        return ruleService.bindRule(bindRuleQuery);
    }

    @Override
    public ResponseData<Boolean> updateBindRule(@RequestBody BindRuleQuery bindRuleQuery) {
        return ruleService.updateBindRule(bindRuleQuery);
    }

    @Override
    public ResponseData<Boolean> deleteBindRule(Long id) {
        return ruleService.deleteBindRule(id);
    }

    @Override
    public ResponseData<BindRuleResponseDTO> queryBindRule(String business) {
        return ruleService.queryBindRule(business);
    }

}

