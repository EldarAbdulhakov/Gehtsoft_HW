package hw04;

import hw04.CustomHashMap.CustomHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

public class PerformanceTest {

    private static final int ITERATIONS = 1_000_000;
    private int i;
    private CustomHashMap<Integer, Integer> customHashMap = new CustomHashMap<>();
    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    private void runAndMeasure(String method,Runnable task1, Runnable task2, int iterations) {
        long startTime1 = System.currentTimeMillis();
        for (i = 0; i < iterations; i++) {
            task1.run();
        }
        long endTime1 = System.currentTimeMillis();
        long time1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();
        for (i = 0; i < iterations; i++) {
            task2.run();
        }
        long endTime2 = System.currentTimeMillis();
        long time2 = endTime2 - startTime2;

        System.out.printf("%-6s %-8d | %10d ms | %5d ms |%n",method, i, time1, time2);
    }

    @Test
    public void testPutPerformance() {
        runAndMeasure("Put", () -> customHashMap.put(i, i), () -> hashMap.put(i, i), ITERATIONS);
    }

    @Test
    public void testGetPerformance() {
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }
        for (i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }

        runAndMeasure("Get", () -> customHashMap.get(i), () -> hashMap.get(i), ITERATIONS);
    }

    @Test
    public void testRemovePerformance() {
        for (i = 0; i < ITERATIONS; i++) {
            customHashMap.put(i, i);
        }
        for (i = 0; i < ITERATIONS; i++) {
            hashMap.put(i, i);
        }

        runAndMeasure("Remove", () -> customHashMap.remove(i), () -> hashMap.remove(i), ITERATIONS);
    }

    @ParameterizedTest()
    @ValueSource(ints = {10_000, 100_000, 1_000_000, 10_000_000})
    public void testMapsPerformance(Integer iterations) {
        System.out.println("--------------------------------------------");
        System.out.printf("%-17s %-16s %-18s%n", "Iterations      |", "CustomHashMap |", "HashMap |");
        runAndMeasure("Put", () -> customHashMap.put(i, i), () -> hashMap.put(i, i), iterations);
        runAndMeasure("Get", () -> customHashMap.get(i), () -> hashMap.get(i), iterations);
        runAndMeasure("Remove", () -> customHashMap.remove(i), () -> hashMap.remove(i), iterations);
    }
}
