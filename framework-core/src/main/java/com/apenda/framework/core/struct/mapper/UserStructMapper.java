package com.apenda.framework.core.struct.mapper;

import com.apenda.framework.dao.entity.User;
import com.apenda.framework.web.response.UserResponseDTO;
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
}
