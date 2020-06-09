// Oleg Kiselev
// 07.06.2020, 19:30

package ru.progwards.java2.lessons.trees;

public class AvlTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size = 0;

    public static class Node<K extends Comparable<K>, V> {
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

        Node<K, V> newNode;
        if (key.compareTo(root.key) == 0) {
            if (root.left == null && root.right == null) {
                root = null;
                size--;
            } else
                rebuildNodes(root, key);
        } else {
            Node<K, V> node = root;
            newNode = keyAndValueSearch(node, key);
            rebuildNodes(newNode, key);
        }
    }

    private void rebuildNodes(Node<K, V> node, K key) {
        Node<K, V> tempNode;
        if (node.left == null && node.right == null)
            if (key.compareTo(node.parent.key) < 0)
                node.parent.left = null;
            else
                node.parent.right = null;
        else {
            if (balanceFactor(node) > 0)
                tempNode = searchMaxKey(node, key);
            else
                tempNode = searchMinKey(node, key);
            assert tempNode != null;
            node.key = tempNode.key;
            node.value = tempNode.value;
            tempNode = null;
        }
    }

    private Node<K, V> searchMaxKey(Node<K, V> node, K key) {
        Node<K, V> newNode;
        if (key.compareTo(node.left.key) > 0)
            if (node.right != null) {
                newNode = node.right;
                return searchMaxKey(newNode, key);
            }
        return null;
    }

    private Node<K, V> searchMinKey(Node<K, V> node, K key) {
        Node<K, V> newNode = null;
        if (key.compareTo(node.right.key) < 0)
            if (node.left != null) {
                newNode = node.left;
                if (newNode.left != null)
                    return searchMinKey(newNode, key);
            }
        return newNode;
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

    // TODO - сделать замену через удаление и добавление (с сохранением value удаляемого ключа)
    public void change(K oldKey, K newKey) {

    }

    public void changeValue(K key, V newValue) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        if (key.compareTo(root.key) == 0)
            root.value = newValue;
        Node<K, V> node = root;
        replacementValueFromChangeValue(node, key, newValue);
    }

    private void replacementValueFromChangeValue(Node<K, V> node, K key, V newValue) {
        Node<K, V> newNode;
        int comparision = key.compareTo(node.key);
        if (comparision < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.left.key) == 0)
                node.left.value = newValue;
            else {
                newNode = node.left;
                replacementValueFromChangeValue(newNode, key, newValue);
            }
        } else {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.right.key) == 0)
                node.right.value = newValue;
            else {
                newNode = node.right;
                replacementValueFromChangeValue(newNode, key, newValue);
            }
        }
    }


    public static void main(String[] args) {
        AvlTree<Integer, String> test = new AvlTree<>();
        test.put(32, "*32*");
        test.put(45, "*45*");
        test.put(25, "*25*");
        test.put(27, "*27*");
        test.put(29, "*29*");
        test.put(28, "*28*");
        test.put(38, "*38*");
        test.put(50, "*50*");
        test.put(47, "*47*");
        test.put(26, "*26*");
        test.put(23, "*23*");
        test.put(24, "*24*");
        test.put(19, "*19*");
        test.put(20, "*20*");
        test.put(17, "*17*");
        test.put(21, "*21*");
        test.delete(27);
//        System.out.println(test.find(26));
//        System.out.println(test.size);
//        test.changeValue(26, "*new-26*");
    }

}
