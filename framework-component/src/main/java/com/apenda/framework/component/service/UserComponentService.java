package com.apenda.framework.component.service;

import com.apenda.framework.dao.entity.User;
import com.apenda.framework.web.request.UserRequestDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rui.zhou
 * @date 2021/06/01 15:07
 */
public interface UserComponentService extends IService<User> {

    /**
     * selectOne
     * @param userRequest
     * @return
     */
    User selectOne(UserRequestDTO userRequest);
}
