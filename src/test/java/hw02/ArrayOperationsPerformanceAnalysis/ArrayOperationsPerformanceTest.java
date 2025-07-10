package hw02.ArrayOperationsPerformanceAnalysis;

import org.junit.jupiter.api.Test;

public class ArrayOperationsPerformanceTest {

    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final int[] SHIFTS = {1, 10, 100, 1_000};

    public static void runAndMeasure(Runnable task1, Runnable task2) {
        long startTime1 = System.nanoTime();
            task1.run();
        long endTime1 = System.nanoTime();
        double time1 = (endTime1 - startTime1) / 1_000_000.0;

        long startTime2 = System.nanoTime();
        task2.run();
        long endTime2 = System.nanoTime();
        double time2 = (endTime2 - startTime2) / 1_000_000.0;

        System.out.printf(" %-15.2f| %-9.2f%n", time1, time2);
    }

    private static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }

        return arr;
    }

    @Test
    public void testArrayPerformance() {
        System.out.println("Array Shift Performance Test\n");
        System.out.printf("%-12s %-10s %-16s %-18s%n", "Array size |", "Shift    |", "ArrayCopy (ms) |", "Manual Loop (ms) |");
        System.out.println("---------------------------------------------------------");

        for (int size : SIZES) {
            for (int shift : SHIFTS) {
                int[] arraySystemCopy = createArray(size);
                int[] arrayManualLoop = new int[size];
                System.arraycopy(arraySystemCopy, 0, arrayManualLoop, 0, size);

                System.out.printf("%-11d| %-9d|", size, shift);
                runAndMeasure(() -> ArrayOperations.shiftLeftSystemCopy(arraySystemCopy, shift),
                        () -> ArrayOperations.shiftLeftManualLoop(arrayManualLoop, shift));

                System.out.println("---------------------------------------------------------");
            }
        }
    }
}
