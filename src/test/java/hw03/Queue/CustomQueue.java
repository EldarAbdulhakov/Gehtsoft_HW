package hw03.Queue;

import hw03.CustomLinkedList.Node;

import java.util.NoSuchElementException;

//FIFO (First In, First Out)
public class CustomQueue<T> {

    private Node<T> front;
    private Node<T> rear;
    private int size;

    //  добавление эл-та в конец
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    //  удаление и возврат первого эл-та
    public T dequeue() {
        if (front == null)
            throw new NoSuchElementException();

        T data = front.getData();
        front = front.getNext();
        size--;

        return data;
    }

    //  возврат первого эл-та без удаления
    public T front() {
        return front.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String dataQueue = String.valueOf(front.getData());
        Node<T> current = front.getNext();
        while (current != null) {
            dataQueue = dataQueue + " " + current.getData();
            current = current.getNext();
        }

        return dataQueue;
    }

    public static void main(String[] args) {
        CustomQueue<Integer> customQueue = new CustomQueue<>();
        customQueue.enqueue(1);
        customQueue.enqueue(2);
        customQueue.enqueue(3);
        System.out.println(customQueue.toString());
        System.out.println(customQueue.front());

        customQueue.dequeue();
        System.out.println(customQueue.toString());
        System.out.println(customQueue.front());

    }
}
