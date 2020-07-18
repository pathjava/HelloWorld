// Oleg Kiselev
// 16.07.2020, 17:46

package ru.progwards.java2.lessons.graph;

import java.util.*;

public class Boruvka<N, E> {

    public List<Edge<N, E>> minTree(Graph<N, E> graph) {
        List<Edge<N, E>> edgeList = new ArrayList<>();

        return edgeList;
    }

    private void find() {

    }

    private void merge() {

    }

    static class Node<N, E> {
        N info; // информация об узле
        List<Edge<N, E>> in; // массив входящих ребер
        List<Edge<N, E>> out; // массив исходящих ребер

        public Node() {
            in = new LinkedList<>();
            out = new LinkedList<>();
        }
    }

    static class Edge<N, E> {
        E info; // информация о ребре
        Node<N, E> out; // вершина, из которой исходит ребро
        Node<N, E> in; // вершина, в которую можно попасть по этому ребру
        double weight; // стоимость перехода
    }

    static class Graph<N, E> {
        List<Node<N, E>> nodes = new LinkedList<>();
        List<Edge<N, E>> edges = new LinkedList<>();
    }

    public static void main(String[] args) {
        Boruvka<String, Integer> boruvka = new Boruvka<>();
        Graph<String, Integer> graph = new Graph<>();

        /* nodes */
        for (int i = 0; i < 12; i++) {
            Node<String, Integer> node = new Node<>();
            node.info = "Node-" + i;
            graph.nodes.add(node);
        }
        /* edges */
        for (int i = 0; i < 20; i++) {
            Edge<String, Integer> edge = new Edge<>();
            edge.info = i;
            edge.weight = i + 1;
            graph.edges.add(edge);
        }
        graph.nodes.get(0).out.add(graph.edges.get(12));
        graph.nodes.get(0).out.add(graph.edges.get(5));

        boruvka.minTree(graph);
    }
}
