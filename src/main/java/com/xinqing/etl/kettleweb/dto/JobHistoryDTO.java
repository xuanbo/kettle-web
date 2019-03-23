package com.xinqing.etl.kettleweb.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * job历史
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Entity
@Table(name = "job_history")
public class JobHistoryDTO extends BaseDTO {

    private Long jobId;

    /**
     * 运行状态
     */
    private Integer status;

    /**
     * 日志
     */
    private String logText;

    @Transient
    private String statusMsg;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
