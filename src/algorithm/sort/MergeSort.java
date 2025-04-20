package algorithm.sort;

public class MergeSort implements Sort {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    void mergeSort(int[] arr, int left, int right) {
        if (left == right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        int[] arr1 = new int[mid - left + 1];
        int[] arr2 = new int[right - mid];

        System.arraycopy(arr, left, arr1, 0, arr1.length);
        System.arraycopy(arr, mid + 1, arr2, 0, arr2.length);

        int idx1 = 0;
        int idx2 = 0;
        int now = left;

        while (now <= right) {
            if (idx1 > arr1.length - 1) {
                arr[now++] = arr2[idx2++];
                continue;
            }
            if (idx2 > arr2.length - 1) {
                arr[now++] = arr1[idx1++];
                continue;
            }
            if (arr1[idx1] > arr2[idx2]) {
                arr[now++] = arr2[idx2++];
                continue;
            }
            arr[now++] = arr1[idx1++];
        }
    }
}
