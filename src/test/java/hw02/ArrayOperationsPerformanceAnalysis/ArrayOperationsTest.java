package hw02.ArrayOperationsPerformanceAnalysis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ArrayOperationsTest {

    public static void runAndMeasure(String testCase, Runnable task) {
        long startTime = System.nanoTime();

            task.run();

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1_000_000.0;
        System.out.printf(testCase + " | %5.2f ms%n", time);
    }

    @ParameterizedTest(name = "n = {0}")
    @ValueSource(ints = {1000, 10_000, 100_000, 1_000_000})
    public void testPerformanceArraySize(int n) {
        int positions = 1000;

        int[] arraySystemCopy = new int[n];
        for (int i = 0; i < arraySystemCopy.length; i++) {
            arraySystemCopy[i] = i;
        }

        int[] arrayManualLoop = new int[n];
        System.arraycopy(arraySystemCopy, 0, arrayManualLoop, 0, n);

        System.out.printf("n = %-8d", n);
        runAndMeasure("| SystemCopy", () -> ArrayOperations.shiftLeftSystemCopy(arraySystemCopy, positions));

        System.out.printf("n = %-8d", n);
        runAndMeasure("| ManualLoop", () -> ArrayOperations.shiftLeftManualLoop(arrayManualLoop, positions));

        System.out.println("-------------------------------------");
    }

    @ParameterizedTest(name = "n = {0}")
    @ValueSource(ints = {1, 10, 100, 1000})
    public void testPerformanceShiftPositions(int n) {
        int arraySize = 2000;

        int[] arraySystemCopy = new int[arraySize];
        for (int i = 0; i < arraySystemCopy.length; i++) {
            arraySystemCopy[i] = i;
        }
        int[] arrayManualLoop = new int[n];
        System.arraycopy(arraySystemCopy, 0, arrayManualLoop, 0, n);

        System.out.printf("n = %-8d", n);
        runAndMeasure("| SystemCopy", () -> ArrayOperations.shiftLeftSystemCopy(arraySystemCopy, n));

        System.out.printf("n = %-8d", n);
        runAndMeasure("| ManualLoop", () -> ArrayOperations.shiftLeftManualLoop(arrayManualLoop, n));

        System.out.println("-------------------------------------");
    }
}
