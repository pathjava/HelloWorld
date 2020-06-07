// Oleg Kiselev
// 05.06.2020, 21:09

package ru.progwards.java2.lessons.trees;

import java.util.Stack;

public class TreeIterator {

    private final Stack<BinaryTree<?, ?>.TreeLeaf<?, ?>> stack;

    public TreeIterator(BinaryTree<?, ?>.TreeLeaf<?, ?> rootTree) {
        stack = new Stack<>();
        while (rootTree != null) {
            stack.push(rootTree);
            rootTree = rootTree.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public BinaryTree<?, ?>.TreeLeaf<?, ?> next() {
        BinaryTree<?, ?>.TreeLeaf<?, ?> tempNode = stack.pop();
        BinaryTree<?, ?>.TreeLeaf<?, ?> result = tempNode;
        if (tempNode.right != null) {
            tempNode = tempNode.right;
            while (tempNode != null) {
                stack.push(tempNode);
                tempNode = tempNode.left;
            }
        }
        return result;
    }
}
