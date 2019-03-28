package com.xinqing.etl.kettleweb.service;

import com.xinqing.etl.kettleweb.dto.JobDTO;

/**
 * job服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface JobService extends BaseService<JobDTO> {

    void exec(Long id);

}
