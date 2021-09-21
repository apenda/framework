package com.apenda.framework.common.log;

import lombok.Data;
import org.slf4j.event.Level;

import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/09/21 10:44
 **/
@Data
public class RecordData {

    /**
    * 日志级别
    */
    private Level level;

    /**
     * 日志格式
     */
    private String format;

    /**
     * 日志信息
     */
    private String msg;

    /**
    * 数据对象
    */
    private Object arg;

    /**
     * 数据对象1
     */
    private Object arg1;

    /**
     * 数据对象2
     */
    private Object arg2;

    /**
     * 数据对象2
     */
    private List<Object> arguments;

    /**
    * 异常信息
    */
    private Throwable throwable;

    /**
     * 记录类型枚举
     */
    private RecordMode recordMode;

    public RecordData() {
    }

    public RecordData(Level level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    public RecordData(Level level, String format, Object arg) {
        this.level = level;
        this.format = format;
        this.arg = arg;
    }

    public RecordData(Level level, String format, Object arg1, Object arg2) {
        this.level = level;
        this.format = format;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public RecordData(Level level, String format, Object arg2, List<Object> arguments) {
        this.level = level;
        this.format = format;
        this.arg2 = arg2;
        this.arguments = arguments;
    }

    public RecordData(Level level, String msg, Throwable throwable) {
        this.level = level;
        this.msg = msg;
        this.throwable = throwable;
    }
}

