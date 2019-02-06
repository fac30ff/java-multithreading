package com.github.fac30ff.java.multithreading.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1); // with 1 param like a lock
        sem.acquire();
        sem.release();
        System.out.println("Available permits: " + sem.availablePermits());

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            service.submit(() -> {
                try {
                    Connection.getInstance().connect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
    }
}
