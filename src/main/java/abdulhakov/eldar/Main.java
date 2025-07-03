package abdulhakov.eldar;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        PerformanceBenchmarks.benchmarkAddMillion("LinkedList", new LinkedList<>());
        PerformanceBenchmarks.benchmarkAddRemove1000("LinkedList", new LinkedList<>());
        System.out.println();

        PerformanceBenchmarks.benchmarkAddMillion("List", new ArrayList<>());
        PerformanceBenchmarks.benchmarkAddRemove1000("List", new ArrayList<>());
        System.out.println();

        PerformanceBenchmarks.benchmarkAddMillion("CustomList", new CustomList<>());
        PerformanceBenchmarks.benchmarkAddRemove1000("CustomList", new CustomList<>());
    }
}
