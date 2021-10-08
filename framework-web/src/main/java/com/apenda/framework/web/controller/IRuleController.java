package com.apenda.framework.web.controller;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.web.dto.BindRuleResponseDTO;
import com.apenda.framework.web.dto.RuleResponseDTO;
import com.apenda.framework.web.query.rule.BindRuleQuery;
import com.apenda.framework.web.query.rule.ExpressionQuery;
import com.apenda.framework.web.query.rule.RuleQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rui.zhou
 * @date 2021/10/01 22:09
 **/
@Api(tags = "规则引擎")
@RequestMapping("/rule")
public interface IRuleController {

    /**
     * 测试表达式是否正确
     *
     * @param expressionQuery 入参
     * @return Boolean
     */
    @ApiOperation("添加规则")
    @PostMapping(value = "/expressionTest")
    ResponseData<Boolean> expressionTest(ExpressionQuery expressionQuery);

    /**
     * 添加规则
     *
     * @param ruleQuery 添加规则入参
     * @return Boolean
     */
    @ApiOperation("添加规则")
    @PostMapping(value = "/addRule")
    ResponseData<Boolean> addRule(RuleQuery ruleQuery);

    /**
     * 修改规则
     *
     * @param ruleQuery 请求实体
     * @return Boolean
     */
    @ApiOperation("修改规则")
    @PostMapping(value = "/updateRule")
    ResponseData<Boolean> updateRule(RuleQuery ruleQuery);

    /**
     * 删除规则
     *
     * @param id 唯一标识
     * @return Boolean
     */
    @ApiOperation("删除规则")
    @GetMapping(value = "/deleteRule")
    ResponseData<Boolean> deleteRule(Long id);

    /**
     * 查询所有规则
     * @return UserResponseDTO
     */
    @ApiOperation("查询所有规则")
    @GetMapping(value = "/queryAllRule")
    ResponseData<RuleResponseDTO> queryAllRule();

    /**
     * 业务绑定规则
     *
     * @param bindRuleQuery 绑定规则入参
     * @return Boolean
     */
    @ApiOperation("业务绑定规则")
    @PostMapping(value = "/bindRule")
    ResponseData<Boolean> bindRule(BindRuleQuery bindRuleQuery);

    /**
     * 修改绑定规则
     *
     * @param bindRuleQuery 请求实体
     * @return Boolean
     */
    @ApiOperation("修改绑定规则")
    @PostMapping(value = "/updateBindRule")
    ResponseData<Boolean> updateBindRule(BindRuleQuery bindRuleQuery);

    /**
     * 删除绑定规则
     *
     * @param id 唯一标识
     * @return Boolean
     */
    @ApiOperation("删除绑定规则")
    @GetMapping(value = "/deleteBindRule")
    ResponseData<Boolean> deleteBindRule(Long id);

    /**
     * 查询绑定规则
     * @return UserResponseDTO
     */
    @ApiOperation("查询绑定规则")
    @GetMapping(value = "/queryBindRule")
    ResponseData<BindRuleResponseDTO> queryBindRule(String business);

}
