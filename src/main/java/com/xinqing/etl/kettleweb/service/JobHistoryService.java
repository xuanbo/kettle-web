package com.xinqing.etl.kettleweb.service;

import com.xinqing.etl.kettleweb.domain.HistoryDashboard;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;

import java.util.List;

/**
 * job历史服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface JobHistoryService extends BaseService<JobHistoryDTO> {

    /**
     * 查询任务状态
     *
     * @return 任务状态
     */
    List<HistoryDashboard> findGroupByStatus();
}
