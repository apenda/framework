package com.apenda.framework.web.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author rui.zhou
 * @date 2021/06/01 19:33
 **/
@Data
public class UserRequestDTO {
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
     * 邮箱 以下注解用其一即可
     */
    @Email
    @Pattern(message = "email format is incorrect", regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
    private String email;
}
