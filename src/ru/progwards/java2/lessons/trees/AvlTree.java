// Oleg Kiselev
// 07.06.2020, 19:30

package ru.progwards.java2.lessons.trees;

public class AvlTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size = 0;

    public static class Node<K, V> {
        private int height;
        private K key;
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
            throw new IllegalArgumentException("The key cannot be null!"); //TODO change all exception
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return;
        } else if (key.compareTo(root.key) == 0) {
            root.value = value;
            return;
        }
        Node<K, V> node = root;
        addFromPut(node, key, value);
    }

    private void addFromPut(Node<K, V> node, K key, V value) {
        Node<K, V> newNode;
        int comparision = key.compareTo(node.key);
        if (comparision < 0) {
            if (node.left == null) {
                newNode = new Node<>(key, value);
                node.left = newNode;
                newNode.parent = node;
                size++;
                //TODO запускаем перестроение
            } else if (key.compareTo(node.left.key) == 0)
                node.left.value = value;
            else {
                newNode = node.left;
                addFromPut(newNode, key, value);
            }
        } else {
            if (node.right == null) {
                newNode = new Node<>(key, value);
                node.right = newNode;
                newNode.parent = node;
                size++;
                //TODO запускаем перестроение
            } else if (key.compareTo(node.right.key) == 0)
                node.right.value = value;
            else {
                newNode = node.right;
                addFromPut(newNode, key, value);
            }
        }
    }

    public void delete(K key) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        //TODO - что дальше? Надо перестроение!
    }

    public V find(K key) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        if (key.compareTo(root.key) == 0)
            return root.value;
        Node<K, V> node = root;
        Node<K, V> newNode = keyAndValueSearch(node, key);
        return newNode.value;
    }

    private Node<K, V> keyAndValueSearch(Node<K, V> node, K key) {
        Node<K, V> newNode;
        int comparision = key.compareTo(node.key);
        if (comparision < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.left.key) == 0)
                newNode = node.left;
            else {
                newNode = node.left;
                return keyAndValueSearch(newNode, key);
            }
        } else {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.right.key) == 0)
                newNode = node.right;
            else {
                newNode = node.right;
                return keyAndValueSearch(newNode, key);
            }
        }
        return newNode;
    }

    public void change(K oldKey, K newKey) {
        if (oldKey == null || newKey == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        if (oldKey.compareTo(root.key) == 0) {
            root.key = newKey;
            //TODO запускаем перестроение
        }
        Node<K, V> node = root;
        keyReplacementFromChange(node, oldKey, newKey);
    }

    private void keyReplacementFromChange(Node<K, V> node, K oldKey, K newKey) {
        Node<K, V> newNode;
        int comparision = oldKey.compareTo(node.key);
        if (comparision < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else if (oldKey.compareTo(node.left.key) == 0) {
                node.left.key = newKey;
                //TODO запускаем перестроение
            } else {
                newNode = node.left;
                keyReplacementFromChange(newNode, oldKey, newKey);
            }
        } else {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else if (oldKey.compareTo(node.right.key) == 0) {
                node.right.key = newKey;
                //TODO запускаем перестроение
            } else {
                newNode = node.right;
                keyReplacementFromChange(newNode, oldKey, newKey);
            }
        }
    }

    private int currentHeight(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private int recalculateHeight(Node<K, V> node) {
        return Math.max(node.left.height, node.right.height) + 1;
    }

    private int balanceFactor(Node<K, V> node) {
        return node.left.height - node.right.height;
    }


    public static void main(String[] args) {
        AvlTree<Integer, String> test = new AvlTree<>();
        test.put(15, "*15*");
//        test.put(5, "newFive");
        test.put(12, "*12*");
        test.put(16, "*16*");
//        test.put(3, "newThree");
//        test.put(6, "newSix");
        test.put(40, "*40*");
        test.put(7, "*7*");
        test.put(10, "*10*");
        test.put(25, "*25*");
        test.put(2, "*2*");
        test.put(7, "*new-7*");
        test.put(14, "*14*");
        System.out.println(test.find(10));
        System.out.println(test.size);
        test.change(10, 11);
    }

}
