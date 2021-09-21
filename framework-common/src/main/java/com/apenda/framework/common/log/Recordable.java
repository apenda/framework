package com.apenda.framework.common.log;

import org.slf4j.event.Level;

import java.util.function.Consumer;

/**
 * Recorder
 *
 * @author rui.zhou
 * @date 2021/09/20 21:56
 **/
public interface Recordable {

    /**
     * 记录日志
     *
     * @param level 日志级别
     * @param msg 日志信息
     */
    void recordLog(Level level, String msg);

    /**
     * 记录日志
     *
     * @param level 日志级别
     * @param format 日志格式
     * @param arg 日志信息
     */
    void recordLog(Level level, String format, Object arg);

    /**
     * 记录日志
     *
     * @param level 日志级别
     * @param format 日志格式
     * @param arg1 日志信息1
     * @param arg2 日志信息2
     */
    void recordLog(Level level, String format, Object arg1, Object arg2);

    /**
     * 记录日志
     *
     * @param level 日志级别
     * @param format 日志格式
     * @param arguments 日志信息
     */
    void recordLog(Level level, String format, Object... arguments);

    /**
     * 记录日志
     *
     * @param level 日志级别
     * @param msg 日志格式
     * @param throwable 抛出异常
     */
    void recordLog(Level level, String msg, Throwable throwable);

    /**
     * 打印日志
     *
     * @param recordData 记录对象
     */
    void log (RecordData recordData);

    /**
     * 添加打印日志方法
     *
     * @param level 日志级别
     * @param recordMode 枚举
     * @param consumer 自定义实现
     */
    void addLog (Level level, RecordMode recordMode, Consumer<RecordData> consumer);

}
