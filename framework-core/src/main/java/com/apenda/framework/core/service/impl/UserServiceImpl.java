package com.apenda.framework.core.service.impl;

import com.apenda.framework.common.config.CustomIdGenerator;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.component.service.UserComponentService;
import com.apenda.framework.core.service.UserService;
import com.apenda.framework.core.struct.mapper.UserStructMapper;
import com.apenda.framework.dao.entity.User;
import com.apenda.framework.web.dto.UserDTO;
import com.apenda.framework.web.request.PageBase;
import com.apenda.framework.web.request.UserAddRequest;
import com.apenda.framework.web.request.UserQueryRequest;
import com.apenda.framework.web.request.UserUpdateRequest;
import com.apenda.framework.web.response.UserResponse;
import com.apenda.framework.web.dto.UserResponseDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rui.zhou
 * @date 2021/06/01 19:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserComponentService userComponentService;

    @Override
    public ResponseData<UserResponseDTO> queryUserById(Long id) {
        return new ResponseData<>(UserStructMapper.INSTANCE.userToUserResponse(userComponentService.getById(id)));
    }

    @Override
    public ResponseData<List<UserResponseDTO>> queryAllUser() {
        List<User> list = userComponentService.list();
        List<UserResponseDTO> collect = list.stream().map(UserStructMapper.INSTANCE::userToUserResponse).collect(Collectors.toList());
        collect.forEach(userResponseDTO -> userResponseDTO.setUserDTO(UserStructMapper.INSTANCE.UserResponseDTOToUser(userResponseDTO)));
        return new ResponseData<>(collect);
    }

    @Override
    public ResponseData<UserResponseDTO> queryUser(UserQueryRequest userRequest) {
        return new ResponseData<>(UserStructMapper.INSTANCE.userToUserResponse(userComponentService.selectOne(userRequest)));
    }

    @Override
    public ResponseData<UserResponse> queryAllUser1() {
        List<User> list = userComponentService.list();
        List<UserDTO> collect = list.stream().map(UserStructMapper.INSTANCE::userToUserDTO).collect(Collectors.toList());
        collect.forEach(userDTO -> userDTO.setUserResponseDTO(UserStructMapper.INSTANCE.userDTOToUserResponse(userDTO)));
        return new ResponseData<>(new UserResponse(collect));
    }

    @Override
    public ResponseData<UserResponse> queryPage(PageBase pageBase) {
        IPage<User> page = userComponentService.page(new Page<>(pageBase.getCurrent(), pageBase.getSize()));
        List<UserDTO> collect = page.getRecords().stream().map(UserStructMapper.INSTANCE::userToUserDTO).collect(Collectors.toList());
        return new ResponseData<>(new UserResponse(collect), (int) page.getSize());
    }

    @Override
    public ResponseData<Boolean> addUser(UserAddRequest userAddRequest) {
        return new ResponseData<>(userComponentService.save(UserStructMapper.INSTANCE.userAddRequestToUser(userAddRequest)));
    }

    @Override
    public ResponseData<Boolean> updateUser(UserUpdateRequest userUpdateRequest) {
        return new ResponseData<>(userComponentService.updateById(UserStructMapper.INSTANCE.userUpdateRequestToUser(userUpdateRequest)));
    }

    @Override
    public ResponseData<Boolean> deleteUser(Long id) {
        return new ResponseData<>(userComponentService.removeById(id));
    }
}
