// Oleg Kiselev
// 07.06.2020, 14:57

package ru.progwards.java2.lessons.trees;

import java.util.Stack;

public class TreeIteratorKV<K, V> {

    private final Stack<BinaryTree<?, ?>.TreeLeaf<?, ?>> stack;

    public TreeIteratorKV(BinaryTree<?, ?>.TreeLeaf<?, ?> root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public BinaryTree<?, ?>.TreeLeaf<?, ?> next() {
        BinaryTree<?, ?>.TreeLeaf<?, ?> node = stack.pop();
        BinaryTree<?, ?>.TreeLeaf<?, ?> result = node;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "TreeIteratorKV{" +
                "stack=" + stack +
                '}';
    }
}
