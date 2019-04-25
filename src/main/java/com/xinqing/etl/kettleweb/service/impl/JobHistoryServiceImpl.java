package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.domain.HistoryDashboard;
import com.xinqing.etl.kettleweb.domain.Status;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import com.xinqing.etl.kettleweb.repository.JobHistoryRepository;
import com.xinqing.etl.kettleweb.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * job历史服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobHistoryServiceImpl extends BaseServiceImpl<JobHistoryDTO> implements JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Override
    public Page<JobHistoryDTO> page(JobHistoryDTO entity, Pageable pageable) {
        Page<JobHistoryDTO> page = super.page(entity, pageable);
        page.getContent().forEach(item -> {
            Status status = Status.forName(item.getStatus());
            item.setStatusMsg(status.description());
        });
        return page;
    }

    @Override
    public List<HistoryDashboard> findGroupByStatus() {
        return jobHistoryRepository.findGroupByStatus().stream().map(it -> {
            Status status = Status.forName((Integer) it[0]);
            BigInteger count = (BigInteger) it[1];
            return new HistoryDashboard(status.description(), count.intValue());
        }).collect(Collectors.toList());
    }
}
