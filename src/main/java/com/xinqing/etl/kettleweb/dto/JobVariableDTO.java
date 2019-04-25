package com.xinqing.etl.kettleweb.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * job变量
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Entity
@Table(name = "job_variable")
public class JobVariableDTO extends BaseDTO {

    private Long jobId;

    private String k;

    private String v;

    private String description;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobVariableDTO{" +
                "jobId=" + jobId +
                ", k='" + k + '\'' +
                ", v='" + v + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
