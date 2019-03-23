package com.xinqing.etl.kettleweb.job;

import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import org.pentaho.di.core.Result;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryListener;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.springframework.stereotype.Component;

/**
 * 记录job entry日志
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class LogJobEntryListener extends AbstractLogListener implements JobEntryListener {

    @Override
    public void beforeExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface) {
    }

    @Override
    public void afterExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface, Result result) {
        JobHistoryDTO jobHistory = getJobHistory(job);
        jobHistory.setLogText(result.getLogText());
        save(jobHistory);
    }

}
