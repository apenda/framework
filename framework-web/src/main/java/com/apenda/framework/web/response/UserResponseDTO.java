package com.apenda.framework.web.response;

import lombok.Data;

/**
 * @author rui.zhou
 * @date 2021/06/01 19:31
 **/
@Data
public class UserResponseDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;
}
