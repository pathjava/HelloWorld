// Oleg Kiselev
// 16.06.2020, 17:32

package ru.progwards.java2.lessons.annotation;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Класс AvlTreeJavaDoc, реализующий АВЛ дерево
 *
 * @param <K> ключ АВЛ дерева, обязательно реализующий интерфейм {@link Comparable}
 * @param <V> значение узла
 * @version 1.0
 * @see ru.progwards.java2.lessons.trees.AvlTree АВЛ дерево
 * @see ru.progwards.java2.lessons.trees.AVLTreeAndTreeMapTest Тест, сравнивающий АВЛ дерево и {@link java.util.TreeMap}
 */
public class AvlTreeJavaDoc<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size = 0;
    private static final String NOT_EXIST = "The key does not exist!";
    private static final String IS_EMPTY = "AVL Tree is empty!";
    private static final String KEY_NULL = "The key cannot be null!";

    /**
     * Класс Node, реализующий узел АВЛ дерева
     *
     * @param <K> ключ, обязательно реализующий интерфейм {@link Comparable}
     * @param <V> значение узла
     */
    public static class Node<K extends Comparable<K>, V> {
        private int height;
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        /**
         * Конструктора класса {@link Node}
         *
         * @param key   инициализация ключа
         * @param value инициализация значения
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }

        /**
         * Метод, переопределяющий toString {@link #toString()}
         *
         * @return возвращает ключ и значение в удобочитаемом виде
         */
        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }

        /**
         * Метод для обхода по всему дереву, основанный на функциональном интерфейсе {@link Consumer}
         * Consumer декларирует абстрактный метод accept, который принимает объект и выполняет над ним требуемые действия.
         *
         * @param consumer
         */
        public void process(Consumer<AvlTreeJavaDoc.Node<K, V>> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }
    }

    /**
     * Метод put инициализирует размещение пары ключ/значение в {@link AvlTreeJavaDoc}
     * Изначально метод проверяет {@link Node#key} на null и если true,
     * выбрасывает исключение {@link IllegalArgumentException} с сообщением {@link #KEY_NULL}
     *
     * @param key   {@link Node#key}
     * @param value {@link Node#value}
     */
    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        root = addFromPut(root, key, value);
        size++;
    }

    /**
     * Метод addFromPut принимает узел из метода {@link #put(Comparable, Object)} и размещает его в {@link AvlTreeJavaDoc}
     *
     * @param node  {@link AvlTreeJavaDoc#root} узел, полученный из метода {@link #put(Comparable, Object)},
     *              указывающий на начало {@link AvlTreeJavaDoc}
     * @param key   {@link Node#key}
     * @param value {@link Node#value}
     * @return возвращает сбалансированное дерево {@link #rebuildBalanceTree(Node)}
     */
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

    /**
     * Метод, инициализирующий удаление узла {@link Node} по ключу {@link Node#key} из {@link AvlTreeJavaDoc}
     * Изначально метод проверяет {@link Node#key} на null и если true,
     *      выбрасывает исключения {@link IllegalArgumentException} с сообщением {@link #KEY_NULL},
     *      {@link NoSuchElementException} с сообщением {@link #IS_EMPTY},
     *      {@link NoSuchElementException} с сообщением {@link #NOT_EXIST}
     *
     * @param key {@link Node#key}
     */
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

    /**
     * Метод, ищущий, удаляющий узел {@link Node} по ключу {@link Node#key}
     *
     * @param node {@link Node}
     * @param key  {@link Node#key}
     * @return возвращает сбалансированное дерево {@link #rebuildBalanceTree(Node)}
     */
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

    /**
     * @param node {@link Node}
     * @return возвращает сбалансированное дерево {@link #rebuildBalanceTree(Node)}
     */
    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) /* если левый узел null */
            return node.right; /* возвращаемый правый null - так как справа null */
        node.left = deleteMin(node.left); /* присваиваем слева null предку левого крайнего потомка - таким образом удаляя крайний лист */
        node.height = recalculateHeight(node); /* пересчитываем высоту для узла */
        return rebuildBalanceTree(node); /* если необходимо, восстанавливаем баланс дерева */
    }

    /**
     * @return
     */
    public K maxKey() { /* так как были написаны методы поиска макс/мин ключа удаляемого узла, добавил метод поиска макс/мин ключей дерева */
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        return searchMaxKey(root).key;
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private Node<K, V> searchMaxKey(Node<K, V> node) { /* если справа null, возвращаем узел как крайний, либо рекурсивно проверяем следующий */
        return node.right == null ? node : searchMaxKey(node.right);
    }

    /**
     * @return
     */
    public K minKey() {
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        return searchMinKey(root).key;
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private Node<K, V> searchMinKey(Node<K, V> node) { /* тоже, что и searchMaxKey, только ищем минимальный кей */
        return node.left == null ? node : searchMinKey(node.left);
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private Node<K, V> rebuildBalanceTree(Node<K, V> node) {
        if (getBalance(node) < -1) { /* если баланс < -1, а значит == 2 */
            if (getBalance(node.right) > 0) /* если баланс больше 0 */
                node.right = rightSmallRotate(node.right); /* то делаем большое вращение rightSmallRotate + leftSmallRotate */
            node = leftSmallRotate(node); /* в противном случае малое левое */
        } else if (getBalance(node) > 1) { /* тоже самое, но наоборот */
            if (getBalance(node.left) < 0)
                node.left = leftSmallRotate(node.left);
            node = rightSmallRotate(node);
        }
        return node;
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private int height(Node<K, V> node) { /* значение высоты узла - если null, то -1, в противном случае возвращаем имеющуюся */
        return node == null ? -1 : node.height;
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private int recalculateHeight(Node<K, V> node) { /* пересчитываем высоту узла по значениям правого и левого поддеревьев */
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private int getBalance(Node<K, V> node) { /* считаем баланс узла */
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private Node<K, V> leftSmallRotate(Node<K, V> node) { /* левое малое вращение */
        Node<K, V> tempNode = node.right;
        node.right = tempNode.left;
        tempNode.left = node;
        node.height = recalculateHeight(node);
        tempNode.height = recalculateHeight(tempNode);
        return tempNode;
    }

    /**
     * @param node {@link Node}
     * @return
     */
    private Node<K, V> rightSmallRotate(Node<K, V> node) { /* правое малое вращение */
        Node<K, V> tempNode = node.left;
        node.left = tempNode.right;
        tempNode.right = node;
        node.height = recalculateHeight(node);
        tempNode.height = recalculateHeight(tempNode);
        return tempNode;
    }

    /**
     * Изначально метод проверяет {@link Node#key} на null и если true,
     *      выбрасывает исключения {@link IllegalArgumentException} с сообщением {@link #KEY_NULL},
     *      {@link NoSuchElementException} с сообщением {@link #IS_EMPTY}
     *
     * @param key {@link Node#key}
     * @return
     */
    public V find(K key) { /* поиск значения по ключу */
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
//        if (!containsKey(key))
//            throw new NoSuchElementException(NOT_EXIST);
        return searchValueFromFind(root, key).value; // TODO - если ключ не существует, сделать возврат null
    }

    /**
     * @param node {@link Node}
     * @param key  {@link Node#key}
     * @return
     */
    private Node<K, V> searchValueFromFind(Node<K, V> node, K key) {
        if (key.compareTo(node.key) == 0) /* если искомый ключ и ключ узла равны, возвращаем узел */
            return node;
        if (key.compareTo(node.key) < 0) /* в противном случае определяем в какое поддерево идти */
            return searchValueFromFind(node.left, key); /* рекурсивно возвращаем следующий узел */
        else
            return searchValueFromFind(node.right, key);
    }

    /**
     * Изначально метод проверяет {@link Node#key} на null и если true,
     *      выбрасывает исключения {@link IllegalArgumentException} с сообщением {@link #KEY_NULL},
     *      {@link NoSuchElementException} с сообщением {@link #IS_EMPTY},
     *      {@link NoSuchElementException} с сообщением {@link #NOT_EXIST}
     *
     * @param oldKey {@link Node#key} //TODO
     * @param newKey
     */
    public void change(K oldKey, K newKey) { /* меняем старый ключ на новый */
        if (oldKey == null || newKey == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(oldKey))
            throw new NoSuchElementException(NOT_EXIST);
        V oldValue = find(oldKey); /* сохраняем значение старого ключа */
        delete(oldKey); /* удаляем старый ключ */
        put(newKey, oldValue); /* добавляем новый ключ со значением от старого ключа */
    }

    /**
     * Изначально метод проверяет {@link Node#key} на null и если true,
     *      выбрасывает исключения {@link IllegalArgumentException} с сообщением {@link #KEY_NULL},
     *      {@link NoSuchElementException} с сообщением {@link #IS_EMPTY},
     *      {@link NoSuchElementException} с сообщением {@link #NOT_EXIST}
     *
     * @param key      {@link Node#key}
     * @param newValue
     */
    public void updateValue(K key, V newValue) { /* обновляем значение по ключу */
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(key))
            throw new NoSuchElementException(NOT_EXIST);
        replacementValueFromUpdateValue(root, key, newValue);
    }

    /**
     * @param node     {@link Node}
     * @param key      {@link Node#key}
     * @param newValue
     */
    private void replacementValueFromUpdateValue(Node<K, V> node, K key, V newValue) {
        if (key.compareTo(node.key) == 0) /* есл искомый ключ и ключ узла равны, обновляем значение */
            node.value = newValue;
        if (key.compareTo(node.key) < 0) /* в противном случае определяем в какое поддерево идти */
            replacementValueFromUpdateValue(node.left, key, newValue); /* рекурсивно вызываем следующий узел */
        else if (key.compareTo(node.key) > 0)
            replacementValueFromUpdateValue(node.right, key, newValue);
    }

    /**
     * @param key {@link Node#key}
     * @return
     */
    public boolean containsKey(K key) { /* проверяем, есть ли такой ключ */
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        return checkContainsKey(root, key);
    }

    /**
     * @param node {@link Node}
     * @param key  {@link Node#key}
     * @return
     */
    private boolean checkContainsKey(Node<K, V> node, K key) {
        if (node == null)
            return false;
        if (key.compareTo(node.key) < 0) /* определяем в какое поддерево идти */
            return checkContainsKey(node.left, key); /* рекурсивно вызываем следующий узел */
        else if (key.compareTo(node.key) > 0)
            return checkContainsKey(node.right, key);
        else
            return true;
    }

    /**
     * @param consumer {@link Consumer}
     */
    public void process(Consumer<AvlTreeJavaDoc.Node<K, V>> consumer) { /* метод обхода дерева */
        if (root != null)
            root.process(consumer);
    }

    /**
     * @see AvlTreeJavaDoc#root
     * @see AvlTreeJavaDoc#size
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @return {@link AvlTreeJavaDoc#size}
     */
    public int size() {
        return size;
    }


    public static void main(String[] args) {
        AvlTreeJavaDoc<Integer, String> test = new AvlTreeJavaDoc<>();
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
        test.put(1, "*1*");


        test.change(11, 34);
        System.out.println(test.size);
        System.out.println(test.find(1));
        test.delete(33);

        System.out.println(test.size);

        System.out.println(test.minKey());
        System.out.println(test.maxKey());

        test.updateValue(21, "*21*");
        System.out.println(test.find(31));

        boolean result = test.containsKey(2);
        System.out.println(result);
        test.process(System.out::println);

        test.clear();
        System.out.println(test.size);
    }
}
