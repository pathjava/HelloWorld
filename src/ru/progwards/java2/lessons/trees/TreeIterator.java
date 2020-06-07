// Oleg Kiselev
// 05.06.2020, 21:09

package ru.progwards.java2.lessons.trees;


public class TreeIterator<K extends Comparable<K>, V> {

    private boolean left = true;
    private boolean right = false;

    BinaryTree<K, V> it = new BinaryTree<>();

    public boolean hasNext() {
        if (it.getRoot() == null)
            return false;

        if (left)
            if (it.new TreeLeaf<K, V>().left != null)
                return true;
            else {
                left = false;
                right = true;
            }

        if (right)
            return it.new TreeLeaf<K, V>().right != null;

        return false;
    }

    public BinaryTree<K, V>.TreeLeaf<K, V> next() {
        return (BinaryTree<K, V>.TreeLeaf<K, V>) it.new TreeLeaf<K, V>().value;

    }

}
