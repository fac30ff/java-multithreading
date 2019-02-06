package com.github.fac30ff.java.multithreading.demo1;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection connection = new Connection();
    private int connections = 0;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {

    }

    public void connect() throws InterruptedException {
        semaphore.acquire();
        try {
            doConnect();
        } finally {
            semaphore.release();
        }

    }

    public static Connection getInstance() {
        return connection;
    }

    private void doConnect() throws InterruptedException {
        semaphore.acquire();
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }
        Thread.sleep(2000);
        synchronized (this) {
            connections--;
        }
    }
}
