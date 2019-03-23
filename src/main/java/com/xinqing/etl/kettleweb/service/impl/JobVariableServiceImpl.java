package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.dto.JobVariableDTO;
import com.xinqing.etl.kettleweb.service.JobVariableService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * job变量服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
public class JobVariableServiceImpl extends BaseServiceImpl<JobVariableDTO> implements JobVariableService {

    @Override
    public List<JobVariableDTO> findByJobId(Long jobId) {
        JobVariableDTO jobVariable = new JobVariableDTO();
        jobVariable.setJobId(jobId);
        return repository.findAll(Example.of(jobVariable));
    }

}
