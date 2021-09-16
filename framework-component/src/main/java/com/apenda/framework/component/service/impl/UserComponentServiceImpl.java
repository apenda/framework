package com.apenda.framework.component.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.apenda.framework.component.service.UserComponentService;
import com.apenda.framework.dao.entity.User;
import com.apenda.framework.dao.mapper.UserMapper;
import com.apenda.framework.web.request.UserQueryRequest;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rui.zhou
 * @date 2021/06/01 15:07
 */
@Service
@DS("mysql_2")
public class UserComponentServiceImpl extends ServiceImpl<UserMapper, User> implements UserComponentService {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @Resource
    private UserMapper userMapper;

    @Override
    @DS("mysql_1")
    public User selectOne(UserQueryRequest userRequest) {
        System.out.printf("useLocalCache = " + useLocalCache);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(User::getId, userRequest.getId()).or()
                .eq(User::getName, userRequest.getName()).or()
                .eq(User::getAge, userRequest.getAge()).or()
                .eq(User::getEmail, userRequest.getEmail())
                .last("limit 1");
        return userMapper.selectOne(lambdaQueryWrapper);
    }
}

