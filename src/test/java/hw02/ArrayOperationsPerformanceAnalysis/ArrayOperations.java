package hw02.ArrayOperationsPerformanceAnalysis;

public class ArrayOperations {

    /**
     * Shift array elements using System.arraycopy
     */
    public static void shiftLeftSystemCopy(int[] array, int positions) {
        if (positions < 0 || positions > array.length) {
            throw new IllegalArgumentException("Positions must be ≥ 0 and < array length");
        }

        System.arraycopy(array, positions, array, 0, array.length - positions);

        for (int i = array.length - positions; i < array.length; i++) {
            array[i] = 0;
        }
    }

    /**
     * Shift array elements using manual for loop
     */
    public static void shiftLeftManualLoop(int[] array, int positions) {
        if (positions < 0 || positions > array.length) {
            throw new IllegalArgumentException("Positions must be >= 0 and <= array length");
        }

        for (int i = 0; i < array.length - positions; i++) {
            array[i] = array[i + positions];
        }

        for (int i = array.length - positions; i < array.length; i++) {
            array[i] = 0;
        }
    }
}
