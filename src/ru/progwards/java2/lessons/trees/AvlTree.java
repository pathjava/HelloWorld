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
        root = addFromPut(root, key, value);
        size++;
    }

    private Node<K, V> addFromPut(Node<K, V> node, K key, V value) {
        if (node == null)
            return new Node<>(key, value);
        if (key.compareTo(node.key) < 0)
            node.left = addFromPut(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = addFromPut(node.right, key, value);
        else {
            node.value = value;
            return node;
        }
        node.height = recalculateHeight(node);
        return rebuildBalanceTree(node);
    }

    public void delete(K key) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        Node<K, V> node = root;
        searchDeleteNode(node, key);
        size--;
    }

    private void searchDeleteNode(Node<K, V> node, K key) {
        Node<K, V> tempNode;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("This key is not exist!");
            tempNode = node.left;
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                throw new IllegalArgumentException("This key is not exist!");
            tempNode = node.right;
        } else
            tempNode = root;
        if (key.compareTo(tempNode.key) == 0)
            rebuildDeleteNodes(tempNode);
        else
            searchDeleteNode(tempNode, key);
    }

    private void rebuildDeleteNodes(Node<K, V> node) {
        if (node.left != null)
            deleteNodesLeft(node);
        else if (node.right != null)
            deleteNodesRight(node);
        else
            deleteLeaf(node);
    }

    private void deleteNodesLeft(Node<K, V> node) {
        Node<K, V> tempNode;
        tempNode = searchMaxKey(node.left);
        node.key = tempNode.key;
        node.value = tempNode.value;
        if (tempNode.left != null) {
            if (tempNode.left.key.compareTo(tempNode.parent.key) > 0)
                tempNode.parent.right = tempNode.left;
            else
                tempNode.parent.left = tempNode.left;
            tempNode.left.parent = tempNode.parent;
        } else if (tempNode.right == null)
            tempNode.parent.left = null;
        else
            tempNode.parent.right = null;
        rebuildBalanceTree(tempNode);
    }

    private void deleteNodesRight(Node<K, V> node) {
        Node<K, V> tempNode;
        tempNode = searchMinKey(node.right);
        node.key = tempNode.key;
        node.value = tempNode.value;
        if (tempNode.right != null) {
            if (tempNode.right.key.compareTo(tempNode.parent.key) < 0)
                tempNode.parent.left = tempNode.right;
            else
                tempNode.parent.right = tempNode.right;
            tempNode.right.parent = tempNode.parent;
        } else if (tempNode.left == null)
            tempNode.parent.right = null;
        else
            tempNode.parent.left = null;
        rebuildBalanceTree(tempNode);
    }

    private void deleteLeaf(Node<K, V> node) {
        if (node.parent == null)
            root = null;
        else {
            if (node.key.compareTo(node.parent.key) < 0)
                node.parent.left = null;
            else
                node.parent.right = null;
        }
    }

    private Node<K, V> searchMaxKey(Node<K, V> node) {
        Node<K, V> tempNode;
        if (node.right == null)
            return node;
        else {
            tempNode = node.right;
            return searchMaxKey(tempNode);
        }
    }

    private Node<K, V> searchMinKey(Node<K, V> node) {
        Node<K, V> tempNode;
        if (node.left == null)
            return node;
        else {
            tempNode = node.left;
            return searchMinKey(tempNode);
        }
    }

    private Node<K, V> rebuildBalanceTree(Node<K, V> node) {
        if (getBalance(node) < -1) {
            if (getBalance(node.right) > 0)
                node.right = rightSmallRotate(node.right);
            node = leftSmallRotate(node);
        } else if (getBalance(node) > 1) {
            if (getBalance(node.left) < 0)
                node.left = leftSmallRotate(node.left);
            node = rightSmallRotate(node);
        }
        return node;
    }

    private int height(Node<K, V> node) {
        return node == null ? -1 : node.height;
    }

    private int recalculateHeight(Node<K, V> node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private int getBalance(Node<K, V> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node<K, V> leftSmallRotate(Node<K, V> node) {
        Node<K, V> tempNode = node.right;
        node.right = tempNode.left;
        tempNode.left = node;
        node.height = recalculateHeight(node);
        tempNode.height = recalculateHeight(tempNode);
        return tempNode;
    }

    private Node<K, V> rightSmallRotate(Node<K, V> node) {
        Node<K, V> tempNode = node.left;
        node.left = tempNode.right;
        tempNode.right = node;
        node.height = recalculateHeight(node);
        tempNode.height = recalculateHeight(tempNode);
        return tempNode;
    }

    public V find(K key) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        Node<K, V> tempNode = keyAndValueSearchFromFind(root, key);
        return tempNode.value;
    }

    private Node<K, V> keyAndValueSearchFromFind(Node<K, V> node, K key) {
        if (node.key.compareTo(key) == 0)
            return node;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else
                return keyAndValueSearchFromFind(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else
                return keyAndValueSearchFromFind(node.right, key);
        } else
            return node;
    }

    public void change(K oldKey, K newKey) {
        V oldValue = find(oldKey);
        delete(oldKey);
        put(newKey, oldValue);
    }

    public void updateValue(K key, V newValue) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        replacementValueFromUpdateValue(root, key, newValue);
    }

    private void replacementValueFromUpdateValue(Node<K, V> node, K key, V newValue) {
        if (node.key.compareTo(key) == 0)
            node.value = newValue;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else
                replacementValueFromUpdateValue(node.left, key, newValue);
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else
                replacementValueFromUpdateValue(node.right, key, newValue);
        }
    }


    public static void main(String[] args) {
        AvlTree<Integer, String> test = new AvlTree<>();
        test.put(50, "***");
        test.put(25, "***");
        test.put(75, "***");
        test.put(12, "***");
        test.put(37, "***");
        test.put(53, "***");
        test.put(87, "***");
        test.put(6, "***");
        test.put(18, "***");
        test.put(31, "***");
        test.put(43, "***");
        test.put(47, "***");
        test.put(59, "***");
        test.put(81, "***");
        test.put(93, "*93*");
        test.put(15, "***");
        test.put(21, "***");
        test.put(14, "*14*");
        test.put(13, "***");
        test.put(23, "***");


//        test.change(11, 34);

//        System.out.println(test.find(14));
//        test.delete(17);
//
//        System.out.println(test.size);
        test.updateValue(21, "*21*");
        System.out.println(test.find(21));
    }

}
