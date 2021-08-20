package com.apenda.framework.core.struct.mapper;

import com.apenda.framework.dao.entity.User;
import com.apenda.framework.web.dto.UserDTO;
import com.apenda.framework.web.request.UserAddRequest;
import com.apenda.framework.web.request.UserUpdateRequest;
import com.apenda.framework.web.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author rui.zhou
 * @date 2021/05/11 15:43
 **/
@Mapper
public interface UserStructMapper {

    UserStructMapper INSTANCE = Mappers.getMapper( UserStructMapper.class );

    /**
     * User类型转换成UserResponse类型
     * @param user
     * @return
     */
    UserResponseDTO userToUserResponse(User user);

    /**
     * User 类型转换成 UserDTO 类型
     *
     * @param user
     * @return
     */
    UserDTO userToUserDTO(User user);

    /**
     * UserUpdateRequest 转 User
     * @param userUpdateRequest
     * @return
     */
    User userUpdateRequestToUser(UserUpdateRequest userUpdateRequest);

    /**
     * userAddRequest 转 User
     *
     * @param userAddRequest
     * @return
     */
    User userAddRequestToUser(UserAddRequest userAddRequest);
}
