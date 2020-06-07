// Oleg Kiselev
// 07.06.2020, 14:57

//package ru.progwards.java2.lessons.trees.tempfile;
//
//import ru.progwards.java2.lessons.trees.BinaryTree;
//
//import java.util.Stack;
//
//public class TreeIteratorKV<K, V> {
//
//    private final Stack<BinaryTree.TreeLeaf<?, ?>> stack;
//
//    public TreeIteratorKV(BinaryTree<?, ?>.TreeLeaf<?, ?> rootTree) {
//        stack = new Stack<>();
//        while (rootTree != null) {
//            stack.push(rootTree);
//            rootTree = rootTree.left;
//        }
//    }
//
//    public boolean hasNext() {
//        return !stack.isEmpty();
//    }
//
//    public BinaryTree<?, ?>.TreeLeaf<?, ?> next() {
//        BinaryTree<?, ?>.TreeLeaf<?, ?> tempNode = stack.pop();
//        BinaryTree<?, ?>.TreeLeaf<?, ?> result = tempNode;
//        if (tempNode.right != null) {
//            tempNode = tempNode.right;
//            while (tempNode != null) {
//                stack.push(tempNode);
//                tempNode = tempNode.left;
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "stack=" + stack;
//    }
//}

/* зачем делать данный метод с возвращаемым типом TreeIteratorKV<K, V> я не понял */
//public TreeIteratorKV<K, V> getIteratorVK() {
//        TreeIteratorKV<K, V> result = null;
//        for (TreeIterator it = new TreeIterator(this.getRoot()); it.hasNext(); ) {
//        System.out.println(result = new TreeIteratorKV<>(it.next()));
//        }
//        return result;
//        }
