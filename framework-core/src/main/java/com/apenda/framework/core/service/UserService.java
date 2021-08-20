package com.apenda.framework.core.service;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.web.request.PageBase;
import com.apenda.framework.web.request.UserAddRequest;
import com.apenda.framework.web.request.UserQueryRequest;
import com.apenda.framework.web.request.UserUpdateRequest;
import com.apenda.framework.web.response.UserResponse;
import com.apenda.framework.web.dto.UserResponseDTO;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/06/01 19:40
 **/
public interface UserService {

    /**
     * id查用户
     *
     * @param id 唯一标识
     * @return UserResponseDTO
     */
    ResponseData<UserResponseDTO> queryUserById(Long id);

    /**
     * 查所有
     *
     * @return UserResponseDTO
     */
    ResponseData<List<UserResponseDTO>> queryAllUser();

    /**
     * 任意参数查用户
     *
     * @param userQueryRequest 请求实体
     * @return UserResponseDTO
     */
    ResponseData<UserResponseDTO> queryUser(UserQueryRequest userQueryRequest);

    /**
     * 查所有1
     *
     * @return UserResponse
     */
    ResponseData<UserResponse> queryAllUser1();

    /**
     * 分页查询
     *
     * @param pageBase 请求实体
     * @return UserResponse
     */
    ResponseData<UserResponse> queryPage(PageBase pageBase);

    /**
     * 添加用户
     *
     * @param userAddRequest 请求实体
     * @return Boolean
     */
    ResponseData<Boolean> addUser(UserAddRequest userAddRequest);

    /**
     * 修改用户
     *
     * @param userUpdateRequest 请求实体
     * @return Boolean
     */
    ResponseData<Boolean> updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * 删除用户
     *
     * @param id 唯一标识
     * @return Boolean
     */
    ResponseData<Boolean> deleteUser(Long id);
}
