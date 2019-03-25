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

    List<HistoryDashboard> findGroupByStatus();
}
