package org.example;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Main {

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        try (Stream<? extends T> stream_ = stream) {
            Optional<? extends T> min = stream_.min(order);
            Optional<? extends T> max = stream_.max(order);
            if(min.isPresent() && max.isPresent()){
                minMaxConsumer.accept(min.get(), max.get());
            } else {
                minMaxConsumer.accept(null, null);
            }
        }
    }

    public static void printEvenNumbers(List<Integer> numbers) {
        long evenCount = numbers.stream()
                .filter(i -> i % 2 == 0)
                .count();
        System.out.println("Number of even numbers: " + evenCount);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 13, 21);
        try (Stream<Integer> stream = list.stream()) {
            findMinMax(stream, Integer::compareTo, (x, y) -> System.out.printf("min: %s, max: %s%n", x, y));
        }
        printEvenNumbers(new ArrayList<>(list));
    }
}
