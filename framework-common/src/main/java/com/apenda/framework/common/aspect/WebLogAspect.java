package com.apenda.framework.common.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 *
 * @author rui.zhou
 * @date 2021/09/20 14:57
 **/
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 切点
     * */
    @Pointcut("execution(public * com.apenda.framework.core.controller..*.*(..))")

    public void webLog() {
        // Do nothing
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint 切点对象
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 请求 url
        logger.info("Request URL    : {}", request.getRequestURL());
        // 请求 type
        logger.debug("Request Type   : {}", request.getMethod());
        // 请求 Accept
        logger.debug("Request Accept : {}", request.getHeader("Accept"));
        // Content-Type
        logger.debug("Content-Type   : {}", request.getHeader("Content-Type"));
        // 请求的 IP
        logger.debug("Request IP     : {}", request.getRemoteAddr());
        // 调用 controller 的全路径以及执行方法
        logger.debug("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 请求入参
        String args = JSON.toJSONString(joinPoint.getArgs());
        logger.info("Request Args   : {}", args);
    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter() {
        // Do nothing
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint 切点介入
     * @return Object
     * @throws Throwable 抛异常·
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        String message = JSON.toJSONString(result);
        logger.info("Response Args  : {}", message);
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

}
