package com.apenda.framework.core.service.impl;

import com.apenda.framework.core.struct.mapper.RuleStructMapper;

import java.lang.reflect.Field;
import java.util.*;

import com.apenda.framework.component.service.RuleBusinessComponentService;
import com.apenda.framework.dao.entity.RuleBusiness;
import com.apenda.framework.web.dto.*;
import com.apenda.framework.web.query.rule.BindRuleQuery;
import com.apenda.framework.web.query.rule.ExpressionQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.component.service.RuleComponentService;
import com.apenda.framework.component.service.RuleFactorComponentService;
import com.apenda.framework.core.service.RuleService;
import com.apenda.framework.dao.entity.Rule;
import com.apenda.framework.dao.entity.RuleFactor;
import com.apenda.framework.web.query.rule.RuleFactorQuery;
import com.apenda.framework.web.query.rule.RuleQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.stream.Collectors;

/**
 * @author rui.zhou
 * @date 2021/10/01 22:47
 **/
@Slf4j
@Service
public class RuleServiceImpl implements RuleService {

    @Resource
    private RuleComponentService ruleComponentService;

    @Resource
    private RuleFactorComponentService ruleFactorComponentService;

    @Resource
    private RuleBusinessComponentService ruleBusinessComponentService;

    @Override
    public ResponseData<Boolean> addRule(RuleQuery ruleQuery) {
        QueryWrapper<Rule> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("rule_name", ruleQuery.getRuleName())
                .eq("rule_group", ruleQuery.getRuleGroup());
        Rule one = ruleComponentService.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(one)) {
            log.warn("新增规则失败 Group={} ruleName={} 的规则已经存在", ruleQuery.getRuleGroup(), ruleQuery.getRuleName());
            return new ResponseData<>(false);
        }
        ruleComponentService.saveRule(RuleStructMapper.INSTANCE.queryToRule(ruleQuery));
        Rule rule = ruleComponentService.getOne(queryWrapper);
        List<RuleFactorQuery> factorList = ruleQuery.getFactorList();
        factorList.stream().map(ruleFactorQuery -> {
            RuleFactor ruleFactor = RuleStructMapper.INSTANCE.queryToRuleFactor(ruleFactorQuery);
            ruleFactor.setRid(rule.getId());
            return ruleFactor;
        }).forEach(ruleFactor -> ruleFactorComponentService.save(ruleFactor));
        return new ResponseData<>(true);
    }

    @Override
    public ResponseData<Boolean> updateRule(RuleQuery ruleQuery) {
        ruleComponentService.updateById(RuleStructMapper.INSTANCE.queryToRule(ruleQuery));
        List<RuleFactorQuery> factorList = ruleQuery.getFactorList();
        factorList.stream().map(RuleStructMapper.INSTANCE::queryToRuleFactor).forEach(ruleFactor -> {
            LambdaUpdateWrapper<RuleFactor> lambdaQueryWrapper = new LambdaUpdateWrapper<>();
            lambdaQueryWrapper.eq(RuleFactor::getRid, ruleFactor.getRid());
            ruleFactorComponentService.update(ruleFactor,lambdaQueryWrapper);
        });
        return new ResponseData<>(true);
    }

    @Override
    public ResponseData<Boolean> deleteRule(Long id) {
        LambdaQueryWrapper<RuleFactor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RuleFactor::getRid,id);
        ruleFactorComponentService.remove(lambdaQueryWrapper);
        ruleComponentService.removeById(id);
        return new ResponseData<>(true);
    }

    @Override
    public ResponseData<RuleResponseDTO> queryAllRule() {
        RuleResponseDTO ruleResponseDTO = new RuleResponseDTO();
        List<RuleDTO> collect = ruleComponentService.list().stream().map(rule -> {
            RuleDTO ruleDTO = RuleStructMapper.INSTANCE.ruleToRuleDTO(rule);
            List<RuleFactorDTO> ruleFactorList = ruleFactorComponentService.list().stream()
                    .filter(ruleFactor -> rule.getId().equals(ruleFactor.getRid()))
                    .map(RuleStructMapper.INSTANCE::ruleFactorToDTO).collect(Collectors.toList());
            ruleDTO.setRuleFactorList(ruleFactorList);
            return ruleDTO;
        }).collect(Collectors.toList());
        ruleResponseDTO.setRuleList(collect);
        return new ResponseData<>(ruleResponseDTO);
    }

    @Override
    public ResponseData<Boolean> bindRule(BindRuleQuery bindRuleQuery) {
        List<RuleBusiness> ruleBusinessList = new ArrayList<>();
        Set<Long> rids = bindRuleQuery.getRids();
        rids.forEach(rid -> {
            Rule rule = this.getRule(bindRuleQuery, rid);
            if (!ObjectUtils.isEmpty(rule)) {
                RuleBusiness ruleBusiness = RuleStructMapper.INSTANCE.queryToRuleBusiness(bindRuleQuery);
                ruleBusiness.setRid(rid);
                ruleBusinessList.add(ruleBusiness);
            } else {
                log.warn("绑定失败 Group={} ID={} 的规则不存在", bindRuleQuery.getRuleGroup(), rid);
            }
        });
        return new ResponseData<>(ruleBusinessComponentService.saveBatch(ruleBusinessList));
    }

    @Override
    public ResponseData<Boolean> updateBindRule(BindRuleQuery bindRuleQuery) {
        //1. 获取之前绑定的规则
        LambdaQueryWrapper<RuleBusiness> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(RuleBusiness::getRuleGroup, bindRuleQuery.getRuleGroup())
                .eq(RuleBusiness::getBusiness, bindRuleQuery.getBusiness());
        List<RuleBusiness> list = ruleBusinessComponentService.list(lambdaQueryWrapper);
        Set<Long> oldSet = list.stream().map(RuleBusiness::getRid).collect(Collectors.toSet());

        //2. 得到新旧规则交集 5
        Set<Long> rids = bindRuleQuery.getRids();
        Set<Long> retainSet = new HashSet<>(oldSet);
        retainSet.retainAll(rids);
        if (retainSet.isEmpty()) {
            this.removeRuleBusiness(oldSet);
            this.bindRule(bindRuleQuery, rids);
            return new ResponseData<>(true);
        }

        //3. 得到需要删除的规则并删除该规则 5 12
        oldSet.removeAll(retainSet);
        this.removeRuleBusiness(oldSet);

        //4. 绑定规则
        rids.removeAll(retainSet);
        this.bindRule(bindRuleQuery, rids);

        //5. 更新绑定规则
        retainSet.forEach(rid ->{
            RuleBusiness ruleBusiness = RuleStructMapper.INSTANCE.queryToRuleBusiness(bindRuleQuery);
            ruleBusiness.setRid(rid);
            LambdaUpdateWrapper<RuleBusiness> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper
                    .eq(RuleBusiness::getRuleGroup, bindRuleQuery.getRuleGroup())
                    .eq(RuleBusiness::getBusiness, bindRuleQuery.getBusiness());
            ruleBusinessComponentService.update(ruleBusiness, lambdaUpdateWrapper);
        });
        return new ResponseData<>(true);
    }

    /**
     * 删除业务规则
     *
     * @param oldSet 规则 ID
     */
    private void removeRuleBusiness(Set<Long> oldSet) {
        oldSet.forEach(aLong -> {
            LambdaQueryWrapper<RuleBusiness> ruleBusinessLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ruleBusinessLambdaQueryWrapper.eq(RuleBusiness::getRid, aLong);
            ruleBusinessComponentService.remove(ruleBusinessLambdaQueryWrapper);
        });
    }

    /**
     * 绑定规则
     *
     * @param bindRuleQuery 入参
     * @param rids 规则集合
     */
    private void bindRule(BindRuleQuery bindRuleQuery, Set<Long> rids) {
        rids.forEach(rid -> {
            Rule rule = this.getRule(bindRuleQuery, rid);
            if (!ObjectUtils.isEmpty(rule)) {
                RuleBusiness ruleBusiness = RuleStructMapper.INSTANCE.queryToRuleBusiness(bindRuleQuery);
                ruleBusiness.setRid(rid);
                ruleBusinessComponentService.save(ruleBusiness);
            } else {
                log.warn("绑定失败 Group={} ID={} 的规则不存在", bindRuleQuery.getRuleGroup(), rid);
            }
        });
    }

    /**
     * 根据分组 ID 查询规则
     * @param bindRuleQuery 入参
     * @param rid 规则 ID
     * @return Rule
     */
    private Rule getRule(BindRuleQuery bindRuleQuery, Long rid) {
        LambdaQueryWrapper<Rule> ruleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ruleLambdaQueryWrapper
                .eq(Rule::getId, rid)
                .eq(Rule::getRuleGroup, bindRuleQuery.getRuleGroup());
        return ruleComponentService.getOne(ruleLambdaQueryWrapper);
    }

    @Override
    public ResponseData<Boolean> deleteBindRule(Long id) {
        LambdaQueryWrapper<RuleBusiness> ruleBusinessLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ruleBusinessLambdaQueryWrapper.eq(RuleBusiness::getRid, id);
        return new ResponseData<>(ruleBusinessComponentService.remove(ruleBusinessLambdaQueryWrapper));
    }

    @Override
    public ResponseData<BindRuleResponseDTO> queryBindRule(String business) {
        LambdaQueryWrapper<RuleBusiness> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RuleBusiness::getBusiness, business);
        List<RuleBusiness> list = ruleBusinessComponentService.list(lambdaQueryWrapper);
        List<RuleBusinessDTO> ruleBusinessList = list.stream().map(ruleBusiness -> {
            LambdaQueryWrapper<Rule> ruleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ruleLambdaQueryWrapper.eq(Rule::getId, ruleBusiness.getRid());
            List<RuleDTO> collect = ruleComponentService.list(ruleLambdaQueryWrapper).stream()
                    .map(RuleStructMapper.INSTANCE::ruleToRuleDTO)
                    .collect(Collectors.toList());
            RuleBusinessDTO ruleBusinessDTO = RuleStructMapper.INSTANCE.ruleBusinessToDTO(ruleBusiness);
            ruleBusinessDTO.setRuleList(collect);
            return ruleBusinessDTO;
        }).collect(Collectors.toList());
        BindRuleResponseDTO bindRuleResponseDTO = new BindRuleResponseDTO();
        bindRuleResponseDTO.setRuleBusinessList(ruleBusinessList);
        return new ResponseData<>(bindRuleResponseDTO);
    }

    @Override
    public ResponseData<Boolean> expressionTest(ExpressionQuery expressionQuery) {
        try {
            String script = StringUtils.defaultString(expressionQuery.getExpressionLanguage(), "javascript");
            Map<String, Object> factorMap = expressionQuery.getFactorMap();
            ScriptEngineManager engineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = engineManager.getEngineByName(script);
            String expression = expressionQuery.getExpression();
            for (Map.Entry<String, Object> entry : factorMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Object fieldValue = ObjectUtils.defaultIfNull(value, "");
                expression = expression.replaceAll(key, fieldValue.toString());
            }
            return new ResponseData<>((boolean)scriptEngine.eval(expression));
        } catch (ScriptException e) {
            log.error("规则执行失败, 请检查规则条件", e);
        }
        return new ResponseData<>(false);
    }

}
