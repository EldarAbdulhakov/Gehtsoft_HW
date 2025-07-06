package hw01;

import java.util.List;

public class PerformanceBenchmarks {

    public static void benchmarkAddMillion(String name, List<Integer> list) {
        System.gc();
        long beforeAddMemory = getUsedMemory();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        long addTime = System.currentTimeMillis() - startTime;
        long afterAddMemory = getUsedMemory();
        double addMemory = (afterAddMemory - beforeAddMemory) / 1024.0 / 1024.0;

        System.out.printf("%-10s | Добавление %10d мс | %10.2f МБ%n", name, addTime, addMemory);
    }

    public static void benchmarkAddRemove1000(String name, List<Integer> list) {
        System.gc();
        long startTime = System.currentTimeMillis();
        long beforeRemoveMemory = getUsedMemory();

        for (int i = 0; i < 10_000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10_000; i++) {
            list.remove(0);
        }

        long removeTime = System.currentTimeMillis() - startTime;
        long afterRemoveMemory = getUsedMemory();
        double removeMemory = (afterRemoveMemory - beforeRemoveMemory) / 1024.0 / 1024.0;

        System.out.printf("%-10s | Удаление   %10d мс | %10.2f МБ%n", name, removeTime, removeMemory);
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();

        return runtime.totalMemory() - runtime.freeMemory();
    }
}
