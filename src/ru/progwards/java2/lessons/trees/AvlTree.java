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

    public void delete(K key) { // TODO - добавить size-- после удаления узла
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");

        Node<K, V> node = root;
        searchNode(node, key);
    }

    private void searchNode(Node<K, V> node, K key) {
        Node<K, V> newNode;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("This key is not exist!");
            newNode = node.left;
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                throw new IllegalArgumentException("This key is not exist!");
            newNode = node.right;
        } else
            newNode = root;
        if (key.compareTo(newNode.key) == 0)
            rebuildNodes(newNode);
        else
            searchNode(newNode, key);
    }

    private void rebuildNodes(Node<K, V> node) {
        if (node.left != null)
            rebuildNodesLeft(node);
        else if (node.right != null)
            rebuildNodesRight(node);
        else
            root = null;
    }

    private void rebuildNodesLeft(Node<K, V> node) {
        Node<K, V> newNode;
        newNode = searchMaxKey(node.left);
        node.key = newNode.key;
        node.value = newNode.value;
        if (newNode.left != null) {
            if (newNode.left.key.compareTo(newNode.parent.key) > 0)
                newNode.parent.right = newNode.left;
            else
                newNode.parent.left = newNode.left;
            newNode.left.parent = newNode.parent;
        } else if (newNode.right == null)
            newNode.parent.left = null;
        else
            newNode.parent.right = null;
    }

    private void rebuildNodesRight(Node<K, V> node) {
        Node<K, V> newNode;
        newNode = searchMinKey(node.right);
        node.key = newNode.key;
        node.value = newNode.value;
        if (newNode.right != null) {
            if (newNode.right.key.compareTo(newNode.parent.key) < 0)
                newNode.parent.left = newNode.right;
            else
                newNode.parent.right = newNode.right;
            newNode.right.parent = newNode.parent;
        } else if (newNode.left == null)
            newNode.parent.right = null;
        else
            newNode.parent.left = null;
    }

    private Node<K, V> searchMaxKey(Node<K, V> node) {
        Node<K, V> newNode;
        if (node.right == null)
            return node;
        else {
            newNode = node.right;
            return searchMaxKey(newNode);
        }
    }

    private Node<K, V> searchMinKey(Node<K, V> node) {
        Node<K, V> newNode;
        if (node.left == null)
            return node;
        else {
            newNode = node.left;
            return searchMinKey(newNode);
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
        V oldValue = find(oldKey);
        delete(oldKey);
        put(newKey, oldValue);
    }

    public void changeValue(K key, V newValue) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        if (key.compareTo(root.key) == 0) // TODO - подумать и перенести это в основной метод замены значения
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
        test.put(21, "***");
        test.put(13, "***");
        test.put(29, "***");
        test.put(8, "***");
        test.put(18, "***");
        test.put(26, "***");
        test.put(32, "***");
        test.put(5, "***");
        test.put(11, "*11*");
        test.put(16, "***");
        test.put(20, "***");
        test.put(24, "***");
        test.put(28, "***");
        test.put(31, "***");
        test.put(33, "***");
        test.put(3, "***");
        test.put(7, "***");
        test.put(10, "***");
        test.put(12, "***");
        test.put(15, "***");
        test.put(17, "***");
        test.put(19, "***");
        test.put(23, "***");
        test.put(25, "***");
        test.put(27, "***");
        test.put(30, "***");
        test.put(2, "***");
        test.put(4, "***");
        test.put(6, "***");
        test.put(9, "***");
        test.put(14, "***");
        test.put(22, "***");
        test.put(1, "***");

        test.change(11, 34);

        test.delete(21);

//        System.out.println(test.find(26));
//        System.out.println(test.size);
//        test.changeValue(26, "*new-26*");
    }

}
