// Oleg Kiselev
// 11.07.2020, 10:07

package ru.progwards.java2.lessons.graph;

import java.util.*;

public class Dijkstra {

    private final int[][] graph;
    private final Map<Integer, Node> nodes = new HashMap<>(); /* для хранения узлов с длинами путей и информации о родительских узлах */
    private Node node;
    private final Queue<Integer> queue = new LinkedList<>(); /* очередь обработки узлов по минимальной длине пути */

    public Dijkstra(int[][] graph) {
        this.graph = graph;
    }

    public void find(int n) {
        initializationFirstNode(n); /* инициализируем стартовый узел N */
        int count = 0;
        while (count < graph.length) {
            int key = queue.isEmpty() ? n : queue.poll(); /* ключ обрабатываемого узла */
            if (!nodes.get(key).visited) { /* если узел не отмечен как посещенный */
                searchPathsToNodes(nodes.get(key), key); /* ищем и добавляем в TreeSet в узле информацию о ближайших узлах */
                nodes.get(key).visited = true; /* отмечаем узел как посещенный */
            }

            for (NodeSet sortedNode : nodes.get(key).sortedNodes) {
                int path = nodes.get(key).pathLength + sortedNode.pathLengthSet;
                if (nodes.containsKey(sortedNode.numberNodeSet)) {
                    if (nodes.get(key).pathLength > path) {
                        nodes.get(key).pathLength = path;
                        nodes.get(key).comeFrom = sortedNode.comeFromSet;
                    }
                } else {
                    node = new Node();
                    node.numberNode = sortedNode.numberNodeSet;
                    node.comeFrom = sortedNode.comeFromSet;
                    node.pathLength = path;
                    node.visited = sortedNode.visitedSet;
                    nodes.put(sortedNode.numberNodeSet, node);
                }
            }
            count++;
        }
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            System.out.println(entry);
        }
    }

    private void initializationFirstNode(int n) {
        node = new Node();
        node.numberNode = n;
        node.pathLength = 0;
        nodes.put(n, node);
    }

    private void searchPathsToNodes(Node node, int key) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[key][i] != 0 && i != node.comeFrom) {
                if (nodes.containsKey(i)) {
                    int pathSize = graph[key][i] + node.pathLength;
                    if (pathSize < nodes.get(i).pathLength) {
                        nodes.get(i).pathLength = pathSize;
                        nodes.get(i).comeFrom = key;
                    }
                } else {
                    NodeSet nodeSet = new NodeSet();
                    nodeSet.pathLengthSet = graph[key][i];
                    nodeSet.numberNodeSet = i;
                    nodeSet.comeFromSet = key;
                    node.sortedNodes.add(nodeSet);
                }
            }
        }
        for (NodeSet sortedNode : node.sortedNodes)
            queue.add(sortedNode.numberNodeSet);
    }

    static class Node {
        private boolean visited = false;
        private int comeFrom = 0;
        private int numberNode = 0;
        private int pathLength = Integer.MAX_VALUE;
        private final Set<NodeSet> sortedNodes;

        public Node() {
            this.sortedNodes = new TreeSet<>(Comparator.comparingInt(o -> o.pathLengthSet));
        }

        @Override
        public String toString() {
            return "Node{" +
                    "visited=" + visited +
                    ", comeFrom=" + comeFrom +
                    ", numberNode=" + numberNode +
                    ", pathLength=" + pathLength +
                    '}';
        }
    }

    static class NodeSet {
        private final boolean visitedSet = false;
        private int comeFromSet = 0;
        private int numberNodeSet = 0;
        private int pathLengthSet = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        /* ориентированный граф */
        int[][] matrix = {{0, 10, 6, 8, 0, 0, 0, 0, 0},
                {0, 0, 0, 5, 13, 0, 11, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 0, 2, 0, 5, 7, 12, 0, 0},
                {0, 0, 0, 0, 0, 9, 0, 0, 12},
                {0, 0, 0, 0, 0, 0, 0, 8, 10},
                {0, 0, 0, 0, 0, 4, 0, 6, 16},
                {0, 0, 0, 0, 0, 0, 0, 0, 15},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        /* неориентированный граф */
        int[][] oriMatrix = {{0, 10, 6, 8, 0, 0, 0, 0, 0},
                {10, 0, 0, 5, 13, 0, 11, 0, 0},
                {6, 0, 0, 2, 3, 0, 0, 0, 0},
                {8, 5, 2, 0, 5, 7, 12, 0, 0},
                {0, 13, 3, 5, 0, 9, 0, 0, 12},
                {0, 0, 0, 7, 9, 0, 4, 8, 10},
                {0, 11, 0, 12, 0, 4, 0, 6, 16},
                {0, 0, 0, 0, 0, 8, 6, 0, 15},
                {0, 0, 0, 0, 12, 10, 16, 15, 0}};

        Dijkstra dijkstra = new Dijkstra(matrix);
        dijkstra.find(0);
    }
}
