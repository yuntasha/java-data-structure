package algorithm.binarySearch;

import java.util.function.Predicate;

public class BinarySearch {
    private final int[] arr;

    public BinarySearch(int[] arr) {
        this.arr = arr;
    }

    public int findIdx(int n) {
        int start = 0;
        int end = arr.length - 1;

        if (arr[start] >= n) {
            return -1;
        }
        if (arr[end] < n) {
            return arr.length;
        }

        while (start + 1 < end) {
            int mid = (start + end) / 2;

            if (arr[mid] < n) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return end;
    }

    public int findIdx(Predicate<Integer> predicate) {
        // 앞이 T 뒤가 F
        int start = 0;
        int end = arr.length - 1;

        if (!predicate.test(arr[start])) {
            return -1;
        }
        if (predicate.test(arr[end])) {
            return arr.length;
        }

        while (start + 1 < end) {
            int mid = (start + end) / 2;

            if (predicate.test(arr[mid])) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return end;
    }
}
