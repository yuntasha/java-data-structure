package algorithm.sort;

public class BubbleSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(i, j, arr);
                }
            }
        }
    }
}
