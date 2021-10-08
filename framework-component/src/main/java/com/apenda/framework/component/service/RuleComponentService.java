package com.apenda.framework.component.service;

import com.apenda.framework.dao.entity.Rule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rui.zhou
 * @since 2021-10-01
 */
public interface RuleComponentService extends IService<Rule> {

    /**
     * 添加规则
     * @param rule 规则实体
     * @return long
     */
    long saveRule(Rule rule);
}
