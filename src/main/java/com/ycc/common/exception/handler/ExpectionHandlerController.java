package com.ycc.common.exception.handler;

import com.ycc.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ccc
 *
**/

@Slf4j
@ControllerAdvice
public class ExpectionHandlerController {

    /**
     * 处理NullPointerException,理论上代码里不应该抛出空指针
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ NullPointerException.class })
    @ResponseBody
    public Response<?> handleNullPointerException(NullPointerException e) {
        log.error("未知空指针异常", e);
        return new Response().failed("服务异常，请检查数据是否正确");
    }

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Response<?> handleException(Exception e) {
        log.error("服务异常", e);
        return new Response().failed("服务异常，请检查数据是否正确");
    }
}
