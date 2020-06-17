// Oleg Kiselev
// 16.06.2020, 17:32

package ru.progwards.java2.lessons.annotation;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Класс AvlTreeJavaDoc, реализующий АВЛ дерево
 *
 * @param <K> ключ АВЛ дерева, обязательно реализующий интерфейс Comparable
 * @param <V> значение узла
 * @version 1.0
 * @see ru.progwards.java2.lessons.trees.AvlTree АВЛ дерево
 * @see ru.progwards.java2.lessons.trees.AVLTreeAndTreeMapTest Тест, сравнивающий АВЛ дерево и TreeMap
 */
public class AvlTreeJavaDoc<K extends Comparable<K>, V> {
    /**
     * root - корневой узел дерева {@link AvlTreeJavaDoc}
     */
    public Node<K, V> root;
    /**
     * size - размер дерева {@link AvlTreeJavaDoc}
     */
    public int size = 0;
    /**
     * NOT_EXIST - сообщение "The key does not exist!" исключения NoSuchElementException, если ключа нет в {@link AvlTreeJavaDoc}
     */
    public static final String NOT_EXIST = "The key does not exist!";
    /**
     * IS_EMPTY - сообщение "AVL Tree is empty!" исключения NoSuchElementException, если дерево {@link AvlTreeJavaDoc} пустое
     */
    public static final String IS_EMPTY = "AVL Tree is empty!";
    /**
     * KEY_NULL - сообщение "The key cannot be null!" исключения IllegalArgumentException, если {@link AvlTreeJavaDoc.Node#key} null
     */
    public static final String KEY_NULL = "The key cannot be null!";

    /**
     * Класс Node, реализующий узел {@link AvlTreeJavaDoc}
     *
     * @param <K> ключ, обязательно реализующий интерфейс Comparable
     * @param <V> значение узла
     */
    public static class Node<K extends Comparable<K>, V> {
        /**
         * height - высота {@link AvlTreeJavaDoc}, рассчитываемая методом {@link AvlTreeJavaDoc#recalculateHeight(Node)}
         */
        public int height;
        /**
         * key -  ключ {@link AvlTreeJavaDoc}, обязательно реализующий интерфейс Comparable
         */
        public final K key;
        /**
         * value - значение узла {@link AvlTreeJavaDoc.Node} в {@link AvlTreeJavaDoc}
         */
        public V value;
        /**
         * left - ссылка на левый узел {@link AvlTreeJavaDoc.Node} потомка
         */
        public Node<K, V> left;
        /**
         * right - ссылка на правый узел {@link AvlTreeJavaDoc.Node} потомка
         */
        public Node<K, V> right;

        /**
         * Конструктора класса {@link AvlTreeJavaDoc.Node}
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
         * Метод переопределяет toString {@link Object#toString()}
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
         * @param consumer принимает в качестве параметра System.out::println
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
     * Метод инициализирует размещение пары ключ/значение в {@link AvlTreeJavaDoc},
     * и передаёт размещение в метод {@link #addFromPut(Node, Comparable, Object)}
     *
     * @param key   ключ {@link AvlTreeJavaDoc.Node#key}
     * @param value значение {@link AvlTreeJavaDoc.Node#value}
     * @throws IllegalArgumentException изначально метод проверяет {@link AvlTreeJavaDoc.Node#key} на null и если true,
     *                                  выбрасывает исключение с сообщением {@link AvlTreeJavaDoc#KEY_NULL}
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
     * @param node  узел {@link AvlTreeJavaDoc#root}, полученный из метода {@link #put(Comparable, Object)},
     *              указывающий на начало {@link AvlTreeJavaDoc}
     * @param key   ключ {@link AvlTreeJavaDoc.Node#key}
     * @param value значение {@link AvlTreeJavaDoc.Node#value}
     * @return возвращает сбалансированное дерево {@link #rebuildBalanceTree(Node)}
     */
    public Node<K, V> addFromPut(Node<K, V> node, K key, V value) {
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

    /**
     * Метод инициализирует удаление узла {@link AvlTreeJavaDoc.Node} по ключу {@link AvlTreeJavaDoc.Node#key} из {@link AvlTreeJavaDoc}
     * и передаёт удаление узла в метод {@link #searchDeleteNode(Node, Comparable)}
     * Изначально метод проверяет {@link AvlTreeJavaDoc.Node#key} на null и если true, выбрасывает исключения
     *
     * @param key ключ {@link AvlTreeJavaDoc.Node#key}
     * @throws IllegalArgumentException с сообщением {@link #KEY_NULL}
     * @throws NoSuchElementException   с сообщением {@link #IS_EMPTY}
     * @throws NoSuchElementException   с сообщением {@link #NOT_EXIST}
     */
    public void delete(K key) {
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
     * Метод ищет и удаляет узел {@link AvlTreeJavaDoc.Node} по ключу {@link AvlTreeJavaDoc.Node#key}
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @param key  ключ {@link AvlTreeJavaDoc.Node#key}
     * @return возвращает сбалансированное дерево {@link #rebuildBalanceTree(Node)}
     */
    public Node<K, V> searchDeleteNode(Node<K, V> node, K key) {
        if (key.compareTo(node.key) < 0)
            node.left = searchDeleteNode(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = searchDeleteNode(node.right, key);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                Node<K, V> tempNode = node;
                node = searchMinKey(tempNode.right);
                node.right = deleteMin(tempNode.right);
                node.left = tempNode.left;
            }
        }
        node.height = recalculateHeight(node);
        return rebuildBalanceTree(node);
    }

    /**
     * Метод вызывается из {@link #searchDeleteNode(Node, Comparable)} и удает минимальный узел, ранее присвоенный удаляемому
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает сбалансированное дерево {@link #rebuildBalanceTree(Node)}
     */
    public Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.height = recalculateHeight(node);
        return rebuildBalanceTree(node);
    }

    /**
     * Метод инициализирует поиск максимального ключа в методе {@link #searchMaxKey(Node)}
     *
     * @return возвращает максимальный ключ в {@link AvlTreeJavaDoc}
     */
    public K maxKey() {
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        return searchMaxKey(root).key;
    }

    /**
     * Метод ищет максимальный ключ в {@link AvlTreeJavaDoc}
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает максимальный ключ в метод {@link #maxKey()}
     */
    public Node<K, V> searchMaxKey(Node<K, V> node) {
        return node.right == null ? node : searchMaxKey(node.right);
    }

    /**
     * Метод инициализирует поиск минимального ключа в методе {@link #searchMinKey(Node)}
     *
     * @return возвращает минимальный ключ в {@link AvlTreeJavaDoc}
     */
    public K minKey() {
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        return searchMinKey(root).key;
    }

    /**
     * Метод ищет минимальный ключ в {@link AvlTreeJavaDoc}
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает минимальный ключ в метод {@link #minKey()}
     */
    public Node<K, V> searchMinKey(Node<K, V> node) {
        return node.left == null ? node : searchMinKey(node.left);
    }

    /**
     * Метод перестраивает баланс {@link AvlTreeJavaDoc} при условии высоты == 2,
     * вызывая малый правый поворот {@link #rightSmallRotate(Node)} или малый левый поворот {@link #leftSmallRotate(Node)}
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает сбалансированный узел {@link AvlTreeJavaDoc.Node}
     */
    public Node<K, V> rebuildBalanceTree(Node<K, V> node) {
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

    /**
     * Метод возвращает высоту текущего узла
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает высоту текущего узла
     */
    public int height(Node<K, V> node) {
        return node == null ? -1 : node.height;
    }

    /**
     * Метод пересчитывает высоту после разворота {@link #rebuildBalanceTree(Node)} узла
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает пересчитанную высоту
     */
    public int recalculateHeight(Node<K, V> node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Метод рассчитывает высоту узла по высоте его потомков
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает высоту узла
     */
    public int getBalance(Node<K, V> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Метод делает левый малый поворот
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает сбалансированный узел
     */
    public Node<K, V> leftSmallRotate(Node<K, V> node) {
        Node<K, V> tempNode = node.right;
        node.right = tempNode.left;
        tempNode.left = node;
        node.height = recalculateHeight(node);
        tempNode.height = recalculateHeight(tempNode);
        return tempNode;
    }

    /**
     * Метод делает правый малый поворот
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @return возвращает сбалансированный узел
     */
    public Node<K, V> rightSmallRotate(Node<K, V> node) {
        Node<K, V> tempNode = node.left;
        node.left = tempNode.right;
        tempNode.right = node;
        node.height = recalculateHeight(node);
        tempNode.height = recalculateHeight(tempNode);
        return tempNode;
    }

    /**
     * Метод инициализирует поиск значения по ключу и передает поиск в метод {@link #searchValueFromFind(Node, Comparable)}
     * Изначально метод проверяет {@link AvlTreeJavaDoc.Node#key} на null и если true, выбрасывает исключения
     *
     * @param key ключ {@link AvlTreeJavaDoc.Node#key}
     * @return возвращает значение узла {@link AvlTreeJavaDoc.Node#value} по ключу
     * @throws IllegalArgumentException с сообщением {@link #KEY_NULL}
     * @throws NoSuchElementException   с сообщением {@link #IS_EMPTY}
     */
    public V find(K key) {
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
//        if (!containsKey(key))
//            throw new NoSuchElementException(NOT_EXIST);
        return searchValueFromFind(root, key).value; // TODO - если ключ не существует, сделать возврат null
    }

    /**
     * Метод ищет по ключу и возвращает значение найденного узла в метод {@link #find(Comparable)}
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @param key  ключ {@link AvlTreeJavaDoc.Node#key}
     * @return возвращает значение найденного узла в метод {@link #find(Comparable)}
     */
    public Node<K, V> searchValueFromFind(Node<K, V> node, K key) {
        if (key.compareTo(node.key) == 0)
            return node;
        if (key.compareTo(node.key) < 0)
            return searchValueFromFind(node.left, key);
        else
            return searchValueFromFind(node.right, key);
    }

    /**
     * Метод для замены старого ключа на новый, с сохранением значения
     * Изначально метод проверяет {@link AvlTreeJavaDoc.Node#key} на null и если true, выбрасывает исключения
     *
     * @param oldKey существующий в АВЛ дереве заменяемый ключ {@link AvlTreeJavaDoc.Node#key} //TODO
     * @param newKey новый ключ, добавляемый в {@link AvlTreeJavaDoc} вместо oldKey
     * @throws IllegalArgumentException с сообщением {@link #KEY_NULL}
     * @throws NoSuchElementException   с сообщением {@link #IS_EMPTY}
     * @throws NoSuchElementException   с сообщением {@link #NOT_EXIST}
     */
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

    /**
     * Метод инициализирует замену старого значения в узле на новое
     * и вызывает метод {@link #replacementValueFromUpdateValue(Node, Comparable, Object)}
     * Изначально метод проверяет {@link AvlTreeJavaDoc.Node#key} на null и если true, выбрасывает исключения
     *
     * @param key      ключ {@link AvlTreeJavaDoc.Node#key}, значение которого требуется заменить
     * @param newValue новое значение узла {@link AvlTreeJavaDoc.Node}
     * @throws IllegalArgumentException с сообщением {@link #KEY_NULL}
     * @throws NoSuchElementException   с сообщением {@link #IS_EMPTY}
     * @throws NoSuchElementException   с сообщением {@link #NOT_EXIST}
     */
    public void updateValue(K key, V newValue) {
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        if (root == null)
            throw new NoSuchElementException(IS_EMPTY);
        if (!containsKey(key))
            throw new NoSuchElementException(NOT_EXIST);
        replacementValueFromUpdateValue(root, key, newValue);
    }

    /**
     * Метод обновляет значение узла {@link AvlTreeJavaDoc.Node},
     * полученное из метода {@link #updateValue(Comparable, Object)}
     *
     * @param node     узел {@link AvlTreeJavaDoc.Node}
     * @param key      ключ {@link AvlTreeJavaDoc.Node#key}
     * @param newValue новое значение узла {@link AvlTreeJavaDoc.Node}
     */
    public void replacementValueFromUpdateValue(Node<K, V> node, K key, V newValue) {
        if (key.compareTo(node.key) == 0)
            node.value = newValue;
        if (key.compareTo(node.key) < 0)
            replacementValueFromUpdateValue(node.left, key, newValue);
        else if (key.compareTo(node.key) > 0)
            replacementValueFromUpdateValue(node.right, key, newValue);
    }

    /**
     * Метод инициализирует проверку наличия клюа в дереве, вызывая метод {@link #checkContainsKey(Node, Comparable)}
     *
     * @param key ключ {@link AvlTreeJavaDoc.Node#key}
     * @return возвращает true или false
     */
    public boolean containsKey(K key) {
        if (key == null)
            throw new IllegalArgumentException(KEY_NULL);
        return checkContainsKey(root, key);
    }

    /**
     * Метод проверяет наличие ключа в {@link AvlTreeJavaDoc} и возвращает true или false в метод {@link #containsKey(Comparable)}
     *
     * @param node узел {@link AvlTreeJavaDoc.Node}
     * @param key  ключ {@link AvlTreeJavaDoc.Node#key}
     * @return возвращает true или false
     */
    public boolean checkContainsKey(Node<K, V> node, K key) {
        if (node == null)
            return false;
        if (key.compareTo(node.key) < 0)
            return checkContainsKey(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return checkContainsKey(node.right, key);
        else
            return true;
    }

    /**
     * Метод обходит все узлы {@link AvlTreeJavaDoc}
     *
     * @param consumer {@link Consumer}
     */
    public void process(Consumer<AvlTreeJavaDoc.Node<K, V>> consumer) {
        if (root != null)
            root.process(consumer);
    }

    /**
     * Метод очищает {@link AvlTreeJavaDoc}
     *
     * @see AvlTreeJavaDoc#root
     * @see AvlTreeJavaDoc#size
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Метод возвращает размер {@link AvlTreeJavaDoc}
     *
     * @return возвращает размер {@link AvlTreeJavaDoc#size}
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
