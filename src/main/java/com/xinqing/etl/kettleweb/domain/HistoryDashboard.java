package com.xinqing.etl.kettleweb.domain;

import java.io.Serializable;

/**
 * 历史面板
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class HistoryDashboard implements Serializable {

    private String status;

    private Integer count;

    public HistoryDashboard() {
    }

    public HistoryDashboard(String status, Integer count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
