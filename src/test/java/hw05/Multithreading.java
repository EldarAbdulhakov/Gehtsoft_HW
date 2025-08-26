package hw05;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Multithreading {

    private static final int SIZE = 100_000_000;
    private final short[] array;

    public Multithreading() {
        array = new short[SIZE];
        for (int i = 0; i < SIZE; i++) {
//            array[i] = (short) (Math.random() * Short.MAX_VALUE);
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Multithreading array = new Multithreading();
//        System.out.println(array.sumWithParallelStream(5));
//        System.out.println(array.sumWithParallelThreads(5));



    }
}
