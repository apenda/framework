package com.apenda.framework.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 更新用户
 *
 * @author rui.zhou
 * @date 2021/08/16 10:40
 **/
@Data
public class UserUpdateRequest {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message="name cannot be empty")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message="age cannot be null")
    private Integer age;

    /**
     * 邮箱
     */
    @Pattern(message = "邮箱格式不正确", regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
    private String email;
}
