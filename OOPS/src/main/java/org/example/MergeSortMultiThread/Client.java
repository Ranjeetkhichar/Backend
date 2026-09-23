package org.example.MergeSortMultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {


    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        MergeSort ms = new MergeSort(list, executorService);
        Future<List<Integer>> listFuture = executorService.submit(ms);
        try {
            List<Integer> sortedList = listFuture.get();
            System.out.println(sortedList);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
