package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.domain.Status;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import com.xinqing.etl.kettleweb.service.JobHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * job历史服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
public class JobHistoryServiceImpl extends BaseServiceImpl<JobHistoryDTO> implements JobHistoryService {

    @Override
    public Page<JobHistoryDTO> page(JobHistoryDTO entity, Pageable pageable) {
        Page<JobHistoryDTO> page = super.page(entity, pageable);
        page.getContent().forEach(item -> {
            Status status = Status.forName(item.getStatus());
            item.setStatusMsg(status.description());
        });
        return page;
    }
}
