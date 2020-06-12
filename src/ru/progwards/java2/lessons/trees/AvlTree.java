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
        if (key.compareTo(node.key) < 0) {
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

        Node<K, V> node = root;
        searchNode(node, key);
        size--;
    }

    private void searchNode(Node<K, V> node, K key) {
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
            rebuildNodes(tempNode);
        else
            searchNode(tempNode, key);
    }

    private void rebuildNodes(Node<K, V> node) {
        if (node.left != null)
            removeNodesLeft(node);
        else if (node.right != null)
            removeNodesRight(node);
        else
            removeLeaf(node);
    }

    private void removeNodesLeft(Node<K, V> node) {
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
    }

    private void removeNodesRight(Node<K, V> node) {
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
    }

    private void removeLeaf(Node<K, V> node) {
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

    private void balanceTree(Node<K, V> node) {

    }

    private int currentHeight(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private int recalculateHeight(Node<K, V> node) {
        return Math.max(currentHeight(node.left), currentHeight(node.right)) + 1;
    }

    private int balanceFactor(Node<K, V> node) {
        return currentHeight(node.left) - currentHeight(node.right);
    }

    private Node<K, V> leftSmallRotate(Node<K, V> node) {
        Node<K, V> rebuildNodeOne = node.right;
        Node<K, V> rebuildNodeTwo = rebuildNodeOne.left;
        rebuildNodeOne.left = node;
        node.right = rebuildNodeTwo;

        return rebuildNodeOne;
    }

    private Node<K, V> leftBigRotate(Node<K, V> node) {
        node.left = leftSmallRotate(node.left);
        return rightSmallRotate(node);
    }

    private Node<K, V> rightSmallRotate(Node<K, V> node) {
        Node<K, V> rebuildNodeOne = node.left;
        Node<K, V> rebuildNodeTwo = rebuildNodeOne.right;
        rebuildNodeOne.right = node;
        node.left = rebuildNodeTwo;

        return rebuildNodeOne;
    }

    private Node<K, V> rightBigRotate(Node<K, V> node) {
        node.right = rightSmallRotate(node.right);
        return leftSmallRotate(node);
    }

    public V find(K key) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be null!");
        if (root == null)
            throw new IllegalArgumentException("AVL Tree is empty!");
        Node<K, V> node = root;
        Node<K, V> tempNode = keyAndValueSearchFromFind(node, key);
        return tempNode.value;
    }

    private Node<K, V> keyAndValueSearchFromFind(Node<K, V> node, K key) {
        Node<K, V> tempNode;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.left.key) == 0)
                tempNode = node.left;
            else {
                tempNode = node.left;
                return keyAndValueSearchFromFind(tempNode, key);
            }
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.right.key) == 0)
                tempNode = node.right;
            else {
                tempNode = node.right;
                return keyAndValueSearchFromFind(tempNode, key);
            }
        } else
            return root;
        return tempNode;
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
        Node<K, V> node = root;
        replacementValueFromChangeValue(node, key, newValue);
    }

    private void replacementValueFromChangeValue(Node<K, V> node, K key, V newValue) {
        Node<K, V> tempNode;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.left.key) == 0)
                node.left.value = newValue;
            else {
                tempNode = node.left;
                replacementValueFromChangeValue(tempNode, key, newValue);
            }
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                throw new IllegalArgumentException("Key not found!");
            else if (key.compareTo(node.right.key) == 0)
                node.right.value = newValue;
            else {
                tempNode = node.right;
                replacementValueFromChangeValue(tempNode, key, newValue);
            }
        } else
            root.value = newValue;
    }


    public static void main(String[] args) {
        AvlTree<Integer, String> test = new AvlTree<>();
        test.put(21, "*21*");
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
        test.put(31, "*31*");
        test.put(33, "***");
        test.put(3, "***");
        test.put(7, "***");
        test.put(10, "***");
        test.put(12, "***");
        test.put(15, "***");
        test.put(17, "*17*");
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

//        test.change(11, 34);

        System.out.println(test.find(31));
        test.delete(17);

        System.out.println(test.size);
        test.changeValue(20, "*20*");
    }

}
