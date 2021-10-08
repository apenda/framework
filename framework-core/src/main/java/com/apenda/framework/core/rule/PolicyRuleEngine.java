package com.apenda.framework.core.rule;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.core.service.RuleService;
import com.apenda.framework.web.dto.RuleDTO;
import com.apenda.framework.web.dto.RuleResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/09/30 23:53
 **/
@Slf4j
@Component
public class PolicyRuleEngine extends AbstractRuleEngine<PolicyRule, Result>{

    @Resource
    private RuleService ruleService;

    @Override
    @PostConstruct
    public void init() {
        ResponseData<RuleResponseDTO> ruleResponseData = ruleService.queryAllRule();
        if (ruleResponseData.isSuccess()) {
            RuleResponseDTO data = ruleResponseData.getData();
            List<RuleDTO> ruleList = data.getRuleList();
            ruleList.stream().map(ruleDTO -> {
                PolicyRule policyRule = new PolicyRule();
                policyRule.setTest("test");
                policyRule.setId(ruleDTO.getId());
                policyRule.setRuleName(ruleDTO.getRuleName());
                policyRule.setGroup(ruleDTO.getRuleGroup());
                policyRule.setExpression(ruleDTO.getExpression());
                policyRule.setCommonRule(ruleDTO.getCommonRule());
                return policyRule;
            }).forEach(this::addRule);
        }
        groupRuleMap.forEach((key, value) -> value.stream().map(PolicyRule.class::cast).forEach(policyRule -> {
            log.info("group | {}", policyRule.getGroup());
            log.info("name | {}", policyRule.getRuleName());
            log.info("expression | {}", policyRule.getExpression());
            log.info("commonRule | {}", policyRule.getCommonRule());
        }));
    }

    /**
     * 添加保全规则
     *
     * @param policyRule 规则实体
     */
    public void addPolicyRule(PolicyRule policyRule){
        this.addRule(policyRule);
    }

}
