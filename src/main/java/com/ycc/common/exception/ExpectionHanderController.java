package com.ycc.common.exception;

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
public class ExpectionHanderController {

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
}
