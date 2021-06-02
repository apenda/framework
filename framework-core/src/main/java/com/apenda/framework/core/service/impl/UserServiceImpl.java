package com.apenda.framework.core.service.impl;

import com.apenda.framework.common.data.CommonMessageCode;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.common.util.ObjectUtils;
import com.apenda.framework.component.service.UserComponentService;
import com.apenda.framework.core.service.UserService;
import com.apenda.framework.core.struct.mapper.UserStructMapper;
import com.apenda.framework.dao.entity.User;
import com.apenda.framework.web.request.UserRequestDTO;
import com.apenda.framework.web.response.UserResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
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
        User user = ObjectUtils.defaultIfNull(userComponentService.getById(id), new User());
        return new ResponseData(CommonMessageCode.SUCCESS, UserStructMapper.INSTANCE.userToUserResponse(user));
    }

    @Override
    public ResponseData<List<UserResponseDTO>> queryAllUser() {
        List<User> userList = userComponentService.list();
        List<UserResponseDTO> resultList = CollectionUtils.emptyIfNull(userList).stream()
                .map(UserStructMapper.INSTANCE::userToUserResponse).collect(Collectors.toList());
        return new ResponseData(CommonMessageCode.SUCCESS,resultList);
    }

    @Override
    public ResponseData<UserResponseDTO> queryUser(UserRequestDTO userRequest) {
        User user = ObjectUtils.defaultIfNull(userComponentService.selectOne(userRequest), new User());
        return new ResponseData(CommonMessageCode.SUCCESS, UserStructMapper.INSTANCE.userToUserResponse(user));
    }
}
