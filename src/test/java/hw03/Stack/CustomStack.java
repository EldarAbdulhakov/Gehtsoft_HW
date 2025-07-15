package hw03.Stack;

//LIFO (Last In First Out)

import hw03.CustomLinkedList.Node;

import java.util.EmptyStackException;

public class CustomStack<T> {

    private Node<T> top;
    private int size;

//  положили сверху
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

//  удаление и возврат верхнего элемента
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T data = top.getData();
        top = top.getNext();
        size--;

        return data;
    }

//  возврат верхнего эл-та без удаления (или peek())
    public T top() {
        return top.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String dataStack = String.valueOf(top.getData());
        Node<T> currentNode = top.getNext();
        while (currentNode != null) {
            dataStack = dataStack + " " + currentNode.getData();
            currentNode = currentNode.getNext();
        }

        return dataStack;
    }

    public static void main(String[] args) {
        CustomStack<Integer> customStack = new CustomStack<>();
        customStack.push(1);
        customStack.push(2);
        customStack.push(3);
        System.out.println("size = " + customStack.size);
        System.out.println(customStack.toString());

        System.out.println("top = " + customStack.top());

        System.out.println(customStack.pop());
        System.out.println(customStack.toString());
        System.out.println("size = " + customStack.size);
    }
}
