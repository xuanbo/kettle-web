package com.xinqing.etl.kettleweb.conf;

import com.xinqing.etl.kettleweb.pool.DefaultThreadFactory;
import com.xinqing.etl.kettleweb.pool.StandardThreadExecutor;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * kettle配置
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(KettleJobPoolProperties.class)
public class KettleConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(KettleConfiguration.class);

    /**
     * 初始化kettle运行环境
     */
    @PostConstruct
    public void setup() {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            LOG.error("init kettle env error", e);
            System.exit(-1);
        }
    }

    /**
     * 销毁kettle运行环境
     */
    @PreDestroy
    public void cleanup() {
        KettleEnvironment.shutdown();
    }

    /**
     * 配置kettle任务运行线程池
     *
     * @param properties KettleJobPoolProperties
     * @return StandardThreadExecutor
     */
    @Bean
    public StandardThreadExecutor kettleJobExecutor(KettleJobPoolProperties properties) {
        return new StandardThreadExecutor(
                properties.getCoreThreads(), properties.getMaxThreads(),
                properties.getKeepAliveTimeMin(), TimeUnit.MINUTES,
                properties.getQueueCapacity(), new DefaultThreadFactory(properties.getNamePrefix()),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
