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
            } else if (key.compareTo(node.left.key) == 0)
                node.left.value = value;
            else {
                newNode = node.left;
                addFromPut(newNode, key, value);
                rebuildBalanceTree(newNode);
            }
        } else {
            if (node.right == null) {
                newNode = new Node<>(key, value);
                node.right = newNode;
                newNode.parent = node;
                size++;
            } else if (key.compareTo(node.right.key) == 0)
                node.right.value = value;
            else {
                newNode = node.right;
                addFromPut(newNode, key, value);
                rebuildBalanceTree(newNode);
            }
        }
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

    private void rebuildBalanceTree(Node<K, V> node) {
        recalculateHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (height(node) - height(node.parent.right) == 2)
                leftSmallRotate(node);
            else
                rightBigRotate(node);
        } else if (balance < -1) {
            if (height(node.left.left) > height(node.left.right))
                rightSmallRotate(node);
            else
                leftBigRotate(node);
        }
    }

    private int height(Node<K, V> node) {
        return node == null ? -1 : node.height;
    }

    private void recalculateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(Node<K, V> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node<K, V> leftSmallRotate(Node<K, V> node) {
        Node<K, V> tempNode;
        tempNode = node.right;
        node.right = tempNode.left;
        tempNode.right = node;
        recalculateHeight(node);
        recalculateHeight(tempNode);
        return tempNode;
    }

    private void leftBigRotate(Node<K, V> node) {
        node.left = leftSmallRotate(node.left);
        rightSmallRotate(node);
    }

    private Node<K, V> rightSmallRotate(Node<K, V> node) {
        Node<K, V> tempNode;
        tempNode = node.left;
        node.left = tempNode.right;
        tempNode.right = node;
        recalculateHeight(node);
        recalculateHeight(tempNode);
        return tempNode;
    }

    private void rightBigRotate(Node<K, V> node) {
        node.right = rightSmallRotate(node.right);
        leftSmallRotate(node);
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
        test.put(3, "***");
        test.put(2, "***");


//        test.change(11, 34);

//        System.out.println(test.find(31));
//        test.delete(17);
//
//        System.out.println(test.size);
//        test.changeValue(20, "*20*");
    }

}
