// Oleg Kiselev
// 11.07.2020, 10:07

package ru.progwards.java2.lessons.graph;

import java.util.*;

public class Dijkstra {

    private final int[][] graph;
    private Set<Node> sortedNodes;
    private final Map<Node, Set<Node>> nodes = new HashMap<>();
    private Node node;
    private Queue<Integer> queue = new LinkedList<Integer>();
    //    private final Map<Node, Set<Node>> node = new TreeMap<>(Comparator.comparingInt(Node::getCurrentNode));

    public Dijkstra(int[][] graph) {
        this.graph = graph;
    }

    public void find(int n) {
        initializationFirstNode(n);
        int count = 0;
        while (count < graph.length) {
            if (!nodes.entrySet().iterator().next().getKey().isVisited()) {
                int key = queue.isEmpty() ? n : queue.poll();
                node = new Node();
                for (Map.Entry<Node, Set<Node>> entry : nodes.entrySet())
                    if (entry.getKey().getNumberNode() == key) {
                        node = entry.getKey();
                        break;
                    }
                searchPathsToNodes(node, key);
                node.setVisited(true);
            }

            for (Node value : nodes.get(node)) {
                int path = node.getPathLength() + value.getPathLength();
                if (nodes.entrySet().iterator().next().getKey().getNumberNode() == value.getNumberNode()) {
                    if (nodes.entrySet().iterator().next().getKey().getPathLength() > path)
                        nodes.entrySet().iterator().next().getKey().setPathLength(path);
                } else {
                    sortedNodes = new TreeSet<>(Comparator.comparingInt(Node::getPathLength));
                    nodes.put(value, sortedNodes);
                }
            }
            count++;
        }
    }

    private void initializationFirstNode(int n) {
        node = new Node();
        sortedNodes = new TreeSet<>(Comparator.comparingInt(Node::getPathLength));
        node.setNumberNode(n);
        node.setPathLength(0);
        nodes.put(node, sortedNodes);
        searchPathsToNodes(node, n);
        node.setVisited(true);
    }

    private void searchPathsToNodes(Node node, int key) {
        sortedNodes = new TreeSet<>(Comparator.comparingInt(Node::getPathLength));
        for (int i = 0; i < graph.length; i++) {
            if (!node.isVisited())
                if (graph[key][i] != 0 && i != node.comeFrom) {
                    Node nodeSet = new Node();
                    nodeSet.setPathLength(graph[key][i]);
                    nodeSet.setNumberNode(i);
                    nodeSet.setComeFrom(key);
                    sortedNodes.add(nodeSet);
                    nodes.put(node, sortedNodes);
                }
        }
        for (Node sortedNode : sortedNodes) {
            queue.add(sortedNode.getNumberNode());
        }
    }

    static class Node {
        private boolean visited;
        private int comeFrom;
        private int numberNode;
        private int pathLength;

        public Node() {
            this.visited = false;
            this.comeFrom = 0;
            this.numberNode = 0;
            this.pathLength = Integer.MAX_VALUE;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public int getComeFrom() {
            return comeFrom;
        }

        public void setComeFrom(int comeFrom) {
            this.comeFrom = comeFrom;
        }

        public int getNumberNode() {
            return numberNode;
        }

        public void setNumberNode(int numberNode) {
            this.numberNode = numberNode;
        }

        public int getPathLength() {
            return pathLength;
        }

        public void setPathLength(int pathLength) {
            this.pathLength = pathLength;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            Node other = (Node) o;
            return numberNode == other.getNumberNode();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + numberNode;
            return result;
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
