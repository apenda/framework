package com.apenda.framework.core.rule;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rui.zhou
 * @date 2021/09/30 23:21
 **/
public abstract class AbstractRuleEngine<T extends Rule, R extends Result> implements RuleEngine<T, R>{

    private static final Logger logger = LoggerFactory.getLogger(AbstractRuleEngine.class);

    /**
     * 根据分组存储规则
     */
    static final Map<String, Set<? super Rule>> groupRuleMap = new HashMap<>();

    /**
     * 根据分组存储公共规则
     */
    static final Map<String, Set<? super Rule>> groupCommonRuleMap = new HashMap<>();

    /**
     * 根据业务绑定规则
     */
    static final Map<Object, Set<Long>> bindRuleMap = new HashMap<>();

    /**
     * 根据业务查询规则
     *
     * @param obj 业务
     * @return Set<? extends Rule>
     */
    public Set<? super Rule> getRule(String group, Object obj){
        Set<Long> ruleIds = ObjectUtils.defaultIfNull(bindRuleMap.get(obj), new HashSet<>());
        Set<? super Rule> ruleSet = ObjectUtils.defaultIfNull(groupRuleMap.get(group), new HashSet<>());
        Set<? super Rule> commonRuleSet = ObjectUtils.defaultIfNull(groupCommonRuleMap.get(group), new HashSet<>());
        return ruleSet.stream()
                .map(o -> (T) o)
                .filter(type -> ruleIds.contains(type.getId()))
                .flatMap(type -> commonRuleSet.stream())
                .collect(Collectors.toSet());
    }

    @Override
    public void addRule(T type) {
        String group = StringUtils.defaultString(type.getGroup());
        Set<? super Rule> ruleSet = ObjectUtils.defaultIfNull(groupRuleMap.get(group), new HashSet<>());
        Set<? super Rule> commonRuleSet = ObjectUtils.defaultIfNull(groupCommonRuleMap.get(group), new HashSet<>());
        if (type.getCommonRule()) {
            commonRuleSet.add(type);
            groupCommonRuleMap.put(group, commonRuleSet);
        } else {
            ruleSet.add(type);
            groupRuleMap.put(group, ruleSet);
        }
    }

    @Override
    public void bindRule(String group, Object obj, Set<Long> ids) {
        Set<? super Rule> groupRuleSet = groupRuleMap.get(group);
        if (ObjectUtils.isEmpty(groupRuleSet)) {
            logger.warn("该规则组的没有规则, 请先添加规则或检查规则组是否正确");
            return;
        }

        ids.forEach(id -> {
            boolean contain = this.contain(id, groupRuleSet);
            if (!contain) {
                logger.warn("{} 规则组的没有 {} 的规则, 请先添加规则或检查规则组是否正确", group, id);
                return;
            }
            Set<Long> ruleIds = ObjectUtils.defaultIfNull(bindRuleMap.get(obj), new HashSet<>());
            ruleIds.add(id);
            bindRuleMap.put(obj, ruleIds);
        });
    }

    @Override
    public R execute(String group, Object obj, Object factor, Class<R> clazz) throws InstantiationException, IllegalAccessException {
        //1. 获取组所有的规则
        R result = clazz.newInstance();
        result.setCode(0);
        result.setMessage("规则执行通过");
        Set<? super Rule> groupRuleSet = groupRuleMap.get(group);
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }

        //2. 获取业务绑定的所有规则
        Set<Long> ruleIds = bindRuleMap.get(obj);
        groupRuleSet.stream()
                .map(o -> (T) o)
                .filter(type -> ruleIds.contains(type.getId()))
                .flatMap(type -> groupCommonRuleMap.get(type.getGroup()).stream())
                .map(o -> (T) o)
                .forEach(type -> {
                    if (!this.executeRule(type, factor)) {
                        result.setCode(1);
                        result.setMessage("规则被" + type.getRuleName() + "阻断");
                        List<Long> failIds = ObjectUtils.defaultIfNull(result.getFailIds(), new ArrayList<>());
                        failIds.add(type.getId());
                    }
                });
        return result;
    }

    /**
     * 执行指定一个规则
     *
     * @param type 规则
     * @param factor 规则因子
     * @return boolean
     */
    private boolean executeRule(T type, Object factor) {
        try {
            Field[] fields = factor.getClass().getDeclaredFields();
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
            String expression = type.getExpression();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                Object fieldValue = ObjectUtils.defaultIfNull(field.get(factor), null);
                expression = expression.replaceAll(name, fieldValue.toString());
            }
            return (boolean) scriptEngine.eval(expression);
        } catch (ScriptException e) {
            logger.error("规则执行失败, 请检查规则条件", e);
        } catch (IllegalAccessException e) {
            logger.error("规则执行失败", e);
        }
        return false;
    }

    /**
     * 规则是否存在集合
     *
     * @param id 规则ID
     * @param set 规则集合
     * @return boolean
     */
    private boolean contain(Long id, Set<? super Rule> set) {
        return set.stream().map(Rule.class::cast).anyMatch(rule -> id.equals(rule.getId()));
    }

}
