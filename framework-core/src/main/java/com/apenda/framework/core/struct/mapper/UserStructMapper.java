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
     * User 类型转换成 UserResponse类型
     * @param user 用户 model
     * @return UserResponseDTO
     */
    UserResponseDTO userToUserResponse(User user);

    /**
     * userDTO 类型转换成 UserResponse类型
     * @param userDTO userDTO
     * @return UserResponseDTO
     */
    UserResponseDTO userDTOToUserResponse(UserDTO userDTO);

    /**
     * User 类型转换成 UserResponse类型
     * @param userResponseDTO 用户相应对象
     * @return UserDTO
     */
    UserDTO UserResponseDTOToUser(UserResponseDTO userResponseDTO);

    /**
     * User 类型转换成 UserDTO 类型
     *
     * @param user 用户 model
     * @return User
     */
    UserDTO userToUserDTO(User user);

    /**
     * UserUpdateRequest 转 User
     * @param userUpdateRequest 用户请求对象
     * @return User
     */
    User userUpdateRequestToUser(UserUpdateRequest userUpdateRequest);

    /**
     * userAddRequest 转 User
     *
     * @param userAddRequest 用户请求对象
     * @return User
     */
    User userAddRequestToUser(UserAddRequest userAddRequest);
}
