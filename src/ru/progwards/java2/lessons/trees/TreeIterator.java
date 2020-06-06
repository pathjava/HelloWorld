// Oleg Kiselev
// 05.06.2020, 21:09

package ru.progwards.java2.lessons.trees;


public class TreeIterator<K extends Comparable<K>, V> {

    private boolean left = true;
    private boolean right = false;

    BinaryTree<K, V> iter = new BinaryTree<>();

    public boolean hasNext() {
        if (iter.getRoot() == null)
            return false;

        if (left)
            if (iter.new TreeLeaf<K, V>().getLeft() != null)
                return true;
            else {
                left = false;
                right = true;
            }

        if (right)
            return iter.new TreeLeaf<K, V>().getRight() != null;

        return false;
    }

    public BinaryTree<K, V>.TreeLeaf<K, V> next() {

    }

}
