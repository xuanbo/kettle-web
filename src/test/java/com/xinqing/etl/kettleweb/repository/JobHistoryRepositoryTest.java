package com.xinqing.etl.kettleweb.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * job历史Repository
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobHistoryRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(JobHistoryRepositoryTest.class);

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Test
    public void findGroupByStatus() {
        List<Object[]> groupByStatus = jobHistoryRepository.findGroupByStatus();
        LOG.info("groupByStatus: {}", groupByStatus);
    }
}
