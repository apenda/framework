package com.apenda.framework.web.controller;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.web.request.UserRequestDTO;
import com.apenda.framework.web.response.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/05/07 17:12
 **/
@Api(tags = "用户管理DEMO")
public interface IUserController {

    /**
     * ID查询用户
     * @param id
     * @return
     */
    @ApiOperation("查询用户通过ID")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    @RequestMapping(value = "/queryUserById", method = RequestMethod.GET)
    ResponseData<UserResponseDTO> queryUserById(Long id);

    /**
     * 查询所有用户
     * @return
     */
    @ApiOperation("查询所有用户")
    @RequestMapping(value = "/queryAllUser", method = RequestMethod.GET)
    ResponseData<List<UserResponseDTO>> queryAllUser();

    /**
     * 查询用户通过任意参数
     * @param userRequest
     * @return
     */
    @ApiOperation("查询用户通过任意参数")
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    ResponseData<UserResponseDTO> queryUser(UserRequestDTO userRequest);
}
