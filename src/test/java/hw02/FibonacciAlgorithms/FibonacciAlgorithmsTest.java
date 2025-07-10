package hw02.FibonacciAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FibonacciAlgorithmsTest {

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();

        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static void runAndMeasure(String testCase, Runnable task) {
        System.gc();
        long beforeRemoveMemory = getUsedMemory();
        long startTime = System.nanoTime();

//        for (int i = 0; i < 50; i++) {
        task.run();
//        }

        long endTime = System.nanoTime();
        long afterRemoveMemory = getUsedMemory();

        double memory = (afterRemoveMemory - beforeRemoveMemory) / 1024.0 / 1024.0;
        double time = (endTime - startTime) / 1000000.0;

        System.out.printf(testCase + " | %7.2f ms | %6.2f Mb%n", time, memory);
    }

    @Test
    public void testCorrectnessValue() {
        List<Long> expectedList = List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L,
                377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L,
                317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L, 9227465L);

        List<Long> recursiveList = new ArrayList<>();
        List<Long> memoList = new ArrayList<>();
        List<Long> iterativeList = new ArrayList<>();

        for (int i = 0; i <= 35; i++) {
            recursiveList.add(FibonacciAlgorithms.fibonacciRecursive(i));
            memoList.add(FibonacciAlgorithms.fibonacciMemoized(i, new HashMap<>()));
            iterativeList.add(FibonacciAlgorithms.fibonacciIterative(i));
        }

        Assertions.assertEquals(expectedList, recursiveList);
        Assertions.assertEquals(expectedList, memoList);
        Assertions.assertEquals(expectedList, iterativeList);
    }

    @ParameterizedTest(name = "n = {0}")
    @ValueSource(ints = {10, 20, 30, 35, 40, 45})
    public void testPerformanceBenchmarkAllAlgoritms(int n) {
        System.out.print("n = " + n + " | ");
        runAndMeasure("Recursive", () -> FibonacciAlgorithms.fibonacciRecursive(n));
        System.out.print("n = " + n + " | ");
        runAndMeasure("Memo     ", () -> FibonacciAlgorithms.fibonacciMemoized(n, new HashMap<>()));
        System.out.print("n = " + n + " | ");
        runAndMeasure("Iterative", () -> FibonacciAlgorithms.fibonacciIterative(n));
        System.out.println("------------------------------------------");
    }

    @ParameterizedTest(name = "n = {0}")
    @ValueSource(ints = {10000, 20000, 40000, 80000, 160000, 320000, 640000, 1280000, 2560000})
    public void testPerformanceBenchmarkMemoAndIterative(int n) {
        System.out.printf("n = %-8d", n);
        runAndMeasure("| Memo     ", () -> FibonacciAlgorithms.fibonacciMemoized(n, new HashMap<>()));
        System.out.printf("n = %-8d", n);
        runAndMeasure("| Iterative", () -> FibonacciAlgorithms.fibonacciIterative(n));
        System.out.println("------------------------------------------------");

    }
}
