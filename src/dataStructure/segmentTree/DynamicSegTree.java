package dataStructure.segmentTree;

import java.util.*;

public class DynamicSegTree {

    static int MIN = 1;
    static int MAX = 1_000_000_000;
    Node root;
    HashMap<String, Integer> nameSearch;
    HashMap<Integer, String> valueSearch;

    DynamicSegTree() {
        this.root = new Node(MIN, MAX);
        this.nameSearch = new HashMap<>();
        this.valueSearch = new HashMap<>();
    }

    int insert(String name, int value) {
        if (nameSearch.containsKey(name) || valueSearch.containsKey(value)) {
            return 0;
        }

        nameSearch.put(name, value);
        valueSearch.put(value, name);

        root.insert(value);

        return 1;
    }

    int delete(String name) {
        if (!nameSearch.containsKey(name)) return 0;

        int value = nameSearch.remove(name);

        valueSearch.remove(value);
        root.delete(value);

        return value;
    }

    String rank(int k) {
        if (root.count < k) return "None";

        int value = root.findRank(k).min;

        return valueSearch.get(value);
    }

    long sum(int k) {
        return root.findSum(k);
    }

    static class Node {
        int count;
        long sum;
        Node right;
        Node left;
        int min;
        int max;

        Node(int min, int max) {
            this.count = 0;
            this.sum = 0;
            this.right = null;
            this.left = null;
            this.min = min;
            this.max = max;
        }

        void insert(int n) {
            sum += n;
            count++;

            if (min == max) {
                return;
            }

            int m = (min + max) >> 1;

            if (n <= m) {
                if (left == null) {
                    left = new Node(min, m);
                }
                left.insert(n);
                return;
            }

            if (right == null) {
                right = new Node(m + 1, max);
            }
            right.insert(n);
        }

        boolean delete(int n) {
            sum -= n;
            count--;

            if (min == max) {
                return false;
            }

            int m = (min + max) >> 1;

            if (n <= m) {
                if (!left.delete(n)) {
                    left = null;
                }
            } else {
                if (!right.delete(n)) {
                    right = null;
                }
            }

            return count != 0;
        }

        Node findRank(int k) {
            if (min == max) {
                return this;
            }

            if (left == null) {
                return right.findRank(k);
            }

            if (right == null) {
                return left.findRank(k);
            }

            if (left.count < k) {
                return right.findRank(k - left.count);
            }

            return left.findRank(k);
        }

        long findSum(int k) {
            if (max <= k) {
                return sum;
            }

            long result = 0;

            if (left != null) {
                result = left.findSum(k);
            }

            int m = (min + max) >> 1;

            if (m < k && right != null) {
                result += right.findSum(k);
            }

            return result;
        }
    }
}