package org.example.MergeSortMultiThread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSort implements Callable<List<Integer>> {

    public List<Integer> arr;
    public ExecutorService es;
    public MergeSort(List<Integer> arr, ExecutorService executor) {
        this.arr = arr;
        this.es = es;
    }

    @Override
    public List<Integer> call() throws Exception {
        if(arr.size() < 2) {
            return arr;
        }
        int mid = arr.size() / 2;
        List<Integer> left = new ArrayList<>(arr.subList(0, mid));
        List<Integer> right = new ArrayList<>(arr.subList(mid + 1, arr.size()));

        Future<List<Integer>> leftSortedFuture = es.submit(new MergeSort(left, es));
        Future<List<Integer>> rightSortedFuture = es.submit(new MergeSort(right, es));

        List<Integer> leftSorted = leftSortedFuture.get();
        List<Integer> rightSorted = rightSortedFuture.get();
        return mergeSortedArray(leftSorted, rightSorted);
    }
    public List<Integer> mergeSortedArray(List<Integer> leftSorted, List<Integer> rightSorted) {
        List<Integer> result = new ArrayList<>(leftSorted.size() + rightSorted.size());
        for(int i = 0; i < leftSorted.size(); i++) {
            result.add(leftSorted.get(i));
        }
        for(int i = 0; i < rightSorted.size(); i++) {
            result.add(rightSorted.get(i));
        }
        return result;
    }
}
