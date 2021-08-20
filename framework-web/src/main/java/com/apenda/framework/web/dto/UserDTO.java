package com.apenda.framework.web.dto;

import com.apenda.framework.common.annotation.Encrypt;
import com.apenda.framework.common.constant.EncryptTypeEnum;
import com.apenda.framework.common.constant.FieldEnum;
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
    @Encrypt(field = FieldEnum.ID_CARD)
    private String idCard;

    /**
     * 银行卡
     */
    @Encrypt(type = EncryptTypeEnum.ENCRYPT)
    private String bankCard;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    @Encrypt(field = FieldEnum.EMAIL)
    private String email;
}
