package dataStructure.rBTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RBTreeImpl<E> implements RBTree<E>{
    private Node<E> head = null;
    private int size = 0;
    private final Comparator<? super E> comparator;

    public RBTreeImpl(){
        this.comparator = null;
    }

    public RBTreeImpl(Comparator<? super E> comparator){
        this.comparator = comparator;
    }

    @Override
    public void add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        if (this.head == null) {
            this.head = new Node<>(data, null, Color.BLACK);
            return;
        }

        Node<E> node;

        if (comparator != null){
            node = addCompare(data);
        } else {
            node = addNotCompare(data);
        }

        rBTreeLogic(node);
    }

    /**
     * 삼촌 노드가 null이거나 검정 체크
     * @param node 현재 노드
     * @return 검정이거나 null이면 true 아니면 false
     */
    private boolean isUncleBlack(Node<E> node){
        return node.parent.parent.left == null || node.parent.parent.right == null ||
                node.parent.parent.left.color != node.parent.parent.right.color;
    }

    /**
     * 삽입될 위치에 삽입
     * @param data 현재 넣을 데이터
     * @return 들어간 노드
     */
    private Node<E> addCompare(E data){
        Node<E> now = head;
        Node<E> prev = null;
        while (now != null){
            if (comparator.compare(data, now.data)<0){
                prev = now;
                now = now.left;
            } else{
                prev = now;
                now = now.right;
            }
        }

        if (comparator.compare(data, prev.data)<0){
            now = prev.left = new Node<E>(data, prev, Color.RED);
        } else{
            now = prev.right = new Node<E>(data, prev, Color.RED);
        }

        return now;
    }

    private Node<E> addNotCompare(E data){
        Comparable<? super E> key = (Comparable) data;
        Node<E> now = head;
        Node<E> prev = null;
        while (now != null){
            if (key.compareTo(now.data)<0){
                prev = now;
                now = now.left;
            } else{
                prev = now;
                now = now.right;
            }
        }

        if (key.compareTo(prev.data)<0){
            now = prev.left = new Node<E>(data, prev, Color.RED);
        } else{
            now = prev.right = new Node<E>(data, prev, Color.RED);
        }

        return now;
    }

    /**
     * 현재 노드 위치에서 RB트리 로직 수행
     * @param node RR검사하는 노드 중 자식 노드
     */
    private void rBTreeLogic(Node<E> node){
        // 레드-블랙 트리 균형 맞추는 로직을 여기 넣어야함

        while (node != head){
            if (node.color == Color.RED && node.parent.color == Color.RED){ // 부모와 자식 모두 빨강이라면
                if (node.parent == head){ // 부모가 head인 경우 Black으로 바꿔버림
                    if (node.parent.left != null) node.parent.parent.left.color = Color.BLACK;
                    if (node.parent.right != null) node.parent.parent.right.color = Color.BLACK;
                    continue;
                }

                // 현재 부모 노드는 레드

                if (isUncleBlack(node)) { // restructure
                    System.out.println("Restureture");
                    if (comparator.compare(node.parent.data, node.parent.parent.data)<0) { // 부모가 조부모 왼쪽인 경우
                        var nowNode = node.parent;
                        if (comparator.compare(node.data, node.parent.data)>=0) { // 자식이 부모의 오른쪽인 경우
                            nowNode = rotateRR(nowNode);
                        }
                        node = rotateLL(nowNode.parent);
                    } else { // 부모가 조부모의 오른쪽인 경우
                        var nowNode = node.parent;
                        if (comparator.compare(node.data, node.parent.data)<0) { // 자식이 부모의 왼쪽인 경우
                            nowNode = rotateLL(nowNode);
                        }
                        node = rotateRR(nowNode.parent);
                    }
                    node.color = Color.BLACK;
                    node.left.color = Color.RED;
                    node.right.color = Color.RED;
                } else { // recoloring
                    System.out.println("ReColoring");
                    node.parent.parent.color = Color.RED;
                    node.parent.parent.left.color = Color.BLACK;
                    node.parent.parent.right.color = Color.BLACK;
                    node = node.parent.parent;
                    if (head.color == Color.RED) head.color = Color.BLACK;
                }

            } else break;
        }
    }

    /**
     * LL회전을 시켜줌
     * @param node LL회전 전의 부모 노드
     * @return LL회전 후의 부모 노드
     */
    private Node<E> rotateLL(Node<E> node){
        System.out.println("Rotate LL");

        var child = node.left;

        // 본인과 자식 처리
        node.left = child.right;
        child.right = node;

        // 부모 바라보는거 처리
        child.parent = node.parent;
        node.parent = child;

        // 조부모의 처리
        if (node == head){
            head = child;
        } else {
            if (child.parent.left == node){
                child.parent.left = child;
            } else {
                child.parent.right = child;
            }
        }

        return child;
    }

    /**
     * RR회전을 시켜줌
     * @param node RR회전 전의 부모 노드
     * @return RR회전 후의 부모 노드
     */
    private Node<E> rotateRR(Node<E> node){
        System.out.println("Rotate RR");
        var child = node.right;

        // 본인과 자식 처리
        node.right = child.left;
        child.left = node;

        // 부모 바라보는거 처리
        child.parent = node.parent;
        node.parent = child;

        // 조부모의 처리
        if (node == head){
            head = child;
        } else {
            if (child.parent.left == node){
                child.parent.left = child;
            } else {
                child.parent.right = child;
            }
        }
        return child;
    }

    @Override
    public boolean remove(E data) {
        var node = nodeFind(data);
        if (node == null){
            return false;
        } else {
            nodeRemove(node);
            return true;
        }
    }

    /**
     * 해당 데이터를 가진 노드 조회
     * @param data 찾을 노드의 데이터
     * @return 해당 데이터를 가진 노드를 반환 없을 시 null 반한
     */
    private Node<E> nodeFind(E data){
        Node<E> now = head;
        Node<E> prev = null;
        while (now != null){
            if (comparator.compare(data, now.data) == 0){
                return now;
            } else if (comparator.compare(data, now.data)<0){
                prev = now;
                now = now.left;
            } else{
                prev = now;
                now = now.right;
            }
        }

        return null;
    }

    /**
     * 노드 찾아서 제거 후 로직까지 돌려줌
     * @param node 삭제할 노드
     */
    private void nodeRemove(Node<E> node){
        if (node.left == null && node.right == null){ // 노드가 끝 부분일 경우
            if (node.parent.left == node) node.parent.left = null;
            else node.parent.right = null;
            // 이중 흑색 노드 처리 null이라 해줘야함
        } else if(node.left != null && node.right != null){ // 양쪽 자식 노드가 존재하는 경우
            // 왼쪽에서 가장 큰거 가져와서 매꿈
            var now = node.left;
            var prev = node;
            while (now != null){
                prev = now;
                now = now.right;
            }

            // 기존 연결 제거
            prev.parent.right = null;

            // 부모와의 연결
            prev.parent = node.parent;
            if (node.parent.left == node) node.parent.left = prev;
            else node.parent.right = prev;

            // 자식과의 연결
            prev.left = node.left;
            node.left.parent = prev;

            prev.right = node.right;
            node.right.parent = prev;

            prev.color = node.color;

            if (prev.color==Color.BLACK){
                // 이중 블랙 찾아서 해결
                return;
            }

        } else { // 한쪽 자식 노드만 존재하는 경우
            if (node.left != null) { // 왼쪽 자식 노드만 존재하는 경우
                if (node.parent.left == node) {
                    node.parent.left = node.left;
                }
                else {
                    node.parent.right = node.left;
                }
                node.left.parent = node.parent;

                node.left.color = node.color;
                node = node.left;
            } else { // 오른쪽 자식 노드만 존재하는 경우
                if (node.parent.left == node) {
                    node.parent.left = node.right;
                }
                else {
                    node.parent.right = node.right;
                }
                node.right.parent = node.parent;

                node.right.color = node.color;
                node = node.right;
            }
            if (node.color == Color.BLACK){
                // 이중 블랙 찾아서 해결
                return;
            }
        }
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public List<E> getList() {
        var list = new ArrayList<E>();
        getElement(list, this.head);
        return list;
    }

    private void getElement(List<E> list, Node<E> now){
        if (now == null) return;
        getElement(list, now.left);
        list.add(now.data);
        getElement(list, now.right);
    }

    private class Node<E> {
        E data;
        Color color;
        Node<E> left = null;
        Node<E> right = null;
        Node<E> parent;

        Node(E data, Node<E> parent, Color color){
            this.data = data;
            this.color = color;
            this.parent = parent;
        }
    }
    public enum Color{
        RED,
        BLACK
    }
}
