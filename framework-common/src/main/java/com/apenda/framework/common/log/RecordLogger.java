package com.apenda.framework.common.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author rui.zhou
 * @date 2021年09月20日 13:50
 */
@Slf4j
@Component
public class RecordLogger implements Recordable {

    private static final EnumMap<Level, EnumMap<RecordMode, Consumer<RecordData>>> logMap = new EnumMap<>(Level.class);

    /**
     * jdk建议将 ThreadLocal 定义为 private static ，这样就不会有弱引用，内存泄漏的问题了
     */
    private static final ThreadLocal<LogBody> threadLocalMap = new ThreadLocal<>();

    private static final ThreadLocal<List<RecordData>> threadLocalLogMap = new ThreadLocal<>();

    @PostConstruct
    public void init(){
        this.addLog(Level.INFO, RecordMode.MSG, recordData -> log.info(recordData.getMsg()));
        this.addLog(Level.INFO, RecordMode.MSG_ERROR, recordData -> log.info(recordData.getFormat(), recordData.getThrowable()));
        this.addLog(Level.INFO, RecordMode.FORMAT_ARG, recordData -> log.info(recordData.getFormat(), recordData.getArg()));
        this.addLog(Level.INFO, RecordMode.FORMAT_2ARG, recordData -> log.info(recordData.getFormat(), recordData.getArg1(), recordData.getArg2()));
        this.addLog(Level.INFO, RecordMode.FORMAT_ARGUMENTS, recordData -> log.info(recordData.getFormat(), recordData.getArguments()));

        this.addLog(Level.DEBUG, RecordMode.MSG, recordData -> log.debug(recordData.getMsg()));
        this.addLog(Level.DEBUG, RecordMode.MSG_ERROR, recordData -> log.debug(recordData.getFormat(), recordData.getThrowable()));
        this.addLog(Level.DEBUG, RecordMode.FORMAT_ARG, recordData -> log.debug(recordData.getFormat(), recordData.getArg()));
        this.addLog(Level.DEBUG, RecordMode.FORMAT_2ARG, recordData -> log.debug(recordData.getFormat(), recordData.getArg1(), recordData.getArg2()));
        this.addLog(Level.DEBUG, RecordMode.FORMAT_ARGUMENTS, recordData -> log.debug(recordData.getFormat(), recordData.getArguments()));

        this.addLog(Level.TRACE, RecordMode.MSG, recordData -> log.trace(recordData.getMsg()));
        this.addLog(Level.TRACE, RecordMode.MSG_ERROR, recordData -> log.trace(recordData.getFormat(), recordData.getThrowable()));
        this.addLog(Level.TRACE, RecordMode.FORMAT_ARG, recordData -> log.trace(recordData.getFormat(), recordData.getArg()));
        this.addLog(Level.TRACE, RecordMode.FORMAT_2ARG, recordData -> log.trace(recordData.getFormat(), recordData.getArg1(), recordData.getArg2()));
        this.addLog(Level.TRACE, RecordMode.FORMAT_ARGUMENTS, recordData -> log.trace(recordData.getFormat(), recordData.getArguments()));

        this.addLog(Level.WARN, RecordMode.MSG, recordData -> log.warn(recordData.getMsg()));
        this.addLog(Level.WARN, RecordMode.MSG_ERROR, recordData -> log.warn(recordData.getFormat(), recordData.getThrowable()));
        this.addLog(Level.WARN, RecordMode.FORMAT_ARG, recordData -> log.warn(recordData.getFormat(), recordData.getArg()));
        this.addLog(Level.WARN, RecordMode.FORMAT_2ARG, recordData -> log.warn(recordData.getFormat(), recordData.getArg1(), recordData.getArg2()));
        this.addLog(Level.WARN, RecordMode.FORMAT_ARGUMENTS, recordData -> log.warn(recordData.getFormat(), recordData.getArguments()));

        this.addLog(Level.ERROR, RecordMode.MSG, recordData -> log.error(recordData.getMsg()));
        this.addLog(Level.ERROR, RecordMode.MSG_ERROR, recordData -> log.error(recordData.getFormat(), recordData.getThrowable()));
        this.addLog(Level.ERROR, RecordMode.FORMAT_ARG, recordData -> log.error(recordData.getFormat(), recordData.getArg()));
        this.addLog(Level.ERROR, RecordMode.FORMAT_2ARG, recordData -> log.error(recordData.getFormat(), recordData.getArg1(), recordData.getArg2()));
        this.addLog(Level.ERROR, RecordMode.FORMAT_ARGUMENTS, recordData -> log.error(recordData.getFormat(), recordData.getArguments()));
    }

    /**
     * 获取当前线程请求信息
     *
     * @return LogBody
     */
    public LogBody get(){
        return threadLocalMap.get();
    }

    /**
     * 获取当前线程记录信息
     *
     * @return List<Record>
     */
    public List<RecordData> getRecord(){
        return threadLocalLogMap.get();
    }

    /**
     * 设置当前线程的存的变量
     *
     * @param logBody 对象
     */
    public void set(LogBody logBody) {
        threadLocalMap.set(logBody);
    }

    /**
     * 设置当前线程记录信息
     *
     * @param recordDataList 记录信息
     */
    public void setRecord(List<RecordData> recordDataList) {
        threadLocalLogMap.set(recordDataList);
    }

    /**
     * 移除当前线程的存的变量
     */
    public void remove() {
        threadLocalMap.remove();
        threadLocalLogMap.remove();
    }

    public void recordLog(Level level, String msg) {
        RecordData recordData = new RecordData(level, msg);
        recordData.setRecordMode(RecordMode.MSG);
        this.recordData(recordData);
    }

    @Override
    public void recordLog(Level level, String format, Object arg) {
        RecordData recordData = new RecordData(level, format, arg);
        recordData.setRecordMode(RecordMode.FORMAT_ARG);
        this.recordData(recordData);
    }

    @Override
    public void recordLog(Level level, String format, Object arg1, Object arg2) {
        RecordData recordData = new RecordData(level, format, arg1, arg1);
        recordData.setRecordMode(RecordMode.FORMAT_2ARG);
        this.recordData(recordData);
    }

    @Override
    public void recordLog(Level level, String format, Object... arguments) {
        RecordData recordData = new RecordData(level, format, arguments);
        recordData.setRecordMode(RecordMode.FORMAT_ARGUMENTS);
        this.recordData(recordData);
    }

    @Override
    public void recordLog(Level level, String msg, Throwable throwable) {
        RecordData recordData = new RecordData(level, msg, throwable);
        recordData.setRecordMode(RecordMode.MSG_ERROR);
        this.recordData(recordData);
    }

    @Override
    public void log(RecordData recordData) {
        Map<RecordMode, Consumer<RecordData>> recordModeConsumerMap = logMap.get(recordData.getLevel());
        Consumer<RecordData> recordDataConsumer = recordModeConsumerMap.get(recordData.getRecordMode());
        recordDataConsumer.accept(recordData);
    }

    @Override
    public void addLog(Level level, RecordMode recordMode, Consumer<RecordData> consumer) {
        EnumMap<RecordMode, Consumer<RecordData>> enumMap = logMap.get(level);
        if (enumMap == null) {
            enumMap = new EnumMap<>(RecordMode.class);
        }
        enumMap.put(recordMode, consumer);
        logMap.put(level, enumMap);
    }

    private void recordData(RecordData recordData) {
        List<RecordData> recordDataList = this.getRecord();
        if (recordDataList == null) {
            recordDataList = new ArrayList<>();
        }
        recordDataList.add(recordData);
        this.setRecord(recordDataList);
    }

}
