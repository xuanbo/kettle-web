package com.xinqing.etl.kettleweb.job;

import com.xinqing.etl.kettleweb.domain.Status;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import org.pentaho.di.core.Result;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobListener;
import org.springframework.stereotype.Component;

/**
 * 记录job日志
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class LogJobListener extends AbstractLogListener implements JobListener {

    @Override
    public void jobFinished(Job job) {
        JobHistoryDTO jobHistory = getJobHistory(job);
        Result result = job.getResult();
        if (result.getNrErrors() > 0) {
            jobHistory.setStatus(Status.FAILED.value());
        } else {
            jobHistory.setStatus(Status.SUCCESS.value());
        }
        jobHistory.setLogText(result.getLogText());
        save(jobHistory);
    }

    @Override
    public void jobStarted(Job job) {
        JobHistoryDTO jobHistory = getJobHistory(job);
        jobHistory.setStatus(Status.RUNNING.value());
        save(jobHistory);
    }

}
