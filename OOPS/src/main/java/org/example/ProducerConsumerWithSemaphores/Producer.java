package org.example.ProducerConsumerWithSemaphores;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    Store store;
    Semaphore prodCount;
    Semaphore consCount;

    public Producer(Store store, Semaphore prodCount, Semaphore consCount) {
        this.store = store;
        this.prodCount = prodCount;
        this.consCount = consCount;
    }

    @Override
    public void run() {
        while (true) {
            try {
                prodCount.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            store.addItem(new Object());
            consCount.release();
        }
    }
}
