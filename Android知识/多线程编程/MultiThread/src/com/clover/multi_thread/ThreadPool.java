package com.clover.multi_thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

    public static void main(String[] args) {
        fixedThreadPool();
        cacheThreadPool();
        singleThreadExecutor();
        scheduledThreadPool();
        
    }

    private static void scheduledThreadPool() {
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
    }

    private static void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    private static void cacheThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

    private static void fixedThreadPool() {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

    }


}
