package com.xinqing.etl.kettleweb.repository;

import com.xinqing.etl.kettleweb.dto.JobVariableDTO;
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
public class JobVariableRepositoryTest {

    @Autowired
    private JobVariableRepository jobVariableRepository;

    @Test
    public void insert() {
        for (int i = 0; i < 150; i++) {
            JobVariableDTO jobVariable = new JobVariableDTO();
            jobVariable.setJobId(3L);
            jobVariable.setK("TABLE_NAME");
            jobVariable.setV("table" + i);
            jobVariable.setDescription("变量" + i);
            jobVariableRepository.save(jobVariable);
        }
    }

}
