package algorithm.sort;

public interface Sort {
    void sort(int[] arr);


    default void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
