// Oleg Kiselev
// 11.07.2020, 10:07

package ru.progwards.java2.lessons.graph;

public class Dijkstra {

    private int[][] graph;

    public Dijkstra(int[][] graph) {
        this.graph = graph;
    }

    public int[][] find(int n) {

    }

    static class Node {
        private boolean visited = false;
        private int comeFrom = 0;
        private int currentNode = 0;
        private int pathLength = 0;

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

        public int getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(int currentNode) {
            this.currentNode = currentNode;
        }

        public int getPathLength() {
            return pathLength;
        }

        public void setPathLength(int pathLength) {
            this.pathLength = pathLength;
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

        Dijkstra dijkstra = new Dijkstra(matrix);
    }
}
