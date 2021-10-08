package com.apenda.framework.core.struct.mapper;

import com.apenda.framework.dao.entity.Rule;
import com.apenda.framework.dao.entity.RuleBusiness;
import com.apenda.framework.dao.entity.RuleFactor;
import com.apenda.framework.web.dto.RuleBusinessDTO;
import com.apenda.framework.web.dto.RuleDTO;
import com.apenda.framework.web.dto.RuleFactorDTO;
import com.apenda.framework.web.query.rule.BindRuleQuery;
import com.apenda.framework.web.query.rule.RuleFactorQuery;
import com.apenda.framework.web.query.rule.RuleQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author rui.zhou
 * @date 2021/10/04 15:40
 **/
@Mapper(componentModel = "spring")
public interface RuleStructMapper {

    RuleStructMapper INSTANCE = Mappers.getMapper(RuleStructMapper.class);

    RuleDTO ruleToRuleDTO(Rule rule);

    RuleBusinessDTO ruleBusinessToDTO(RuleBusiness ruleBusiness);

    RuleBusiness queryToRuleBusiness(BindRuleQuery bindRuleQuery);

    RuleFactorDTO ruleFactorToDTO(RuleFactor ruleFactor);

    RuleFactor queryToRuleFactor(RuleFactorQuery ruleFactorQuery);

    Rule queryToRule(RuleQuery ruleQuery);
}
