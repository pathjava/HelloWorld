// Oleg Kiselev
// 07.06.2020, 18:34

//package ru.progwards.java2.lessons.trees.tempfile;
//
//import ru.progwards.java2.lessons.trees.BinaryTree;
//
//import java.util.Iterator;
//import java.util.Stack;
//
//public class TreeIteratorTwoVersion {
//
//    public Iterator<BinaryTree.TreeLeaf<?, ?>> getIteratorTwo() {
//        return new Iterator<>() {
//            private Stack<BinaryTree<?, ?>.TreeLeaf<?, ?>> stack = null;
//            BinaryTree<?, ?>.TreeLeaf<?, ?> rootTree;
//            public void checkRoot() {
//                stack = new Stack<>();
//                while (rootTree != null) {
//                    stack.push(rootTree);
//                    rootTree = rootTree.left;
//                }
//            }
//
//            @Override
//            public boolean hasNext() {
//                if (stack == null){
//                    checkRoot();
//                }
//                return !stack.isEmpty();
//            }
//
//            @Override
//            public BinaryTree<?, ?>.TreeLeaf<?, ?> next() {
//                BinaryTree<?, ?>.TreeLeaf<?, ?> tempNode = stack.pop();
//                BinaryTree<?, ?>.TreeLeaf<?, ?> result = tempNode;
//                if (tempNode.right != null) {
//                    tempNode = tempNode.right;
//                    while (tempNode != null) {
//                        stack.push(tempNode);
//                        tempNode = tempNode.left;
//                    }
//                }
//                return result;
//            }
//        };
//    }
//}
