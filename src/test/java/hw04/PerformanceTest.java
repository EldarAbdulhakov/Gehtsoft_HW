package hw04;

import hw04.CustomHashMap.CustomHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PerformanceTest {

    private static final int ITERATIONS = 10_000_000;
    private Integer i;
    private CustomHashMap<Integer, Integer> customHashMap = new CustomHashMap<>();
    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    public void runAndMeasure(Runnable task1, Runnable task2) {
        long startTime1 = System.currentTimeMillis();
        for (i = 0; i < ITERATIONS; i++) {
            task1.run();
        }
        task1.run();
        long endTime1 = System.currentTimeMillis();
        long time1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();
        for (i = 0; i < ITERATIONS; i++) {
            task2.run();
        }
        task2.run();
        long endTime2 = System.currentTimeMillis();
        long time2 = endTime2 - startTime2;

        System.out.printf(" %-15d ms| %-15d ms%n", time1, time2);
    }

    @Test
    public void testPutPerformance1() {
        runAndMeasure(() -> customHashMap.put(i, i), () ->hashMap.put(i, i));
    }

    @Test
    public void testPutPerformance2() {
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }
        long endTime1 = System.currentTimeMillis();
        long time1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }
        long endTime2 = System.currentTimeMillis();
        long time2 = endTime2 - startTime2;

        System.out.println("customHashMap = " + time1  + " ms, " + "hashMap = " + time2 + " ms");
    }

    @Test
    public void testGetPerformance1() {
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }
        for (i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }

        runAndMeasure(() -> customHashMap.get(i), () -> hashMap.get(i));
    }

    @Test
    public void testGetPerformance2() {
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }

        for (i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }

        long startTime1 = System.currentTimeMillis();
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.get(i);
        }
        long endTime1 = System.currentTimeMillis();
        long time1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();
        for (i = 0; i < ITERATIONS; i++) {
            hashMap.get(i);
        }
        long endTime2 = System.currentTimeMillis();
        long time2 = endTime2 - startTime2;

        System.out.println("customHashMap = " + time1  + " ms, " + "hashMap = " + time2 + " ms");
    }

    @Test
    public void testRemovePerformance1() {
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }
        for (i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }

        runAndMeasure(() -> customHashMap.remove(i), () -> hashMap.remove(i));
    }

    @Test
    public void testRemovePerformance() {
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }

        for (i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }

        long startTime1 = System.currentTimeMillis();
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.remove(i);
        }
        long endTime1 = System.currentTimeMillis();
        long time1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();
        for (i = 0; i < ITERATIONS; i++) {
            hashMap.remove(i);
        }
        long endTime2 = System.currentTimeMillis();
        long time2 = endTime2 - startTime2;

        System.out.println("customHashMap = " + time1  + " ms, " + "hashMap = " + time2 + " ms");
    }

//    @ParameterizedTest
//    public void testMapsPerformance() {
//
//    }


//    @Test
//    public void testMapsPerformance() {
//        System.out.println("Maps Performance Test\n");
//        System.out.printf("%-12s %-10s %-16s %-18s%n", "Array size |", "Shift    |", "ArrayCopy (ms) |", "Manual Loop (ms) |");
//        System.out.println("---------------------------------------------------------");
//
//                System.out.printf("%-11d| %-9d|", size, shift);
//                runAndMeasure(() -> ArrayOperations.shiftLeftSystemCopy(arraySystemCopy, shift),
//                        () -> ArrayOperations.shiftLeftManualLoop(arrayManualLoop, shift));
//
//                System.out.println("---------------------------------------------------------");
//
//
//    }
}
