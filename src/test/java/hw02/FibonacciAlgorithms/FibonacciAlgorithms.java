package hw02.FibonacciAlgorithms;

import java.util.Map;

public class FibonacciAlgorithms {

    /**
     * Recursive implementation of Fibonacci sequence
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     * <p>
     * Explanation: Each call branches into two recursive calls, creating
     * a binary tree of calls with height n. The same subproblems are
     * solved multiple times, leading to exponential time complexity.
     */

    public static long fibonacciRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        if (n < 2) {
            return n;
        }

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Memoized implementation of Fibonacci sequence
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * <p>
     * Explanation: By caching intermediate results, we avoid
     * redundant calculations. Each number from 0 to n is
     * calculated exactly once.
     */

    public static long fibonacciMemoized(int n, Map<Integer, Long> cache) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        if (n < 2) {
            return n;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long result = fibonacciMemoized(n - 1, cache) + fibonacciMemoized(n - 2, cache);
        cache.put(n, result);

        return result;
    }

    /**
     * Iterative implementation of Fibonacci sequence
     * Time Complexity: O(n) - single loop from 0 to n
     * Space Complexity: O(1) - constant space usage
     * <p>
     * Explanation: Uses bottom-up approach with only two variables
     * to track previous values, eliminating recursion overhead.
     */

    public static long fibonacciIterative(int n) {
        long first = 0;
        long second = 1;
        long result = 0;

        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        if (n < 2) {
            return n;
        }

        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }

        return result;
    }
}
