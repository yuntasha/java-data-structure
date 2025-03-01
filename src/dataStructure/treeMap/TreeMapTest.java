package dataStructure.treeMap;

import java.util.*;

public class TreeMapTest {
    public static void main(String[] args) {
        testSet();
        testMap();
    }

    static void testSet() {
//        Set<Node> set = new HashSet<>();
//        Set<Node> set = new TreeSet<>(Comparator.comparingInt(Node::getValue));
        Set<Node> set = new TreeSet<>(Comparator.comparingInt(Node::getValue)
                .thenComparingInt(Node::getNum));

        set.add(new Node(1, 3));
        System.out.println(set.size());

        set.add(new Node(2, 4));
        System.out.println(set.size());

        set.add(new Node(3, 4));
        System.out.println(set.size());

        for (Node node : set) {
            System.out.println(node.num + " " + node.value);
        }
    }

    static void testMap() {
//        Map<Node, Integer> map = new HashMap<>();
//        Map<Node, Integer> map = new TreeMap<>(Comparator.comparingInt(Node::getValue));
        Map<Node, Integer> map = new TreeMap<>(Comparator.comparingInt(Node::getValue)
                .thenComparingInt(Node::getNum));

        map.put(new Node(1, 3), 1);
        System.out.println(map.size());

        map.put(new Node(2, 4), 2);
        System.out.println(map.size());

        map.put(new Node(3, 4), 3);
        System.out.println(map.size());

        for (Map.Entry<Node, Integer> en : map.entrySet()) {
            System.out.println(en.getKey().num + " " + en.getValue());
        }
    }

    static class Node {
        int num;
        int value;

        public Node(int num, int value) {
            this.num = num;
            this.value = value;
        }

        public int getNum() {
            return num;
        }

        public int getValue() {
            return value;
        }

//        public int getValue() {
//            return value * 1000 + num;
//        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node n = (Node) obj;
            return this.num == n.num && this.value == n.value;
        }
    }
}
