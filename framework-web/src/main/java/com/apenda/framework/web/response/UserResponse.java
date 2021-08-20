package com.apenda.framework.web.response;

import com.apenda.framework.web.dto.UserDTO;
import lombok.Data;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/06/01 19:31
 **/
@Data
public class UserResponse {

    /**
     * 用户列表
     */
    private List<UserDTO> userList;

    public UserResponse() {
    }

    public UserResponse(List<UserDTO> userList) {
        this.userList = userList;
    }
}
