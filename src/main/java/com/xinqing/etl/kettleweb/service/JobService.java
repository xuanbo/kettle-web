package com.xinqing.etl.kettleweb.service;

import com.xinqing.etl.kettleweb.dto.JobDTO;

/**
 * job服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface JobService extends BaseService<JobDTO> {

    /**
     * 执行任务
     *
     * @param id 任务id
     */
    void exec(Long id);

}
