package org.example.ProducerConsumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {

    Store store;
    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
//        Lock lock = new ReentrantLock();
        while (true) {
//            lock.lock();
            synchronized (store) {
                if(store.getItems().size() < store.getMaxItems()){
                    store.addItem(new Object());
                }
            }

//            lock.unlock();
        }
    }
}
