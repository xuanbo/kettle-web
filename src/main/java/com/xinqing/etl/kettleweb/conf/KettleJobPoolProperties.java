package com.xinqing.etl.kettleweb.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * kettle任务线程池配置
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@ConfigurationProperties(prefix = "kettle.pool")
public class KettleJobPoolProperties implements Serializable {

    /**
     * 线程池前缀
     */
    private String namePrefix = "kettleJobPool";

    /**
     * 核心线程数
     */
    private int coreThreads = 20;

    /**
     * 最大的线程数
     */
    private int maxThreads = 50;

    /**
     * 队列容量
     */
    private int queueCapacity = 100;

    /**
     * 空闲x分钟则释放线程
     */
    private long keepAliveTimeMin = 5;

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public int getCoreThreads() {
        return coreThreads;
    }

    public void setCoreThreads(int coreThreads) {
        this.coreThreads = coreThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public long getKeepAliveTimeMin() {
        return keepAliveTimeMin;
    }

    public void setKeepAliveTimeMin(long keepAliveTimeMin) {
        this.keepAliveTimeMin = keepAliveTimeMin;
    }
}
