// Oleg Kiselev
// 05.06.2020, 21:09

package ru.progwards.java2.lessons.trees;


import java.util.Stack;

public class TreeIterator {

    Stack<BinaryTree<?, ?>.TreeLeaf<?, ?>> stack;

    public TreeIterator(BinaryTree<?, ?>.TreeLeaf<?, ?> root) {
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
        BinaryTree<?, ?>.TreeLeaf<?, ?> result = (BinaryTree<?, ?>.TreeLeaf<?, ?>) node.value;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }
}
