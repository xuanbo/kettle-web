package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.domain.Api;
import com.xinqing.etl.kettleweb.domain.JobParam;
import com.xinqing.etl.kettleweb.dto.JobDTO;
import com.xinqing.etl.kettleweb.dto.JobVariableDTO;
import com.xinqing.etl.kettleweb.job.JobSubmitter;
import com.xinqing.etl.kettleweb.service.JobService;
import com.xinqing.etl.kettleweb.service.JobVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * job服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
public class JobServiceImpl extends BaseServiceImpl<JobDTO> implements JobService {

    @Autowired
    private JobVariableService jobVariableService;

    @Autowired
    private JobSubmitter submitter;

    @Override
    public Api<?> exec(Long id) {
        JobDTO job = find(id);
        if (job == null) {
            logger.warn("Job not found, jobId: {}", id);
            return Api.fail("任务不存在", null);
        }
        Map<String, String> variables = jobVariableService.findByJobId(id).stream()
                .collect(Collectors.toMap(JobVariableDTO::getK, JobVariableDTO::getV));
        JobParam param = new JobParam();
        param.setId(id);
        param.setPath(job.getPath());
        param.setVariables(variables);
        submitter.submit(param);
        return Api.ok(null);
    }

}
