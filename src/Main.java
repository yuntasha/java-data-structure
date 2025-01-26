import heap.TestAbstractQueue;
import heap.TestQueue;
import kmp.Kmp;
import rBTree.RBTree;
import rBTree.RBTreeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Kmp kmp = new Kmp("abcabcabc", "abcabcabcabcabcabc");
        System.out.println(Arrays.toString(kmp.getPi()));
        System.out.println("kmp.count() = " + kmp.count());
        System.out.println("kmp.getIdx() = " + kmp.getIdx());
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