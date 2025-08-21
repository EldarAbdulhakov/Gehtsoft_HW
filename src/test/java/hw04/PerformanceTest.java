package hw04;

import hw04.CustomHashMap.CustomHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.function.IntConsumer;

public class PerformanceTest {

    private static final int ITERATIONS = 1_000_000;
    private CustomHashMap<Integer, Integer> customHashMap;
    private HashMap<Integer, Integer> hashMap;

    private void runAndMeasure(String method, IntConsumer task1, IntConsumer task2, int iterations) {
        System.gc();
        long startMemory1 = getUsedMemory();
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            task1.accept(i);
        }
        long endTime1 = System.currentTimeMillis();
        long time1 = endTime1 - startTime1;
        long endMemory1 = getUsedMemory();
        double memory1 = (endMemory1 - startMemory1) / 1024.0 / 1024.0;

        System.gc();
        long startMemory2 = getUsedMemory();
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            task2.accept(i);
        }
        long endTime2 = System.currentTimeMillis();
        long time2 = endTime2 - startTime2;
        long endMemory2 = getUsedMemory();
        double memory2 = (endMemory2 - startMemory2) / 1024.0 / 1024.0;

        System.out.printf("%-6s %-8d | %10d ms | %5d ms || %10.2f mb | %6.2f mb |%n", method, iterations, time1, time2, memory1, memory2);
    }

    private long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();

        return runtime.totalMemory() - runtime.freeMemory();
    }

    private void prepareData(int iterations) {
        for (int j = 0; j < iterations; j++) {
            customHashMap.put(j, j);
            hashMap.put(j, j);
        }
    }

    @BeforeEach
    void setUp() {
        customHashMap = new CustomHashMap<>();
        hashMap = new HashMap<>();
    }

    @Test
    public void testPutPerformance() {
        runAndMeasure("Put", key -> customHashMap.put(key, key), key -> hashMap.put(key, key), ITERATIONS);
    }

    @Test
    public void testGetPerformance() {
        prepareData(ITERATIONS);
        runAndMeasure("Get", key -> customHashMap.get(key), key -> hashMap.get(key), ITERATIONS);
    }

    @Test
    public void testRemovePerformance() {
        prepareData(ITERATIONS);
        runAndMeasure("Remove", key -> customHashMap.remove(key), key -> hashMap.remove(key), ITERATIONS);
    }

    @ParameterizedTest()
    @ValueSource(ints = {10_000, 100_000, 1_000_000, 10_000_000})
    public void testMapsPerformance(Integer iterations) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-17s %-16s %-9s %-17s %-9s%n", "Iterations      |", "CustomHashMap |", "HashMap ||", "CustomHashMap |", "HashMap |");

        runAndMeasure("Put", key -> customHashMap.put(key, key), key -> hashMap.put(key, key), iterations);

        prepareData(iterations);
        runAndMeasure("Get", key -> customHashMap.get(key), key -> hashMap.get(key), iterations);

        prepareData(iterations);
        runAndMeasure("Remove", key -> customHashMap.remove(key), key -> hashMap.remove(key), iterations);
    }
}
