package hw03.CustomLinkedList;

import java.util.NoSuchElementException;

public class CustomLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void addFirst(T data) {
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

    public void add(int index, T data) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.setNext(first);
            first = newNode;
            if (size == 0) {
                last = newNode;
            }
        }

        Node<T> currentNode = first;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNext();
        }
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        size++;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setData(data);
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

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> getNode = first;
        for (int i = 0; i < index; i++) {
            getNode = getNode.getNext();
        }

        return getNode.getData();
    }

    public T removeFirst() {
        if (first == null)
            throw new NoSuchElementException();

        T data = first.getData();
        first = first.getNext();
        size--;

        return data;
    }

    public T removeLast() {
        if (last == null)
            throw new NoSuchElementException();

        T data = last.getData();
        Node<T> current = first;
        while (current.getNext() != last) {
            current = current.getNext();
        }
        current.setNext(null);
        last = current;
        size--;

        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String dataList = String.valueOf(first.getData());
        Node<T> current = first.getNext();
        while (current != null) {
            dataList = dataList + " " + current.getData();
            current = current.getNext();
        }

        return dataList;
    }

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
        customLinkedList.removeLast();

        System.out.println("first = " + customLinkedList.getFirst());
        System.out.println("last = " + customLinkedList.getLast());
        System.out.println("size = " + customLinkedList.size());

        int index = 1;
        System.out.println("Linked list :[" + customLinkedList.toString() + "]");
        System.out.println("index № " + index + " = " + customLinkedList.get(index));

        customLinkedList.set(1, 155);
        System.out.println("size = " + customLinkedList.size());
        System.out.println("Linked list :[" + customLinkedList.toString() + "]");

        customLinkedList.add(2, 255);
        System.out.println("Linked list :[" + customLinkedList.toString() + "]");
    }
}
