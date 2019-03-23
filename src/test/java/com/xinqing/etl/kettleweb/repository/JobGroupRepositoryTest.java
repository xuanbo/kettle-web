package com.xinqing.etl.kettleweb.repository;

import com.xinqing.etl.kettleweb.dto.JobGroupDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * job组Repository
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobGroupRepositoryTest {

    @Autowired
    private JobGroupRepository jobGroupRepository;

    @Test
    public void insert() {
        for (int i = 0; i < 150; i++) {
            JobGroupDTO jobGroup = new JobGroupDTO();
            jobGroup.setName("test" + i);
            jobGroup.setDescription("测试组" + i);
            jobGroupRepository.save(jobGroup);
        }
    }

    @Test
    public void update() {
        JobGroupDTO jobGroup = new JobGroupDTO();
        jobGroup.setId(1L);
        jobGroup.setName("test");
        jobGroup.setDescription("测试组");
        jobGroupRepository.save(jobGroup);
    }
}
