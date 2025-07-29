package CustomHashMap;

import lombok.Data;

@Data
public class Node<K, V> {

    private K key;
    private V value;
    private int hash;
    private Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = Math.abs(key.hashCode());
    }
}
