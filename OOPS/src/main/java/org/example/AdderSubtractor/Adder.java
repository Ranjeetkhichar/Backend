package org.example.AdderSubtractor;

import java.util.Hashtable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

//public class Adder implements Callable<Void> {
public class Adder implements Runnable {

    private Value val;
    private Lock lock;

    public Adder(Value val, Lock lock) {
        this.val = val;
        this.lock = lock;
    }

//    @Override
//    public void call() {
//        for(int i = 1 ; i <= 1000000 ; i++){
//            val.value += i;
//        }
//        return  null;
//    }

    @Override
    public void run() {
        for(int i = 1 ; i <= 1000000 ; i++){
            synchronized (val){
                val.value += i;
            }
//            lock.lock();

//            lock.unlock();
        }
    }
}
