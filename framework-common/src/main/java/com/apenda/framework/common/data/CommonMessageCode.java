package com.apenda.framework.common.data;

/**
 * 通用错误码
 *
 * @author rui.zhou
 * @date 2021/05/21 15:05
 */
public enum CommonMessageCode implements MessageCode{

    /**
     * 未知系统异常, 请稍后再试
     */
    UNKNOWN_EXCEPTION(-1, "未知系统异常, 请稍后再试"),
    SUCCESS(0, "成功"),
    INSERT_EXCEPTION(10, "添加新数据时发生异常"),
    INSERT_BATCH_EXCEPTION(11, "批量添加新数据时发生异常"),
    UPDATE_EXCEPTION(20, "数据更新时发生异常"),
    DELETE_EXCEPTION(30, "删除数据时发生异常"),
    DISABLE_EXCEPTION(31, "禁用数据时发生异常"),
    ENABLE_EXCEPTION(32, "启用数据时发生异常"),
    SELECT_ONE_EXCEPTION(40, "获取单个数据时发生异常"),
    SELECT_EXCEPTION(41, "获取数据时发生异常"),
    SELECT_PAGINATION_EXCEPTION(42, "获取分页数据时发生异常"),
    INVALID_ARGUMENT(50, "无效参数{0}");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 描述信息
     */
    private final String message;

    CommonMessageCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return Integer.toString(getCode());
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return the code
     */
    @Override
    public int getCode() {
        return code;
    }
}
