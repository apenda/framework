package com.apenda.framework.core.service;

import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.web.request.UserRequestDTO;
import com.apenda.framework.web.response.UserResponseDTO;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/06/01 19:40
 **/
public interface UserService {

    /**
     * id查用户
     * @param id
     * @return
     */
    ResponseData<UserResponseDTO> queryUserById(Long id);

    /**
     * 查所有
     * @return
     */
    ResponseData<List<UserResponseDTO>> queryAllUser();

    /**
     * 任意参数查用户
     * @param userRequest
     * @return
     */
    ResponseData<UserResponseDTO> queryUser(UserRequestDTO userRequest);
}
