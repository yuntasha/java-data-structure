package dataStructure.rBTree;

import java.util.List;

public interface RBTree<E> {

    /**
     * 데이터 추가
     * @param data 추가할 데이터
     */
    void add(E data);

    /**
     * 특정 데이터 삭제
     * @param data 삭제할 데이터
     * @return 데이터 있으면 True, 없으면 False 그리고 삭제
     */
    boolean remove(E data);

    /**
     * 가장 작은 데이터 삭제
     * @return 가장 작은 데이터 리턴
     */
    E removeFirst();

    /**
     * 가장 큰 데이터 삭제
     * @return 가장 큰 데이터 리턴
     */
    E removeLast();

    /**
     * 현재 트리에 들어있는 데이터 수
     * @return 데이터 수
     */
    int size();

    /**
     * 현재 트리를 오름차순으로 반환
     * @return List 형식으로 반환
     */
    List<E> getList();
}
