package com.apenda.framework.common.util;

import com.apenda.framework.common.data.LogBody;

/**
 * @author rui.zhou
 * @date 2021年09月20日 13:50
 */
@Deprecated
public class LogContext {

    /**
     * jdk建议将 ThreadLocal 定义为 private static ，这样就不会有弱引用，内存泄漏的问题了
     */
    private static ThreadLocal<LogBody> threadLocalMap = new ThreadLocal<>();

    /**
     * 获取当前线程的存的变量
     *
     * @return
     */
    public static LogBody get(){
        return threadLocalMap.get();
    }

    /**
     * 设置当前线程的存的变量
     *
     * @param logBody
     */
    public static void set(LogBody logBody) {
        threadLocalMap.set(logBody);
    }

    /**
     * 移除当前线程的存的变量
     */
    public static void remove() {
        threadLocalMap.remove();
    }

}
