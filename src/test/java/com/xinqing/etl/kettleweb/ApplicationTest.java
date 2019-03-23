package com.xinqing.etl.kettleweb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryListener;
import org.pentaho.di.job.JobListener;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 奔波儿灞
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTest.class);

    private ExecutorService executor;

    @Before
    public void setup() throws KettleException {
        executor = Executors.newFixedThreadPool(10);
        // 初始化kettle运行环境
        KettleEnvironment.init();
    }

    @After
    public void cleanup() {
        executor.shutdown();
    }

    @Test
    public void runKjb() {
        final CountDownLatch latch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    doRunKjb();
                } catch (KettleXMLException e) {
                    LOG.error("kettle exec error", e);
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            LOG.error("interrupted error", e);
        }
    }

    private void doRunKjb() throws KettleXMLException {
        // 创建job
        JobMeta meta = new JobMeta("/Users/xuanbo/Projects/kettle-web/src/test/resources/demo.kjb", null);
        Job job = new Job(null, meta);
        // 日志
        job.setLogLevel(LogLevel.ROWLEVEL);
        // 设置变量
        // job.setParameterValue("TABLE_NAME", "FND_COMPANY_B");
        job.setVariable("TABLE_NAME", "FND_COMPANY_B");
        // 运行job
        job.start();
        job.addJobListener(new JobListener() {
            @Override
            public void jobFinished(Job job) throws KettleException {
                Result result = job.getResult();
                LOG.info("end job[{}], log: {}", job.getName(), result.getLogText());
            }

            @Override
            public void jobStarted(Job job) throws KettleException {
                LOG.info("start job[{}]", job.getName());
            }
        });
        job.addJobEntryListener(new JobEntryListener() {
            @Override
            public void beforeExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface) {
                LOG.info("start entry[{}]", jobEntryInterface.getName());
            }

            @Override
            public void afterExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface, Result result) {
                LOG.info("end entry[{}], log: {}", jobEntryInterface.getName(), result.getLogText());
            }
        });
        job.waitUntilFinished();
    }

}
