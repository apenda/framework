package com.apenda.framework.core.controller;


import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.core.service.UserService;
import com.apenda.framework.web.controller.IUserController;
import com.apenda.framework.web.request.PageBase;
import com.apenda.framework.web.request.UserAddRequest;
import com.apenda.framework.web.request.UserQueryRequest;
import com.apenda.framework.web.request.UserUpdateRequest;
import com.apenda.framework.web.response.UserResponse;
import com.apenda.framework.web.dto.UserResponseDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
public class UserController implements IUserController {

    @Resource
    private UserService userService;

    @Override
    public ResponseData<UserResponseDTO> queryUserById(Long id) {
        return userService.queryUserById(id);
    }

    @Override
    public ResponseData<UserResponseDTO> queryUser(@RequestBody @Validated UserQueryRequest userRequest) {
        return userService.queryUser(userRequest);
    }

    @Override
    public ResponseData<List<UserResponseDTO>> queryAllUser() {
        return userService.queryAllUser();
    }

    @Override
    public ResponseData<UserResponse> queryAllUser1() {
        return userService.queryAllUser1();
    }

    @Override
    public ResponseData<UserResponse> queryPage(@RequestBody PageBase pageBase) {
        return userService.queryPage(pageBase);
    }

    @Override
    public ResponseData<Boolean> addUser(@RequestBody UserAddRequest userAddRequest) {
        return userService.addUser(userAddRequest);
    }

    @Override
    public ResponseData<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userUpdateRequest);
    }

    @Override
    public ResponseData<Boolean> deleteUser(Long id) {
        return userService.deleteUser(id);
    }


}

