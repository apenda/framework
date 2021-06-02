package com.apenda.framework.common.data;

import java.text.MessageFormat;

/**
 * @author rui.zhou
 * @date 2021/05/21 15:05
 */
public interface MessageCode {

    /**
     * 获取描述信息
     * @return
     */
    String getMessage();

    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 成功
     * @return
     */
    default boolean isSuccess() {
        return this.getCode() == 0;
    }

    /**
     * message使用了占位符（我是{0}）时可以调用该方法进行格式化
     * @param args
     * @return
     */
    default FormattedMessageCode formatMessage(Object... args) {
        return new FormattedMessageCode(this.getCode(), MessageFormat.format(this.getMessage(), args));
    }

    class FormattedMessageCode implements MessageCode {

        private Integer code;
        private String message;

        FormattedMessageCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public int getCode() {
            return this.code;
        }
    }
}
