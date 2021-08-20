package com.apenda.framework.common.util;


import com.apenda.framework.common.constant.FieldEnum;

/**
 * 脱敏工具类
 *
 * @author rui.zhou
 * @date 2021/08/03 12:20
 **/
public class DesensitizationUtil {

    /**
     * 字段脱敏处理
     *
     * @param fieldEnum 脱敏字段枚举值
     * @param value 脱敏字段的值
     * @return String
     */
    public static String encrypt(FieldEnum fieldEnum, String value) {
        switch (fieldEnum){
            case ADDRESS:
            case BANK_CARD:
            case FIXED_PHONE:
            case CHINESE_NAME:
                break;
            case EMAIL:
                return emailEncrypt(value);
            case MOBILE_PHONE:
                return mobileEncrypt(value);
            default:
                return idEncrypt(value);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 手机号码中间四位数字脱敏
     *
     * @param mobile 手机号
     * @return String
     */
    public static String mobileEncrypt(String mobile) {
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证号脱敏
     *
     * @param id 身份证号
     * @return String
     */
    public static String idEncrypt(String id) {
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 邮箱脱敏
     *
     * @param email 邮箱
     * @return String
     */
    public static String emailEncrypt(String email) {
        return email.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2");
    }

}
