package org.example.ProducerConsumerWithSemaphores;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private Store store;
    private Semaphore prodCount;
    private Semaphore consCount;
    private CyclicBarrier barrier = new CyclicBarrier(3);

    public Consumer(Store store, Semaphore prodCount, Semaphore consCount) {
        this.store = store;
        this.prodCount = prodCount;
        this.consCount = consCount;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consCount.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            store.removeItem();
            prodCount.release();
        }
    }
}
