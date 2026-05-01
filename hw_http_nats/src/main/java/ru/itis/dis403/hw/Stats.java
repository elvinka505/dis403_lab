package ru.itis.dis403.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stats {
    private final List<Long> times;
    public Stats(List<Long> times) {
        this.times = times;
    }

    public double average() {
        return times.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    public double median() {
        List<Long> sorted = new ArrayList<>(times);
        Collections.sort(sorted);
        return sorted.get(sorted.size() / 2);
    }

    public long min() {
        return Collections.min(times);
    }

    public long max() {
        return Collections.max(times);
    }

    public void print(String label) {
        System.out.println("\n=== " + label + " ===");
        System.out.printf("  Среднее:  %.2f мс%n", average());
        System.out.printf("  Медиана:  %.2f мс%n", median());
        System.out.printf("  Мин:      %d мс%n", min());
        System.out.printf("  Макс:     %d мс%n", max());
    }
}
