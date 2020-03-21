package com.yang.code.util.java8.lambda;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * https://time.geekbang.org/column/article/212374
 * <p>
 * Created by yangguojun01 on 2020/3/21.
 */
public class ParallelDemo {

    public static void parallel() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println(LocalDateTime.now() + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }
        });
    }

    private static void increment(AtomicInteger atomicInteger) {
        atomicInteger.incrementAndGet();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    // 使用 20 个线程（threadCount）以并行方式总计执行 10000 次（taskCount）操作，5种方式实现
    // 1.CountDownLatch
    private static int thread(int taskCount, int threadCount) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        IntStream.rangeClosed(1, threadCount).mapToObj(i -> new Thread(() -> {
            IntStream.rangeClosed(1, taskCount / threadCount).forEach(j -> increment(atomicInteger));
            countDownLatch.countDown();
        })).forEach(Thread::start);
        countDownLatch.await();
        return atomicInteger.get();
    }

    // 2.Executors.newFixedThreadPool
    private static int threadpool(int taskCount, int threadCount) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        IntStream.rangeClosed(1, taskCount).forEach(j -> increment(atomicInteger));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        return atomicInteger.get();
    }

    // 3.ForkJoinPool ForkJoinPool 和传统的 ThreadPoolExecutor 区别在于，
    // 前者对于 n 并行度有 n 个独立队列，后者是共享队列。
    // 如果有大量执行耗时比较短的任务，ThreadPoolExecutor 的单队列就可能会成为瓶颈，使用 ForkJoinPool 性能会更好。
    private static int forkjoin(int taskCount, int threadCount) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        forkJoinPool
                .execute(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return atomicInteger.get();
    }

    // 4.并行流使用公共的 ForkJoinPool，也就是 ForkJoinPool.commonPool()
    private static int stream(int taskCount, int threadCount) throws InterruptedException {
        //设置公共ForkJoinPool并行度，默认的并行度是 CPU 核心数 -1
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(threadCount));
        AtomicInteger atomicInteger = new AtomicInteger();
        IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger));
        return atomicInteger.get();
    }

    // 5.CompletableFuture
    private static int completableFuture(int taskCount, int threadCount) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        CompletableFuture
                .runAsync(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)),
                        forkJoinPool).get();
        return atomicInteger.get();
    }

    // 方式2、方式4最常用
    public static void main(String[] args) throws Exception {
        //        parallel();
        System.out.println(LocalDateTime.now() + ":1");
        int ret1 = thread(1000, 20);
        System.out.println(LocalDateTime.now() + ":1");
        System.out.println(LocalDateTime.now() + " ret1:" + ret1);
        System.out.println(LocalDateTime.now() + ":2");
        int ret2 = threadpool(1000, 20);
        System.out.println(LocalDateTime.now() + ":2");
        System.out.println(LocalDateTime.now() + " ret2:" + ret2);
        System.out.println(LocalDateTime.now() + ":3");
        int ret3 = forkjoin(1000, 20);
        System.out.println(LocalDateTime.now() + ":3");
        System.out.println(LocalDateTime.now() + " ret3:" + ret3);
        System.out.println(LocalDateTime.now() + ":4");
        int ret4 = stream(1000, 20);
        System.out.println(LocalDateTime.now() + ":4");
        System.out.println(LocalDateTime.now() + " ret4:" + ret4);
        System.out.println(LocalDateTime.now() + ":5");
        int ret5 = completableFuture(1000, 20);
        System.out.println(LocalDateTime.now() + ":5");
        System.out.println(LocalDateTime.now() + " ret5:" + ret5);

    }
}
