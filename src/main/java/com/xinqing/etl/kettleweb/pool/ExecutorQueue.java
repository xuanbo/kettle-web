package com.xinqing.etl.kettleweb.pool;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.RejectedExecutionException;

/**
 * LinkedTransferQueue 能保证更高性能，相比与LinkedBlockingQueue有明显提升
 * <p>
 * 1) 不过LinkedTransferQueue的缺点是没有队列长度控制，需要在外层协助控制
 * <p>
 * 代码来自motan rpc
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class ExecutorQueue extends LinkedTransferQueue<Runnable> {

    private StandardThreadExecutor threadPoolExecutor;

    public ExecutorQueue() {
        super();
    }

    public void setStandardThreadExecutor(StandardThreadExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    /**
     * 注：代码来源于 tomcat
     *
     * @param runnable Runnable
     * @return
     */
    public boolean force(Runnable runnable) {
        if (threadPoolExecutor.isShutdown()) {
            throw new RejectedExecutionException("Executor not running, can't force a command into the queue");
        }
        // forces the item onto the queue, to be used if the task is rejected
        return super.offer(runnable);
    }

    /**
     * 注：tomcat的代码进行一些小变更
     * 在提交的任务数超过poolSize, 而poolSize小于最大线程数的时候返回false, 让executor创建线程
     *
     * @param runnable Runnable
     * @return
     */
    @Override
    public boolean offer(Runnable runnable) {
        int poolSize = threadPoolExecutor.getPoolSize();

        // we are maxed out on threads, simply queue the object
        if (poolSize == threadPoolExecutor.getMaximumPoolSize()) {
            return super.offer(runnable);
        }
        // we have idle threads, just add it to the queue
        // note that we don't use getActiveCount(), see BZ 49730
        if (threadPoolExecutor.getSubmittedTasksCount() <= poolSize) {
            return super.offer(runnable);
        }
        // if we have less threads than maximum force creation of a new thread
        if (poolSize < threadPoolExecutor.getMaximumPoolSize()) {
            return false;
        }
        // if we reached here, we need to add it to the queue
        return super.offer(runnable);
    }

}
