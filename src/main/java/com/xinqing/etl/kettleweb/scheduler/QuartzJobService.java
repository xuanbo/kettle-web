package com.xinqing.etl.kettleweb.scheduler;

import com.xinqing.etl.kettleweb.dto.ScheduleJobDTO;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * quartz任务服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    public void addJob(ScheduleJobDTO scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(jobName)
                .build();
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCron());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void updateJob(ScheduleJobDTO scheduleJob) throws Exception {
        String jobName = getJobName(scheduleJob);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
        CronTrigger oldTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCron());
        CronTrigger newTrigger = oldTrigger.getTriggerBuilder()
                .withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder)
                .build();
        JobKey jobKey = JobKey.jobKey(jobName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
        scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    public void deleteJob(ScheduleJobDTO scheduleJob) throws SchedulerException {
        String jobName = getJobName(scheduleJob);
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.deleteJob(jobKey);
    }

    private String getJobName(ScheduleJobDTO scheduleJob) {
        return "job_" + scheduleJob.getJobId() + "_" + scheduleJob.getId();
    }

}
