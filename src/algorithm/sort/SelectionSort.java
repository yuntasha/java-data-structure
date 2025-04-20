package algorithm.sort;

public class SelectionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            int minIdx = 0;
            int minV = Integer.MAX_VALUE;

            for (int j = i; j < len; j++) {
                if (minV > arr[j]) {
                    minIdx = j;
                    minV = arr[j];
                }
            }

            swap(i, minIdx, arr);
        }
    }
}
