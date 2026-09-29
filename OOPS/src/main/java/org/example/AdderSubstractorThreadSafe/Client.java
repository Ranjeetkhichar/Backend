package org.example.AdderSubstractorThreadSafe;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main() throws ExecutionException, InterruptedException {

        Value val = new Value(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Adder adder = new Adder(val);
        Substractor substractor = new Substractor(val);

        Future<Integer> adderFuture = executor.submit(adder);
        Integer x = adderFuture.get();
        Future<Integer> subtFuture = executor.submit(substractor);
        x = subtFuture.get();
        System.out.println("Final integer value: " + val.value);

    }
}
