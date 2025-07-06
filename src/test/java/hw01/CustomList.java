package hw01;

import java.util.*;

public class CustomList<T> implements List<T> {

    private Object[] array = new Object[10];
    private int size = 0;

    private void ensureCapacity(int capacity) {
        if (capacity > array.length) {
            extendCapacity(capacity);
        }
    }

    private void extendCapacity(int capacity) {
        int newCapacity = capacity * 2;
        if (newCapacity < capacity) {
            newCapacity = capacity;
        }

        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    @Override
    public boolean add(T element) {
        ensureCapacity(size + 1);


        array[size] = element;
        size++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }

        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size--;
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

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index <  0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object o : c) {
            while (remove(o)) {
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
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
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }

        T oldValue = (T) array[index];
        array[index] = element;

        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            Object currentElement = array[i];
            if (currentElement.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }

        for (int i = size - 1; i >=0; i--) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        return indexOf(size);
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
