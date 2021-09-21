package com.apenda.framework.common.filter;

import com.apenda.framework.common.log.LogBody;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用切面代替
 *
 * @author rui.zhou
 * @date 2021年09月20日 12:57
 */
@Slf4j
//@WebFilter(filterName = "logFilter", urlPatterns = {"/*"})
@Deprecated
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 1. 保存 URL 到生下文
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        LogBody logBody = new LogBody();
        logBody.setUrl(httpRequest.getRequestURI());
        //set(logBody);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
