package com.apenda.framework.web.dto;

import com.apenda.framework.common.annotation.Effect;
import com.apenda.framework.common.annotation.Sensitive;
import lombok.Data;

/**
 * 用户传输对象
 *
 * @author rui.zhou
 * @date 2021/08/09 17:27
 **/
@Data
public class UserDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证
     */
    @Sensitive
    private String idCard;

    /**
     * 银行卡
     */
    private String bankCard;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 脱敏测试
     */
    @Effect
    private UserResponseDTO userResponseDTO;
}
