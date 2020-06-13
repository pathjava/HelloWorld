// Oleg Kiselev
// 07.06.2020, 19:30

package ru.progwards.java2.lessons.trees;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class AvlTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size = 0;
    private static final String NOT_EXIST = "The key does not exist!";
    private static final String IS_EMPTY = "AVL Tree is empty!";
    private static final String KEY_NULL = "The key cannot be null!";

    public static class Node<K extends Comparable<K>, V> { /* узел АВЛ дерева */
        private int height;
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }

        public void process(Consumer<AvlTree.Node<K, V>> consumer) { /* обход всего дерева */
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }
    }

    public void put(K key, V value) { /* добавление узла в дерево */
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        root = addFromPut(root, key, value);
        size++;
    }

    private Node<K, V> addFromPut(Node<K, V> node, K key, V value) {
        if (node == null) /* если узел null, создаем и возвращаем новый */
            return new Node<>(key, value);
        if (key.compareTo(node.key) < 0) /* если добавляемый ключ меньше ключа из node, идём в левое поддерево */
            node.left = addFromPut(node.left, key, value); /* рекурсивно создаем узел в левое поддерево */
        else if (key.compareTo(node.key) > 0) /* если больше, идем в правое поддерево */
            node.right = addFromPut(node.right, key, value); /* рекурсивно создаем узел в правом поддереве */
        else { /* в противном случае ключи равны */
            node.value = value; /* обновляем значения */
            return node;
        }
        node.height = recalculateHeight(node); /* пересчитываем высоту для узла */
        return rebuildBalanceTree(node); /* если необходимо, восстанавливаем баланс дерева */
    }

    public void delete(K key) { /* удаление узла из дерева */
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(key))
            throw new NoSuchElementException(NOT_EXIST);
        root = searchDeleteNode(root, key);
        size--;
    }

    private Node<K, V> searchDeleteNode(Node<K, V> node, K key) {
        if (key.compareTo(node.key) < 0) /* если ключ меньше node, ищем удаляемый узел в левом поддереве */
            node.left = searchDeleteNode(node.left, key);
        else if (key.compareTo(node.key) > 0) /* если ключ больше node, ищем удаляемый узел в правом поддереве */
            node.right = searchDeleteNode(node.right, key);
        else { /* если ключи равны */
            if (node.left == null)
                return node.right; /* возвращаем правый узел/null и в обратном ходе рекурсии удаляем крайний узел/лист */
            else if (node.right == null)
                return node.left; /* возвращаем левый узел/null и в обратном ходе рекурсии удаляем крайний узел/лист */
            else { /* если удаляемый узел имеет обоих потомков */
                Node<K, V> tempNode = node; /* кладем удаляемый узел во временный узел */
                node = searchMinKey(tempNode.right); /* находим минимальный правый узел */
                node.right = deleteMin(tempNode.right); /* удаляем минимальный узел и присваиваем новому узлу правое поддерево */
                node.left = tempNode.left; /* присваиваем новому узлу левое поддерево */
            }
        }
        node.height = recalculateHeight(node); /* пересчитываем высоту для узла */
        return rebuildBalanceTree(node); /* если необходимо, восстанавливаем баланс дерева */
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.height = recalculateHeight(node);
        return rebuildBalanceTree(node);
    }

    public K maxKey() {
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        return searchMaxKey(root).key;
    }

    private Node<K, V> searchMaxKey(Node<K, V> node) {
        return node.right == null ? node : searchMaxKey(node.right);
    }

    public K minKey() {
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        return searchMinKey(root).key;
    }

    private Node<K, V> searchMinKey(Node<K, V> node) {
        return node.left == null ? node : searchMinKey(node.left);
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
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(key))
            throw new NoSuchElementException(NOT_EXIST);
        Node<K, V> tempNode = searchValueFromFind(root, key);
        return tempNode.value;
    }

    private Node<K, V> searchValueFromFind(Node<K, V> node, K key) {
        if (node.key.compareTo(key) == 0)
            return node;
        if (key.compareTo(node.key) < 0) {
            return searchValueFromFind(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return searchValueFromFind(node.right, key);
        } else
            return node;
    }

    public void change(K oldKey, K newKey) {
        if (oldKey == null || newKey == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(oldKey))
            throw new NoSuchElementException(NOT_EXIST);
        V oldValue = find(oldKey);
        delete(oldKey);
        put(newKey, oldValue);
    }

    public void updateValue(K key, V newValue) {
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(key))
            throw new NoSuchElementException(NOT_EXIST);
        replacementValueFromUpdateValue(root, key, newValue);
    }

    private void replacementValueFromUpdateValue(Node<K, V> node, K key, V newValue) {
        if (key.compareTo(node.key) == 0)
            node.value = newValue;
        if (key.compareTo(node.key) < 0) {
            replacementValueFromUpdateValue(node.left, key, newValue);
        } else if (key.compareTo(node.key) > 0) {
            replacementValueFromUpdateValue(node.right, key, newValue);
        }
    }

    public boolean containsKey(K key) {
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        return checkContainsKey(root, key);
    }

    private boolean checkContainsKey(Node<K, V> node, K key) {
        if (key.compareTo(node.key) == 0)
            return true;
        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                return false;
            else
                return checkContainsKey(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                return false;
            else
                return checkContainsKey(node.right, key);
        }
        return false;
    }

    public void process(Consumer<AvlTree.Node<K, V>> consumer) {
        if (root != null)
            root.process(consumer);
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
        System.out.println(test.size);
//        System.out.println(test.find(14));
        test.delete(18);

        System.out.println(test.size);

        System.out.println(test.minKey());
        System.out.println(test.maxKey());

//        test.updateValue(21, "*21*");
//        System.out.println(test.find(31));

//        boolean result = test.containsKey(2);
//        System.out.println(result);
        test.process(System.out::println);
    }
}
