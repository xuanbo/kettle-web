package com.xinqing.etl.kettleweb.scheduler;

import com.xinqing.etl.kettleweb.dto.ScheduleJobDTO;
import com.xinqing.etl.kettleweb.util.ApplicationContextUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 每动态的添加一个定时任务就是创建此类的实例。并把要执行的定时任务类的名称或者全限定类名以及要执行的方法名传入
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class QuartzJobFactory extends QuartzJobBean {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJobDTO scheduleJob = (ScheduleJobDTO) context.getMergedJobDataMap().get("scheduleJob");
        String beanName = scheduleJob.getBeanName();
        Long jobId = scheduleJob.getJobId();
        AbstractJob job = null;
        try {
            job = ApplicationContextUtil.getBean(beanName, AbstractJob.class);
        } catch (BeansException e) {
            LOG.error("find bean error.s", e);
        }
        if (job != null) {
            job.doExecute(jobId);
        }
    }

}
