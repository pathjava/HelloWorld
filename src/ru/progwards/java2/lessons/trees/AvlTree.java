// Oleg Kiselev
// 07.06.2020, 19:30

package ru.progwards.java2.lessons.trees;

public class AvlTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size = 0;

    public class Node<K, V> {
        private int height;
        private final K key;
        private V value;
        private Node<K, V> parent;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        Node<K, V> node = root;
        Node<K, V> newNode;
        if (node == null) {
            root = new Node<>(key, value);
            size++;
            return;
        }
        int comparision = key.compareTo(node.key);
        if (comparision == 0)
            node.value = value;

        if (comparision < 0) {
            if (node.left == null) {
                newNode = new Node<>(key, value);
                node.left = newNode;
                newNode.parent = node;
                size++;
            } else
                put(node.key, value);
        } else {
            if (node.right == null) {
                newNode = new Node<>(key, value);
                node.right = newNode;
                newNode.parent = node;
                size++;
            } else
                put(node.key, value);
        }
    }


    public static void main(String[] args) {
        AvlTree<Integer, String> test = new AvlTree<>();
        test.put(5, "five");
        test.put(3, "three");
        test.put(6, "six");
    }

}
