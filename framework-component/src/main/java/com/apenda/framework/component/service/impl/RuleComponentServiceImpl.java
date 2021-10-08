package com.apenda.framework.component.service.impl;

import com.apenda.framework.dao.entity.Rule;
import com.apenda.framework.dao.mapper.RuleMapper;
import com.apenda.framework.component.service.RuleComponentService;
import com.apenda.framework.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rui.zhou
 * @since 2021-10-01
 */
@Service
public class RuleComponentServiceImpl extends ServiceImpl<RuleMapper, Rule> implements RuleComponentService {

    @Resource
    private RuleMapper ruleMapper;

    @Override
    public long saveRule(Rule rule) {
        return ruleMapper.insert(rule);
    }
}
