package com.xinqing.etl.kettleweb.pool;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 优先启动线程
 *
 * 代码来自motan rpc
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class StandardThreadExecutor extends ThreadPoolExecutor {

    public static final int DEFAULT_MIN_THREADS = 20;

    public static final int DEFAULT_MAX_THREADS = 200;

    // 1 minutes
    public static final int DEFAULT_MAX_IDLE_TIME = 60 * 1000;

    // 正在处理的任务数
    private AtomicInteger submittedTasksCount;

    // 最大允许同时处理的任务数
    private int maxSubmittedTaskCount;

    public StandardThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit,
                                  int queueCapacity, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(coreThreads, maxThreads, keepAliveTime, unit, new ExecutorQueue(), threadFactory, handler);
        ((ExecutorQueue) getQueue()).setStandardThreadExecutor(this);

        submittedTasksCount = new AtomicInteger(0);

        // 最大并发任务限制： 队列buffer数 + 最大线程数
        maxSubmittedTaskCount = queueCapacity + maxThreads;
    }

    public void execute(Runnable command) {
        int count = submittedTasksCount.incrementAndGet();

        // 超过最大的并发任务限制，进行 reject
        // 依赖的LinkedTransferQueue没有长度限制，因此这里进行控制
        if (count > maxSubmittedTaskCount) {
            submittedTasksCount.decrementAndGet();
            getRejectedExecutionHandler().rejectedExecution(command, this);
        }

        try {
            super.execute(command);
        } catch (RejectedExecutionException rx) {
            // there could have been contention around the queue
            if (!((ExecutorQueue) getQueue()).force(command)) {
                submittedTasksCount.decrementAndGet();

                getRejectedExecutionHandler().rejectedExecution(command, this);
            }
        }
    }

    public int getSubmittedTasksCount() {
        return this.submittedTasksCount.get();
    }

    public int getMaxSubmittedTaskCount() {
        return maxSubmittedTaskCount;
    }

    protected void afterExecute(Runnable r, Throwable t) {
        submittedTasksCount.decrementAndGet();
    }

}
