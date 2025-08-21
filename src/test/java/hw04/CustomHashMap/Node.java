package hw04.CustomHashMap;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Node<K, V> {

    private K key;
    private V value;
    private Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
