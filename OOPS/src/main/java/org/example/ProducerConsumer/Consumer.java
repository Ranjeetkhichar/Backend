package org.example.ProducerConsumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {

    private Store store;
    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
//        Lock lock = new ReentrantLock(); => Same lock must required
        while (true) {
//            lock.lock();
            synchronized (store) {
                if(store.getItems().size() > 0){
                    store.removeItem();
                }
            }

//            lock.unlock();
        }
    }
}
