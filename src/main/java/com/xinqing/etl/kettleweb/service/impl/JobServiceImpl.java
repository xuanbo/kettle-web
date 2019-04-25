package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.domain.JobParam;
import com.xinqing.etl.kettleweb.domain.Status;
import com.xinqing.etl.kettleweb.dto.JobDTO;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import com.xinqing.etl.kettleweb.dto.JobVariableDTO;
import com.xinqing.etl.kettleweb.exception.CoreException;
import com.xinqing.etl.kettleweb.job.JobSubmitter;
import com.xinqing.etl.kettleweb.service.JobHistoryService;
import com.xinqing.etl.kettleweb.service.JobService;
import com.xinqing.etl.kettleweb.service.JobVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * job服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl extends BaseServiceImpl<JobDTO> implements JobService {

    @Autowired
    private JobVariableService jobVariableService;

    @Autowired
    private JobHistoryService jobHistoryService;

    @Autowired
    private JobSubmitter submitter;

    @Override
    public void exec(Long id) {
        JobDTO job = find(id);
        if (job == null) {
            logger.warn("Job not found, jobId: {}", id);
            throw new CoreException("任务不存在");
        }
        Map<String, String> variables = jobVariableService.findByJobId(id).stream()
                .collect(Collectors.toMap(JobVariableDTO::getK, JobVariableDTO::getV));
        JobParam param = new JobParam();
        param.setId(id);
        param.setPath(job.getPath());
        param.setVariables(variables);
        // 设置job等待状态
        jobPending(param);
        // 提交任务
        submitter.submit(param);
    }

    private void jobPending(JobParam param) {
        JobHistoryDTO jobHistory = new JobHistoryDTO();
        jobHistory.setJobId(param.getId());
        jobHistory.setStatus(Status.PENDING.value());
        jobHistoryService.save(jobHistory);
        Long jobHistoryId = jobHistory.getId();
        param.setHistoryId(jobHistoryId);
        logger.info("job submitted, job history id: {}", jobHistoryId);
    }

}
