package org.example.AdderSubtractor;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

//public class Substractor implements Callable<Void> {
public class Substractor implements Runnable {

    private Value val;
    private Lock lock;
    public Substractor(Value val, Lock lock) {
        this.val = val;
        this.lock = lock;
    }

//    @Override
    public Void call() {
        for(int i = 1 ; i <= 1000000 ; i++){
            val.value -= i;
        }
        return null;
    }

    @Override
    public void run() {
        for(int i = 1 ; i <= 1000000 ; i++){
            lock.lock();
            val.value -= i;
            lock.unlock();
        }
    }
}
