package hw03.CustomLinkedList;

import lombok.Data;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void addFirst(T data) {
        if (data == null)
            throw new NullPointerException();

        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        if (data == null)
            throw new NullPointerException();

        Node<T> newNode = new Node<>(data);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public T getFirst() {
        if (first == null)
            throw new NoSuchElementException();

        return first.getData();
    }

    public T getLast() {
        if (last == null)
            throw new NoSuchElementException();

        return last.getData();
    }

    public T removeFirst() {
        if (first == null)
            throw new NoSuchElementException();

        first = first.getNext();
        size--;

        return first.getData();
    }

    public T removeLast() {
        if (last == null)
            throw new NoSuchElementException();


        last


        size--;

        return last.getData();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

//    public List<T> toString() {
//
//        while ( != null) {
//            getFirst();
//        }
//        return ;
//    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
        customLinkedList.addFirst(10);
        customLinkedList.addFirst(20);
        customLinkedList.addFirst(30);

        customLinkedList.addLast(40);
        customLinkedList.addFirst(45);
        customLinkedList.addLast(50);

        customLinkedList.removeFirst();
        customLinkedList.removeFirst();

        System.out.println("first = " + customLinkedList.getFirst());
        System.out.println("last = " + customLinkedList.getLast());
        System.out.println("size = " + customLinkedList.size());
    }

}
