package com.xinqing.etl.kettleweb.service;

import com.xinqing.etl.kettleweb.dto.ScheduleJobDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 计划任务服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleJobServiceTest {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Test
    public void add() {
        ScheduleJobDTO scheduleJob = new ScheduleJobDTO();
        scheduleJob.setJobId(273L);
        // 每分钟运行一次
        scheduleJob.setCron("0 */1 * * * ?");
        scheduleJob.setBeanName("defaultJob");
        scheduleJob.setDescription("每分钟运行一次");
        scheduleJobService.save(scheduleJob);
    }

    @Test
    public void delete() {
        scheduleJobService.delete(464L);
    }
}
