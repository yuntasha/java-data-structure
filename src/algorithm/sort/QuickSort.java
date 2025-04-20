package algorithm.sort;

public class QuickSort implements Sort {

    @Override
    public void sort(int[] arr) {
        quick(arr, 0, arr.length - 1);
    }

    public void quick(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = arr[left];
        int now = left;

        for (int i = left; i <= right; i++) {
            if (pivot > arr[i]) {
                swap(now, i, arr);
                now++;
            }
        }

        arr[now] = pivot;

        quick(arr, left, now - 1);
        quick(arr, now + 1, right);
    }
}
