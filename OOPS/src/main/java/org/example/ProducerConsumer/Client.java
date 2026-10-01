package org.example.ProducerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        Store store = new Store(5);
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 100; i++){
            executor.execute(new Producer(store));
        }
        for(int i = 0; i < 100; i++){
            executor.execute(new Consumer(store));
        }

    }
}
