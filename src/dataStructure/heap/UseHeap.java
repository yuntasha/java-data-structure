package dataStructure.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class UseHeap {
    public static void useIntegerMinHeap(){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        System.out.println("======================");
        System.out.println("minHeap");

        System.out.println("Before");
        for (int i=0; i<20; i++) {
            int k = (int) (Math.random() * 10);
            System.out.print(k + " ");
            minHeap.add(k);
        }
        System.out.println();

        System.out.println("After");
        while (!minHeap.isEmpty()){
            System.out.print(minHeap.remove()+ " ");
        }
        System.out.println();

        System.out.println("======================");
    }

    public static void useIntegerMaxHeap(){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        System.out.println("======================");
        System.out.println("maxHeap");

        System.out.println("Before");
        for (int i=0; i<20; i++) {
            int k = (int) (Math.random() * 10);
            System.out.print(k + " ");
            maxHeap.add(k);
        }
        System.out.println();

        System.out.println("After");
        while (!maxHeap.isEmpty()){
            System.out.print(maxHeap.remove()+ " ");
        }
        System.out.println();

        System.out.println("======================");
    }

    static class Node{
        int index;
        int value;

        public Node(int index, int value){
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" +
                    index +
                    ", " + value +
                    ')';
        }
    }

    public static void useClassMinHeap(){
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.value - n2.value);

        System.out.println("======================");
        System.out.println("minHeap");

        System.out.println("Before");
        for (int i=0; i<20; i++) {
            Node node = new Node(i, (int) (Math.random() * 10));
            System.out.print(node.toString() + " ");
            minHeap.add(node);
        }
        System.out.println();

        System.out.println("After");
        while (!minHeap.isEmpty()){
            System.out.print(minHeap.remove().toString()+ " ");
        }
        System.out.println();

        System.out.println("======================");
    }

    public static void useClassMaxHeap(){
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((n1, n2) -> n2.value - n1.value);

        System.out.println("======================");
        System.out.println("maxHeap");

        System.out.println("Before");
        for (int i=0; i<20; i++) {
            Node node = new Node(i, (int) (Math.random() * 10));
            System.out.print(node.toString() + " ");
            maxHeap.add(node);
        }
        System.out.println();

        System.out.println("After");
        while (!maxHeap.isEmpty()){
            System.out.print(maxHeap.remove().toString()+ " ");
        }
        System.out.println();

        System.out.println("======================");
    }
}
