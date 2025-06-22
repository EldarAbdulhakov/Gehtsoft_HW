package abdulhakov.eldar;

public class CustomList {

    private int[] array = new int[0];
    private int length = 0;

    public void add(int value) {
        int[] newArray = new int[length + 1];

        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }

        newArray[length] = value;
        array = newArray;
        length++;
    }

    public void print() {
        System.out.print("Array: ");
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public int size() {
        return array.length;
    }

    public int get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        return array[index];
    }

    public void set(int index, int value) {
        array[index] = value;
    }

    public void clear() {
        array = new int[0];
        length = 0;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public void remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }

        int[] newArray = new int[length - 1];

        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        for (int i = index + 1; i < length; i++) {
            newArray[i - 1] = array[i];
        }

        array = newArray;
        length--;
    }
}
