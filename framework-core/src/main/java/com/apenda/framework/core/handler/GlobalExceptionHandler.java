package com.apenda.framework.core.handler;

import com.apenda.framework.common.data.CommonMessageCode;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.common.exception.BusinessException;
import com.apenda.framework.common.log.RecordLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * @author rui.zhou
 * @date 2021/06/01 16:05
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private RecordLogger recordLogger;

    /**
     * 业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData businessExceptionHandler(BusinessException e) {
        recordLogger.remove();
        log.error( "An error occurred while processing your request : Cause by "+ e,e);
        return new ResponseData(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData otherExceptionHandler(Exception e) {
        recordLogger.remove();
        log.error( "An error occurred while processing your request : Cause by "+ e,e);
        return new ResponseData(CommonMessageCode.UNKNOWN_EXCEPTION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        recordLogger.remove();
        String message = getMessage(e);
        return new ResponseData(CommonMessageCode.INVALID_ARGUMENT.formatMessage(message));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData bindExceptionHandler(BindException e) {
        recordLogger.remove();
        String message = getMessage(e);
        return new ResponseData(CommonMessageCode.INVALID_ARGUMENT.formatMessage(message));
    }

    private String getMessage(BindException e) {
        log.debug("Exception occurred while processing your request : Cause by " + e, e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return message;
    }
}
