import dataStructure.heap.TestAbstractQueue;
import dataStructure.heap.TestQueue;
import algorithm.kadane.Kadane;
import dataStructure.rBTree.RBTree;
import dataStructure.rBTree.RBTreeImpl;

import java.io.IOException;
import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] arr = new int[10000];
        Random numGenerate = new Random();
        for (int i = 0; i < 10000; i++) {
            arr[i] = numGenerate.nextInt(2001) - 2000; // -1000 ~ 1000 난수 생성
        }

        Kadane kadane = new Kadane(arr);


        System.out.println("getTime(() -> algorithm.kadane.bruteForce()) = " + getTime(kadane::bruteForce));
        System.out.println("getTime(algorithm.kadane::prefixSum) = " + getTime(kadane::prefixSum));
        System.out.println("getTime(algorithm.kadane::algorithm.kadane) = " + getTime(kadane::kadane));
        System.out.println("getTime(algorithm.kadane::kadaneNoMemory) = " + getTime(kadane::kadaneNoMemory));
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