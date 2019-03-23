package com.xinqing.etl.kettleweb.job;

import com.xinqing.etl.kettleweb.domain.JobParam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务提交
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobSubmitterTest {

    private static final Logger LOG = LoggerFactory.getLogger(JobSubmitterTest.class);

    @Autowired
    private JobSubmitter submitter;

    private JobParam param;

    @Before
    public void setup() {
        param = new JobParam();
        param.setPath("demo.kjb");
        Map<String, String> variables = new HashMap<>();
        variables.put("TABLE_NAME", "FND_COMPANY_B");
        param.setVariables(variables);
    }

    @Test
    public void submit() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            submitter.submit(param);
        }
        Thread.sleep(3000L);
        for (; submitter.isActive(); ) {
            // wait
            Thread.sleep(100);
        }
    }

}
