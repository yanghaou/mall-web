package com.mall.admin.controller;

import com.mall.common.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        LOGGER.info("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        Result result = new Result(1, "参数错误", null);
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        LOGGER.info("参数校验异常:{}({})");
        Result result = new Result(1, "参数类型错误", null);
        return result;
    }

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 binding result 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        LOGGER.info("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        Result result = new Result(1, "参数缺失", null);
        return result;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleRequestParameterException(MissingServletRequestParameterException ex) {
        //校验 required RequestParameter 是否缺失
        LOGGER.info("必填校验异常:{}({})", ex);
        Result result = new Result(1, "参数缺失:"+ex.getParameterName(), null);
        return result;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Result handleDeleteNoId(EmptyResultDataAccessException ex) {
        //校验 required RequestParameter 是否缺失
        LOGGER.info("根据id删除异常:{}({})", ex);
        Result result = new Result(1, ex.getMessage(), null);
        return result;
    }


}
