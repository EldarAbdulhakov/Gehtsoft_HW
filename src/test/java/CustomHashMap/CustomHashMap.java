package CustomHashMap;

import java.util.Arrays;

public class CustomHashMap<K, V> {

    private int capacity = 16;
    private Node<K, V>[] map = new Node[capacity];
    private int size = 0;

    private void resize() {
        int oldCapacity = capacity;
        capacity = capacity * 2;
        Node<K, V>[] oldMap = map;
        map = new Node[capacity];

        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> currentNode = oldMap[i];
            while (currentNode != null) {
                putInternal(currentNode.getKey(), currentNode.getValue());
                currentNode = currentNode.getNext();
            }
        }
    }

    private void putInternal(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = getIndex(key);
        Node<K, V> currentNode = map[index];

        if (currentNode == null) {
            map[index] = newNode;
        } else {
            while (true) {
                if ((key == null && currentNode.getKey() == null) || (key != null && key.equals(currentNode.getKey()))) {
                    currentNode.setValue(value);
                    return;
                }
                if (currentNode.getNext() == null) {
                    currentNode.setNext(newNode);
                    break;
                }
                currentNode = currentNode.getNext();
            }
        }
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }

        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        if (size * 100.0 / capacity > 75) {
            resize();
        }

        Node<K, V> newNode = new Node<>(key, value);
        int index = getIndex(key);
        Node<K, V> currentNode = map[index];

        if (currentNode == null) {
            map[index] = newNode;
        } else {
            while (true) {
                if ((key == null && currentNode.getKey() == null) || (key != null && key.equals(currentNode.getKey()))) {
                    currentNode.setValue(value);
                    return;
                }
                if (currentNode.getNext() == null) {
                    currentNode.setNext(newNode);
                    break;
                }
                currentNode = currentNode.getNext();
            }
        }
        size++;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("[");
        boolean first = true;

        for (int i = 0; i < capacity; i++) {
            Node<K, V> currentNode = map[i];
            while (currentNode != null) {
                if (!first) {
                    data.append(", ");
                }
                data
                        .append("[")
                        .append(currentNode.getKey())
                        .append(", ")
                        .append(currentNode.getValue())
                        .append("]");
                first = false;
                currentNode = currentNode.getNext();
            }
        }
        data.append("]");

        return data.toString();
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> currentNode = map[index];

        while (currentNode != null) {
            if ((key == null && currentNode.getKey() == null) || (key != null && key.equals(currentNode.getKey()))) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> currentNode = map[index];

        while (currentNode != null) {
            if ((key == null && currentNode.getKey() == null) || (key != null && key.equals(currentNode.getKey()))) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            Node<K, V> currentNode = map[i];

            while (currentNode != null) {
                if ((value == null && currentNode.getValue() == null) ||
                        (value != null && value.equals(currentNode.getValue()))) {
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public K[] keySet() {
        K[] result = (K[]) new Object[size];
        int arrayIndex = 0;

        for (int i = 0; i < capacity; i++) {
            Node<K, V> currentNode = map[i];

            while (currentNode != null) {
                result[arrayIndex++] = currentNode.getKey();
                currentNode = currentNode.getNext();
            }
        }

        return result;
    }

    public V[] values() {
        V[] result = (V[]) new Object[size];
        int arrayIndex = 0;

        for (int i = 0; i < capacity; i++) {
            Node<K, V> currentNode = map[i];

            while (currentNode != null) {
                result[arrayIndex++] = currentNode.getValue();
                currentNode = currentNode.getNext();
            }
        }

        return result;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> currentNode = map[index];
        Node<K, V> prevNode = null;

        while (currentNode != null) {
            if ((key == null && currentNode.getKey() == null) || (key != null && key.equals(currentNode.getKey()))) {
                if (prevNode == null) {
                    map[index] = currentNode.getNext();
                } else {
                    prevNode.setNext(currentNode.getNext());
                }
                size--;
                return currentNode.getValue();
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public void clear() {
        map = new Node[capacity];
        size = 0;
    }

    public static void main(String[] args) {
        CustomHashMap<Integer, Integer> customHashMap = new CustomHashMap<Integer, Integer>();
        customHashMap.put(1, 10);
        customHashMap.put(2, 20);
        customHashMap.put(null, 30);
        customHashMap.put(null, 40);
        customHashMap.put(5, 50);
        customHashMap.put(6, 60);
        customHashMap.put(7, 70);
        customHashMap.put(8, 80);
        customHashMap.put(9, 90);
        customHashMap.put(10, 90);
        customHashMap.put(11, 90);
        customHashMap.put(12, 90);
        customHashMap.put(13, 90);
        customHashMap.put(14, 90);
        customHashMap.put(15, null);
        customHashMap.put(11, 900);

        System.out.println(customHashMap);
        System.out.println(customHashMap.remove(1));
        System.out.println(customHashMap.get(110));
        System.out.println(customHashMap.containsKey(1));
        System.out.println(customHashMap.containsKey(50));
        System.out.println(customHashMap.size());
        System.out.println(customHashMap.containsValue(9000));
        System.out.println(Arrays.toString(customHashMap.values()));
        System.out.println(Arrays.toString(customHashMap.keySet()));


    }
}
