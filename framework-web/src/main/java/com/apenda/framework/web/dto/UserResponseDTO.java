package com.apenda.framework.web.dto;

import com.apenda.framework.common.annotation.Encrypt;
import com.apenda.framework.common.constant.FieldEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

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
     * 手机号
     */
    private String phone;

    /**
     * 身份证
     */
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
    @Encrypt(field = FieldEnum.EMAIL)
    private String email;

    /**
     * 最后更新日期
     */
    private Date lastUpdateDate;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

}
