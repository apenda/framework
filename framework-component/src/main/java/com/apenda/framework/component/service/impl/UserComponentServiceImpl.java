package com.apenda.framework.component.service.impl;

import com.apenda.framework.component.service.UserComponentService;
import com.apenda.framework.dao.entity.User;
import com.apenda.framework.dao.mapper.UserMapper;
import com.apenda.framework.web.request.UserRequestDTO;
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
public class UserComponentServiceImpl extends ServiceImpl<UserMapper, User> implements UserComponentService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectOne(UserRequestDTO userRequest) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(User::getId, userRequest.getId()).or()
                .eq(User::getName, userRequest.getName()).or()
                .eq(User::getAge, userRequest.getAge()).or()
                .eq(User::getEmail, userRequest.getEmail());
        User user = userMapper.selectOne(lambdaQueryWrapper);
        return user;
    }
}
