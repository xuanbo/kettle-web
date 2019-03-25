package com.xinqing.etl.kettleweb.conf;

import com.xinqing.etl.kettleweb.domain.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionConfiguration.class);

    @ExceptionHandler(Exception.class)
    public Api<?> handle(Exception e) {
        LOG.error("catch error", e);
        return Api.fail(e.getMessage(), null);
    }

}
