package hw04;

import hw04.CustomHashMap.CustomHashMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PerformanceTest {

    int count = 100_000;
    CustomHashMap<Integer, Integer> customHashMap = new CustomHashMap<>();
    HashMap<Integer, Integer> hashMap = new HashMap<>();



    @Test
    public void testPutPerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            customHashMap.put(i, i * 10);
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
    }

    @Test
    public void testGetPerformance() {

    }
}
