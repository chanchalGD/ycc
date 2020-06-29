package com.ycc.common.exception.handler;

import com.ycc.common.exception.ApiException;
import com.ycc.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理控制器
 * @author: ccc
 * @date 2020/06/29
 *
**/

@Slf4j
@ControllerAdvice
public class ExpectionHandlerController {

    @ExceptionHandler({ ApiException.class })
    @ResponseBody
    public Response<?> handleApiException(ApiException e) {
        log.error("接口服务异常", e);
        return new Response().failed(e.getMessage());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Response<?> handleException(Exception e) {
        log.error("服务异常", e);
        return new Response().failed("服务异常，请检查数据是否正确");
    }
}
