package com.xinqing.etl.kettleweb.service;

import com.xinqing.etl.kettleweb.dto.JobVariableDTO;

import java.util.List;

/**
 * job变量服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface JobVariableService extends BaseService<JobVariableDTO> {

    List<JobVariableDTO> findByJobId(Long jobId);

}
