package abdulhakov.eldar;

import java.util.AbstractList;

public class CustomList<T> extends AbstractList<T> {

    private Object[] array = new Object[0];
    private int size = 0;

    @Override
    public boolean add(T value) {
        Object[] newArray = new Object[size + 1];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        newArray[size] = value;
        array = newArray;
        size++;

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }

        return (T) array[index];
    }

    @Override
    public T set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }

        T oldValue = (T) array[index];
        array[index] = value;

        return oldValue;
    }

    @Override
    public void clear() {
        array = new Object[0];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        T oldValue = (T) array[index];
        Object[] newArray = new Object[size - 1];

        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        for (int i = index + 1; i < size; i++) {
            newArray[i - 1] = array[i];
        }

        array = newArray;
        size--;

        return oldValue;
    }
}
