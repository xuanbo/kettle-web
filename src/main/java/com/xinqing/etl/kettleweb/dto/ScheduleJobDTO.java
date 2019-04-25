package com.xinqing.etl.kettleweb.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 计划任务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Entity
@Table(name = "schedule_job")
public class ScheduleJobDTO extends BaseDTO {

    private Long jobId;

    private String cron;

    private String description;

    private String beanName;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "ScheduleJobDTO{" +
                "jobId=" + jobId +
                ", cron='" + cron + '\'' +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
