package com.xinqing.etl.kettleweb.job;

import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import com.xinqing.etl.kettleweb.service.JobHistoryService;
import org.pentaho.di.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * log记录
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public abstract class AbstractLogListener {

    @Autowired
    private JobHistoryService jobHistoryService;

    protected JobHistoryDTO getJobHistory(Job job) {
        long id = Long.parseLong(job.getVariable(JobSubmitter.VARIABLE_JOB_HISTORY_ID));
        JobHistoryDTO jobHistory = new JobHistoryDTO();
        jobHistory.setId(id);
        return jobHistory;
    }

    protected void save(JobHistoryDTO jobHistory) {
        String logText = jobHistory.getLogText();
        // kettle日志是反的，需要调正存储，或者前端调整
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(logText)) {
            String[] lines = logText.split("\n");
            for (int i = lines.length - 1; i > 0; i--) {
                if ("null".equals(lines[i])) {
                    continue;
                }
                sb.append(lines[i]).append("\n");
            }
        }
        jobHistory.setLogText(sb.toString());
        jobHistoryService.save(jobHistory);
    }

}
