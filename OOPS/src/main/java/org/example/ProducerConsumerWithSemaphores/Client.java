package org.example.ProducerConsumerWithSemaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Client {
    public static void main(String[] args) {
        Store store = new Store(5);
        ExecutorService executor = Executors.newCachedThreadPool();

        Semaphore prodCount = new Semaphore(5);
        Semaphore consCount = new Semaphore(0);

        for(int i = 0; i < 10; i++){
            executor.execute(new Producer(store, prodCount, consCount));
        }
        for(int i = 0; i < 10; i++){
            executor.execute(new Consumer(store, prodCount, consCount));
        }

    }
}
