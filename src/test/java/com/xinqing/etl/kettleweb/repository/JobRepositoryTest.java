package com.xinqing.etl.kettleweb.repository;

import com.xinqing.etl.kettleweb.dto.JobDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * job Repository
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void insert() {
        for (int i = 0; i < 150; i++) {
            JobDTO job = new JobDTO();
            job.setGroupId(1L);
            job.setPath("demo.kjb");
            job.setName("任务" + i);
            job.setDescription("任务" + i);
            jobRepository.save(job);
        }
    }

}
