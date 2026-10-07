package org.example.Streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class Client{
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // What does the following code snippet do?
        int sum = numbers.stream()
                .map(n -> n * n)
                .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6);

        list.stream().forEach((x) -> {
            System.out.print(x + " ");
        });

        System.out.println();

        List<Integer> list2 =
                list.stream().
//                        distinct().
        filter(x -> x % 2 == 0).
                        toList();
        list2.stream().forEach((x) -> {
            System.out.print(x + " ");
        });

        System.out.println();


        List<List<Integer>> doubleList = new ArrayList<>();
        doubleList.add(List.of(1, 2, 3));
        doubleList.add(List.of(3, 3, 5));
        doubleList.add(List.of(6, 6, 8));

        List<Integer> singleList = doubleList.
                stream().
                flatMap((l1) -> {
                    return l1.stream();
                }).toList().stream().distinct().toList();

        singleList.stream().forEach((x) -> {
            System.out.print(x + " ");
        });

        list.stream().forEach((x) -> {
            System.out.print(x + " ");
        });

        System.out.println();
        List<Integer> first = list
                .stream()
                .filter(x -> {
                    System.out.println("Filer x -> " + x);
                    return x % 2 == 0;
                })
                .map(x -> {
                    System.out.println("Square -> " + x);
                    return x * x;
                })
                .map((x -> x % 5)).toList();

        System.out.println(list);
        System.out.print(first);

        System.out.print(list.stream().
                reduce(0, (a, b) -> a + b));

        System.out.println(list.stream()
//                        .filter(x -> x % 2 == 0)
                        .reduce(1, (prod, num) -> prod * num));



    }
    public static <T extends Comparable> int compare(T a, T b){
        return a.compareTo(b);
    }

    Stream<Integer> stream = new Stream<Integer>() {
        @Override
        public Stream<Integer> filter(Predicate<? super Integer> predicate) {
            return Stream.empty();
        }

        @Override
        public <R> Stream<R> map(Function<? super Integer, ? extends R> mapper) {
            return Stream.empty();
        }

        @Override
        public IntStream mapToInt(ToIntFunction<? super Integer> mapper) {
            return IntStream.empty();
        }

        @Override
        public LongStream mapToLong(ToLongFunction<? super Integer> mapper) {
            return LongStream.empty();
        }

        @Override
        public DoubleStream mapToDouble(ToDoubleFunction<? super Integer> mapper) {
            return DoubleStream.empty();
        }

        @Override
        public <R> Stream<R> flatMap(Function<? super Integer, ? extends Stream<? extends R>> mapper) {
            return Stream.empty();
        }

        @Override
        public IntStream flatMapToInt(Function<? super Integer, ? extends IntStream> mapper) {
            return IntStream.empty();
        }

        @Override
        public LongStream flatMapToLong(Function<? super Integer, ? extends LongStream> mapper) {
            return LongStream.empty();
        }

        @Override
        public DoubleStream flatMapToDouble(Function<? super Integer, ? extends DoubleStream> mapper) {
            return DoubleStream.empty();
        }

        @Override
        public Stream<Integer> distinct() {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> sorted() {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> sorted(Comparator<? super Integer> comparator) {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> peek(Consumer<? super Integer> action) {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> limit(long maxSize) {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> skip(long n) {
            return Stream.empty();
        }

        @Override
        public void forEach(Consumer<? super Integer> action) {

        }

        @Override
        public void forEachOrdered(Consumer<? super Integer> action) {

        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <A> A[] toArray(IntFunction<A[]> generator) {
            return null;
        }

        @Override
        public Integer reduce(Integer identity, BinaryOperator<Integer> accumulator) {
            return 0;
        }

        @Override
        public Optional<Integer> reduce(BinaryOperator<Integer> accumulator) {
            return Optional.empty();
        }

        @Override
        public <U> U reduce(U identity, BiFunction<U, ? super Integer, U> accumulator, BinaryOperator<U> combiner) {
            return null;
        }

        @Override
        public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super Integer> accumulator, BiConsumer<R, R> combiner) {
            return null;
        }

        @Override
        public <R, A> R collect(Collector<? super Integer, A, R> collector) {
            return null;
        }

        @Override
        public Optional<Integer> min(Comparator<? super Integer> comparator) {
            return Optional.empty();
        }

        @Override
        public Optional<Integer> max(Comparator<? super Integer> comparator) {
            return Optional.empty();
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public boolean anyMatch(Predicate<? super Integer> predicate) {
            return false;
        }

        @Override
        public boolean allMatch(Predicate<? super Integer> predicate) {
            return false;
        }

        @Override
        public boolean noneMatch(Predicate<? super Integer> predicate) {
            return false;
        }

        @Override
        public Optional<Integer> findFirst() {
            return Optional.empty();
        }

        @Override
        public Optional<Integer> findAny() {
            return Optional.empty();
        }

        @Override
        public Iterator<Integer> iterator() {
            return null;
        }

        @Override
        public Spliterator<Integer> spliterator() {
            return null;
        }

        @Override
        public boolean isParallel() {
            return false;
        }

        @Override
        public Stream<Integer> sequential() {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> parallel() {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> unordered() {
            return Stream.empty();
        }

        @Override
        public Stream<Integer> onClose(Runnable closeHandler) {
            return Stream.empty();
        }

        @Override
        public void close() {

        }
    };

}
