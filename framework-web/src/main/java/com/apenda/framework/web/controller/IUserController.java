package com.apenda.framework.web.controller;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.web.request.PageBase;
import com.apenda.framework.web.request.UserQueryRequest;
import com.apenda.framework.web.request.UserAddRequest;
import com.apenda.framework.web.request.UserUpdateRequest;
import com.apenda.framework.web.response.UserResponse;
import com.apenda.framework.web.dto.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/05/07 17:12
 **/
@Api(tags = "用户管理DEMO")
@RequestMapping("/user")
public interface IUserController {

    /**
     * 通过ID查询用户
     * @param id 唯一标识
     * @return UserResponseDTO
     */
    @ApiOperation("查询用户通过ID")
    @RequestMapping(value = "/queryUserById", method = RequestMethod.GET)
    ResponseData<UserResponseDTO> queryUserById(Long id);

    /**
     * 通过任意参数查询用户
     * @param userQueryRequest 请求实体
     * @return UserResponseDTO
     */
    @ApiOperation("查询用户通过任意参数")
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    ResponseData<UserResponseDTO> queryUser(UserQueryRequest userQueryRequest);

    /**
     * 查询所有用户
     * @return UserResponseDTO
     */
    @ApiOperation("查询所有用户")
    @RequestMapping(value = "/queryAllUser", method = RequestMethod.GET)
    ResponseData<List<UserResponseDTO>> queryAllUser();

    /**
     * 查询所有用户
     * @return UserResponse
     */
    @ApiOperation("查询所有用户2")
    @RequestMapping(value = "/queryAllUser1", method = RequestMethod.GET)
    ResponseData<UserResponse> queryAllUser1();

    /**
    * 分页查询
    *
    * @param pageBase 请求实体
    * @return UserResponse
    */
    @ApiOperation("分页查询")
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    ResponseData<UserResponse> queryPage(PageBase pageBase);

    /**
    * 添加用户
    *
    * @param userAddRequest 请求实体
    * @return Boolean
    */
    @ApiOperation("添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    ResponseData<Boolean> addUser(UserAddRequest userAddRequest);

    /**
    * 修改用户
    *
    * @param userUpdateRequest 请求实体
    * @return Boolean
    */
    @ApiOperation("修改用户")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    ResponseData<Boolean> updateUser(UserUpdateRequest userUpdateRequest);

    /**
    * 删除用户
    *
    * @param id 唯一标识
    * @return Boolean
    */
    @ApiOperation("删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    ResponseData<Boolean> deleteUser(Long id);

}
