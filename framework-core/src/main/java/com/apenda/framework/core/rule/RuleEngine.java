package com.apenda.framework.core.rule;

import java.util.Set;

/**
 * 规则引擎
 *
 * @author rui.zhou
 * @date 2021/09/30 23:18
 **/
public interface RuleEngine<T, R> {

    /**
     * 初始化
     */
    void init();

    /**
     * 添加规则
     *
     * @param type 泛型规则实体
     */
    void addRule(T type);

    /**
     * 绑定规则
     *
     * @param group 规则组
     * @param obj 指定业务
     * @param ids 规则名称合集
     */
    void bindRule(String group, Object obj, Set<Long> ids);

    /**
     * 根据分组执行指定规则
     *
     * @param group 规则组
     * @param obj 指定业务
     * @param factor 规则因子
     * @param clazz 泛型类型
     * @return R
     */
    R execute(String group, Object obj, Object factor, Class<R> clazz) throws InstantiationException, IllegalAccessException;
}
