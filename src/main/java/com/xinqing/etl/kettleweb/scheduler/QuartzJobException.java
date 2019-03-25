package com.xinqing.etl.kettleweb.scheduler;

/**
 * quartz异常
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class QuartzJobException extends RuntimeException {

    public QuartzJobException(Throwable cause) {
        super(cause);
    }

}
