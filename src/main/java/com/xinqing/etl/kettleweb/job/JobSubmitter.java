package com.xinqing.etl.kettleweb.job;

import com.xinqing.etl.kettleweb.domain.JobParam;
import com.xinqing.etl.kettleweb.domain.Status;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import com.xinqing.etl.kettleweb.pool.StandardThreadExecutor;
import com.xinqing.etl.kettleweb.service.JobHistoryService;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryListener;
import org.pentaho.di.job.JobListener;
import org.pentaho.di.job.JobMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 任务提交
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Component
public class JobSubmitter {

    public static final String VARIABLE_JOB_ID = "GLOBAL_JOB_ID";

    public static final String VARIABLE_JOB_HISTORY_ID = "GLOBAL_JOB_HISTORY_ID";

    private static final Logger LOG = LoggerFactory.getLogger(JobSubmitter.class);

    @Autowired
    private JobHistoryService jobHistoryService;

    @Autowired
    private StandardThreadExecutor kettleJobExecutor;

    @Autowired
    private JobEntryListener logJobEntryListener;

    @Autowired
    private JobListener logJobListener;

    /**
     * 提交
     *
     * @param param JobParam
     */
    public void submit(JobParam param) {
        kettleJobExecutor.execute(() -> {
            Long jobHistoryId = null;
            try {
                // job历史
                jobHistoryId = jobPending(param);
                // 创建job
                JobMeta meta = new JobMeta(param.getPath(), null);
                Job job = new Job(null, meta);

                // 日志
                job.setLogLevel(LogLevel.ROWLEVEL);

                // 设置变量
                Map<String, String> variables = param.getVariables();
                for (Map.Entry<String, String> variable : variables.entrySet()) {
                    job.setVariable(variable.getKey(), variable.getValue());
                }
                // 全局job变量设置
                job.setVariable(VARIABLE_JOB_ID, job.getId() + "");
                job.setVariable(VARIABLE_JOB_HISTORY_ID, jobHistoryId + "");

                // 添加listener
                job.addJobEntryListener(logJobEntryListener);
                job.addJobListener(logJobListener);

                // 运行job
                job.start();
                // 等待job执行完毕
                job.waitUntilFinished();
            } catch (Exception e) {
                LOG.error("job submit unknown error", e);
                if (jobHistoryId != null) {
                    try {
                        JobHistoryDTO jobHistory = new JobHistoryDTO();
                        jobHistory.setId(jobHistoryId);
                        jobHistory.setJobId(param.getId());
                        jobHistory.setStatus(Status.FAILED.value());
                        jobHistory.setLogText(e.getMessage());
                        jobHistoryService.save(jobHistory);
                    } catch (Exception ex) {
                        // ignore
                        LOG.error("job history save error", e);
                    }
                }
            }
        });
    }

    private Long jobPending(JobParam param) {
        JobHistoryDTO jobHistory = new JobHistoryDTO();
        jobHistory.setJobId(param.getId());
        jobHistory.setStatus(Status.PENDING.value());
        jobHistoryService.save(jobHistory);
        Long jobHistoryId = jobHistory.getId();
        LOG.info("job submitted, job history id: {}", jobHistoryId);
        return jobHistoryId;
    }

    /**
     * 是否在执行
     *
     * @return 执行返回true
     */
    public boolean isActive() {
        return kettleJobExecutor.getSubmittedTasksCount() > 0;
    }

}
