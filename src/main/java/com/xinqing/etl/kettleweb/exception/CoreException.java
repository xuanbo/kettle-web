package com.xinqing.etl.kettleweb.exception;

/**
 * 核心异常
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class CoreException extends RuntimeException {

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

}
