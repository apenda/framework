package com.apenda.framework.core.controller;


import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.core.service.UserService;
import com.apenda.framework.web.controller.IUserController;
import com.apenda.framework.web.request.UserRequestDTO;
import com.apenda.framework.web.response.UserResponseDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rui.zhou
 * @date 2021/06/01 15:07
 */
@RestController
@RequestMapping("/framework/user")
public class UserController implements IUserController {

    @Resource
    private UserService userService;

    @Override
    public ResponseData<UserResponseDTO> queryUserById(Long id) {
        return userService.queryUserById(id);
    }

    @Override
    public ResponseData<List<UserResponseDTO>> queryAllUser() {
        return userService.queryAllUser();
    }

    @Override
    public ResponseData<UserResponseDTO> queryUser(@RequestBody @Validated UserRequestDTO userRequest) {
        return userService.queryUser(userRequest);
    }
}

