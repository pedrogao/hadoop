package org.apache.hadoop.util;

/**
 * A thread that has called {@link Thread#setDaemon(boolean) } with true.
 * 守护线程
 */
public class Daemon extends Thread {

    // 实例化后调用
    {
        setDaemon(true);                              // always a daemon
    }

    Runnable runnable = null;

    /**
     * Construct a daemon thread.
     */
    public Daemon() {
        super();
    }

    /**
     * Construct a daemon thread.
     */
    public Daemon(Runnable runnable) {
        super(runnable);
        this.runnable = runnable;
        this.setName(((Object) runnable).toString());
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
