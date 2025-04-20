package algorithm.sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class RadixSort implements Sort {
    @Override
    public void sort(int[] arr) {
        List<ArrayDeque<Integer>> radix = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            radix.add(new ArrayDeque<>());
        }

        for (int i = 1; i < 10000; i *= 10) {
            for (int n : arr) {
                radix.get((n / i) % 10).add(n);
            }

            int idx = 0;

            for (int j = 0; j < 10; j++) {
                while (!radix.get(j).isEmpty()) {
                    arr[idx++] = radix.get(j).remove();
                }
            }
        }
    }
}
