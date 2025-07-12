package hw03.CustomLinkedList;

import lombok.Data;

@Data
public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}
