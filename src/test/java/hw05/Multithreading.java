package hw05;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Multithreading {

    private static final int SIZE = 100_000_000;
    private final short[] array;

    public Multithreading() {
        array = new short[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = (short) i;
        }
    }

    private long sumWithParallelStream(int threadsCount) throws ExecutionException, InterruptedException {
        ForkJoinPool customPool = new ForkJoinPool(threadsCount);

        return customPool.submit(() ->
                IntStream.range(0, array.length)
                        .parallel()
                        .mapToLong(i -> array[i])
                        .sum()
        ).get();
    }

    private long sumWithParallelThreads(int threadsCount) throws InterruptedException {
        long result = 0;
        long[] partialSums = new long[threadsCount];
        int chunkSize = SIZE / threadsCount;
        Thread[] threads = new Thread[threadsCount];

        for (int t = 0; t < threadsCount; t++) {
            int start = t * chunkSize;
            int end = (t == threadsCount - 1) ? array.length : start + chunkSize;
            final int threadIndex = t;

            threads[t] = new Thread(() -> {
                long localSum = 0;
                for (int i = start; i < end; i++) {
                    localSum += array[i];
                }
                partialSums[threadIndex] = localSum;
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (long sum : partialSums) {
            result += sum;
        }

        return result;
    }

    private static void warmUp() throws ExecutionException, InterruptedException {
        Multithreading array1 = new Multithreading();
        for (int i = 1; i <= 50; i++) {
            array1.sumWithParallelStream(i);
            array1.sumWithParallelThreads(i);
        }
    }

    public static void main(String[] args) throws ExecutionException {
        Multithreading array = new Multithreading();
        int[] threadCounts = {1, 10, 100, 1000};

        try (FileWriter writer = new FileWriter("sum_results.txt")) {
            writer.write(String.format("Threads | Method Parallel: | Time, ms |    Sum    |%n"));
            writer.write(String.format("---------------------------------------------------%n"));
            warmUp();
            for (int threads : threadCounts) {
                long startTime1 = System.nanoTime();
                long sum1 = array.sumWithParallelStream(threads);
                long endTime1 = System.nanoTime();
                long time1 = (endTime1 - startTime1) / 1000000;
                writer.write(String.format("  %-5d |     Stream       |    %-6d| %d |%n", threads, time1, sum1));

                long startTime2 = System.nanoTime();
                long sum2 = array.sumWithParallelThreads(threads);
                long endTime2 = System.nanoTime();
                long time2 = (endTime2 - startTime2) / 1000000;
                writer.write(String.format("  %-5d |     Threads      |    %-6d| %d |%n", threads, time2, sum2));
                writer.write(String.format("---------------------------------------------------%n"));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
