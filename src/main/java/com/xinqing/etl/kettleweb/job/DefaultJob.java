package com.xinqing.etl.kettleweb.job;

import com.xinqing.etl.kettleweb.scheduler.AbstractJob;
import com.xinqing.etl.kettleweb.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认任务实现，执行kjb任务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class DefaultJob extends AbstractJob {

    @Autowired
    private JobService jobService;

    @Override
    protected void doExecute(Long jobId) {
        jobService.exec(jobId);
    }
}
