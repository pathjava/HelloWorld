// Oleg Kiselev
// 11.07.2020, 10:07

package ru.progwards.java2.lessons.graph;

import java.util.*;

public class Dijkstra {

    private final int[][] graph;
    private final List<Node> nodes = new ArrayList<>();
    private Node node;
    private NodeSet nodeSet;
    private Queue<Integer> queue = new LinkedList<>();

    public Dijkstra(int[][] graph) {
        this.graph = graph;
    }


    public void find(int n) {
        initializationFirstNode(n);
        int count = 0;
        int index = 0;
        while (count < graph.length) {
            if (!nodes.get(index).visited) {
                int key = queue.isEmpty() ? n : queue.poll();
//                node = new Node();
//                for (NodeSet sortedNode : nodes.get(index).sortedNodes)
//                    if (sortedNode.numberNodeSet == key) {
//                        node.numberNode = sortedNode.numberNodeSet;
//                        node.comeFrom = sortedNode.comeFromSet;
//                        node.pathLength = sortedNode.pathLengthSet;
//                        node.visited = sortedNode.visitedSet;
//                        break;
//                    }
                searchPathsToNodes(nodes.get(index), key);
                node.visited = true;
            }

            for (NodeSet sortedNode : nodes.get(index).sortedNodes) {
                int path = node.pathLength + sortedNode.pathLengthSet;
                if (nodes.get(count).numberNode == sortedNode.numberNodeSet) {
                    if (nodes.get(count).pathLength > path) {
                        nodes.get(count).pathLength = path;
                        nodes.get(count).comeFrom = sortedNode.comeFromSet;
                    }
                } else {
                    node = new Node();
                    node.numberNode = sortedNode.numberNodeSet;
                    node.comeFrom = sortedNode.comeFromSet;
                    node.pathLength = sortedNode.pathLengthSet;
                    node.visited = sortedNode.visitedSet;
                    nodes.add(node);
                }
            }
            count++;
            index++;
        }
    }

    private void initializationFirstNode(int n) {
        node = new Node();
        node.numberNode = n;
        node.pathLength = 0;
        nodes.add(node);
//        searchPathsToNodes(node, n);
//        node.visited = true;
    }

    private void searchPathsToNodes(Node node, int key) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[key][i] != 0 && i != node.comeFrom) {
//                if (graph[key][i]+node.pathLength < )
                nodeSet = new NodeSet();
                nodeSet.pathLengthSet = graph[key][i];
                nodeSet.numberNodeSet = i;
                nodeSet.comeFromSet = key;
                node.sortedNodes.add(nodeSet);
            }
        }
        for (NodeSet sortedNode : node.sortedNodes)
            queue.add(sortedNode.numberNodeSet);
    }

    static class Node {
        private boolean visited;
        private int comeFrom;
        private int numberNode;
        private int pathLength;
        private Set<NodeSet> sortedNodes;

        public Node() {
            this.visited = false;
            this.comeFrom = 0;
            this.numberNode = 0;
            this.pathLength = Integer.MAX_VALUE;
            this.sortedNodes = new TreeSet<>(Comparator.comparingInt(o -> o.pathLengthSet));
        }
    }

    static class NodeSet {
        private boolean visitedSet;
        private int comeFromSet;
        private int numberNodeSet;
        private int pathLengthSet;

        public NodeSet() {
            this.visitedSet = false;
            this.comeFromSet = 0;
            this.numberNodeSet = 0;
            this.pathLengthSet = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 10, 6, 8, 0, 0, 0, 0, 0},
                {0, 0, 0, 5, 13, 0, 11, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 0, 2, 0, 5, 7, 12, 0, 0},
                {0, 0, 0, 0, 0, 9, 0, 0, 12},
                {0, 0, 0, 0, 0, 0, 0, 8, 10},
                {0, 0, 0, 0, 0, 4, 0, 6, 16},
                {0, 0, 0, 0, 0, 0, 0, 0, 15},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] oriMatrix = {{0, 10, 6, 8, 0, 0, 0, 0, 0},
                {10, 0, 0, 5, 13, 0, 11, 0, 0},
                {6, 0, 0, 2, 3, 0, 0, 0, 0},
                {8, 5, 2, 0, 5, 7, 12, 0, 0},
                {0, 13, 3, 5, 0, 9, 0, 0, 12},
                {0, 0, 0, 7, 9, 0, 4, 8, 10},
                {0, 11, 0, 12, 0, 4, 0, 6, 16},
                {0, 0, 0, 0, 0, 8, 6, 0, 15},
                {0, 0, 0, 0, 12, 10, 16, 15, 0}};

        Dijkstra dijkstra = new Dijkstra(oriMatrix);
        dijkstra.find(6);
    }
}
