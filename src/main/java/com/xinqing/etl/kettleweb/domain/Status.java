package com.xinqing.etl.kettleweb.domain;

/**
 * 状态
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public enum Status {

    /**
     * 未知状态
     */
    UNKNOWN(-1, "未知状态"),

    /**
     * 等待运行
     */
    PENDING(0, "等待运行"),

    /**
     * 运行中
     */
    RUNNING(10, "运行中"),

    /**
     * 成功
     */
    SUCCESS(20, "成功"),

    /**
     * 失败
     */
    FAILED(30, "失败");

    private Integer status;

    private String description;

    Status(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer value() {
        return status;
    }

    public String description() {
        return description;
    }

    public static Status forName(Integer value) {
        Status[] statuses = values();
        for (Status status : statuses) {
            if (status.value().equals(value)) {
                return status;
            }
        }
        return UNKNOWN;
    }

}
