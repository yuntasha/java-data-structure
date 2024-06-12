import heap.TestAbstractQueue;
import heap.TestQueue;
import rBTree.RBTree;
import rBTree.RBTreeImpl;

import static heap.UseHeap.*;

public class Main {
    public static void main(String[] args) {
        testQ();
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
    }

    private static void addWithPrint(RBTree<Integer> rbt, int i){
        System.out.println("add "+i);
        rbt.add(i);
        System.out.println("======================");
        rbt.getList().forEach(System.out::println);
        System.out.println("======================");

    }

    private static void chkPD(){
        TestAbstractQueue testQueue = new TestQueue(2, 3);
        System.out.println(testQueue.testPrivate());
    }
}