package com.example.pokemonapitest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int KEEP_ALIVE = 120;

    private static ThreadExecutor instance;
    private ThreadPoolExecutor executor;

    public static ThreadExecutor getInstance() {
        if (instance == null) {
            instance = new ThreadExecutor();
        }
        return instance;
    }

    private ThreadExecutor() {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
    }

    public void execute(Runnable cmd) { executor.execute(cmd); }
}
