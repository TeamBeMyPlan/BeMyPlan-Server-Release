package com.deploy.bemyplan.core.advice;

import com.deploy.bemyplan.core.config.ResponseProperties;
import com.deploy.bemyplan.core.model.SingleResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseProperties responseProperties;

    public ExceptionAdvice(ResponseProperties responseProperties) {
        this.responseProperties = responseProperties;
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handle(Exception e) {
        return getResult(e, getExceptionProperties(e, ResponseProperties.ExceptionProperties.UNHANDLED));
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNoHandlerFoundException(Exception e) {
        return getResult(e, getExceptionProperties(e, ResponseProperties.ExceptionProperties.NOT_FOUND));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleNotSupportedMethodException(Exception e) {
        return getResult(e, getExceptionProperties(e, ResponseProperties.ExceptionProperties.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingRequestParameterException(Exception e) {
        return getResult(e, getExceptionProperties(e, ResponseProperties.ExceptionProperties.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationException(Exception e) {
        return getResult(e, getExceptionProperties(e, ResponseProperties.ExceptionProperties.BAD_REQUEST));
    }

    private ResponseProperties.ExceptionProperties getExceptionProperties(Exception e, ResponseProperties.ExceptionProperties unhandled) {
        ResponseProperties.ExceptionProperties exceptionModel = responseProperties.getExceptions()
                .values().stream()
                .filter(r -> r.getType().equals(e.getClass()))
                .findFirst()
                .orElse(unhandled);

        exceptionModel.setMsg(exceptionModel.getMsg());
        return exceptionModel;
    }

    private SingleResult<Object> getResult(Exception e, ResponseProperties.ExceptionProperties exceptionModel) {
        SingleResult<Object> result = new SingleResult<>();
        result.setSuccess(false);
        result.setCode(exceptionModel.getCode());
        result.setMsg(exceptionModel.getMsg());
        result.setData(e.getMessage());
        return result;
    }
}
