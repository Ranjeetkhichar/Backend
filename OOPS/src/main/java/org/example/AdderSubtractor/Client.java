package org.example.AdderSubtractor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    public static void main() {

        Value val = new Value(0);
//        ExecutorService executor = Executors.newFixedThreadPool(2);
        Lock lock = new ReentrantLock();
        Adder adder = new Adder(val, lock);
        Substractor substractor = new Substractor(val, lock);


        Thread adderThread = new Thread(adder);
        Thread subsThread = new Thread(substractor);

        adderThread.start();
        subsThread.start();

        System.out.println("Final integer value: " + val.value);
    }
}
