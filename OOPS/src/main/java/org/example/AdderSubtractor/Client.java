package org.example.AdderSubtractor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main() {

        Value val = new Value(0);
//        ExecutorService executor = Executors.newFixedThreadPool(2);
        Adder adder = new Adder(val);
        Substractor substractor = new Substractor(val);

        Thread adderThread = new Thread(adder);
        Thread subsThread = new Thread(substractor);

        adderThread.start();
        subsThread.start();

        System.out.println("Final integer value: " + val.value);
    }
}
