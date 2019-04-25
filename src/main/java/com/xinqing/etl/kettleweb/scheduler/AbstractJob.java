package com.xinqing.etl.kettleweb.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * quartz任务执行基类
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public abstract class AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void execute(Long jobId) {
        logger.info("begin execute task");
        try {
            doExecute(jobId);
        } catch (Exception e) {
            logger.error("job execute error", e);
        }
        logger.info("execute task end");
    }

    /**
     * 执行任务
     *
     * @param jobId job id
     */
    protected abstract void doExecute(Long jobId);

}
