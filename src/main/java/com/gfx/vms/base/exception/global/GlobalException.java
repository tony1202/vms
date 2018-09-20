package com.gfx.vms.base.exception.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常
 * @author tony
 * @date 2018/9/19
 */
@ControllerAdvice
public class GlobalException {

    private final static Logger log = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e){


    }

}
