import algorithm.sort.*;
import dataStructure.heap.TestAbstractQueue;
import dataStructure.heap.TestQueue;
import algorithm.kadane.Kadane;
import dataStructure.rBTree.RBTree;
import dataStructure.rBTree.RBTreeImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] arr = new int[10000];
        Random numGenerate = new Random();
        for (int i = 0; i < 10000; i++) {
            arr[i] = numGenerate.nextInt(9000) + 1000; // -1000 ~ 1000 난수 생성
        }

        List<Sort> sortList = List.of(new BubbleSort(), new InsertionSort(), new SelectionSort(), new MergeSort(), new QuickSort(), new RadixSort());
        List<String> nameList = List.of("BubbleSort", "InsertionSort", "SelectionSort", "MergeSort", "QuickSort", "RadixSort");

        HashMap<String, Long> time = new HashMap<>();

        for (String name : nameList) {
            time.put(name, 0L);
        }

        System.out.println("Before arr");
        System.out.println(Arrays.toString(arr));
        for (int t = 0; t < 10; t++) {
            for (int i = 0; i < 6; i++) {
                int[] temp = new int[10000];
                System.arraycopy(arr, 0, temp, 0, 10000);

                long start = System.currentTimeMillis();
                sortList.get(i).sort(temp);
                long end = System.currentTimeMillis();

                time.put(nameList.get(i), time.get(nameList.get(i)) + end - start);
            }
        }

        for (int i = 0; i < 6; i++) {
            System.out.println(nameList.get(i) + " : " + (time.get(nameList.get(i))));
        }
    }

    private static long getTime(Supplier<Integer> function) {
        long start = System.currentTimeMillis();
        function.get();
        long end = System.currentTimeMillis();

        return end - start;
    }


    private static void testQ(){
        RBTree<Integer> rbt = new RBTreeImpl<>((a,b) -> a-b);

        addWithPrint(rbt, 8);
        addWithPrint(rbt, 7);
        addWithPrint(rbt, 9);
        addWithPrint(rbt, 3);
        addWithPrint(rbt, 6);
        addWithPrint(rbt, 4);
        addWithPrint(rbt, 5);
        addWithPrint(rbt, 12);
        removeWithPrint(rbt, 6);
        removeWithPrint(rbt, 4);
    }

    private static void addWithPrint(RBTree<Integer> rbt, int i){
        System.out.println("add "+i);
        rbt.add(i);
        System.out.println("======================");
        rbt.getList().forEach(System.out::println);
        System.out.println("======================");
    }

    private static void removeWithPrint(RBTree<Integer> rbt, int i){
        System.out.println("remove "+i);
        rbt.remove(i);
        System.out.println("======================");
        rbt.getList().forEach(System.out::println);
        System.out.println("======================");
    }

    private static void chkPD(){
        TestAbstractQueue testQueue = new TestQueue(2, 3);
        System.out.println(testQueue.testPrivate());
    }
}