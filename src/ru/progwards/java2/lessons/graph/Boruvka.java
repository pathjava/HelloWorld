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
        char c = 'A';
        for (int i = 0; i < 7; i++) {
            Node<String, Integer> node = new Node<>();
            node.info = "Node-" + c;
            graph.nodes.add(node);
            c++;
        }
        /* edges */
        for (int i = 0; i < 11; i++) {
            Edge<String, Integer> edge = new Edge<>();
            edge.info = i + 1;
            graph.edges.add(edge);
        }
        /* A */
        graph.nodes.get(0).out.add(graph.edges.get(0));
        graph.nodes.get(0).in.add(graph.edges.get(0));
        graph.nodes.get(0).out.add(graph.edges.get(1));
        graph.nodes.get(0).in.add(graph.edges.get(1));
        /* B */
        graph.nodes.get(1).out.add(graph.edges.get(0));
        graph.nodes.get(1).in.add(graph.edges.get(0));
        graph.nodes.get(1).out.add(graph.edges.get(2));
        graph.nodes.get(1).in.add(graph.edges.get(2));
        graph.nodes.get(1).out.add(graph.edges.get(3));
        graph.nodes.get(1).in.add(graph.edges.get(3));
        graph.nodes.get(1).out.add(graph.edges.get(4));
        graph.nodes.get(1).in.add(graph.edges.get(4));
        /* C */
        graph.nodes.get(2).out.add(graph.edges.get(3));
        graph.nodes.get(2).in.add(graph.edges.get(3));
        graph.nodes.get(2).out.add(graph.edges.get(5));
        graph.nodes.get(2).in.add(graph.edges.get(5));
        /* D */
        graph.nodes.get(3).out.add(graph.edges.get(1));
        graph.nodes.get(3).in.add(graph.edges.get(1));
        graph.nodes.get(3).out.add(graph.edges.get(2));
        graph.nodes.get(3).in.add(graph.edges.get(2));
        graph.nodes.get(3).out.add(graph.edges.get(6));
        graph.nodes.get(3).in.add(graph.edges.get(6));
        graph.nodes.get(3).out.add(graph.edges.get(7));
        graph.nodes.get(3).in.add(graph.edges.get(7));
        /* E */
        graph.nodes.get(4).out.add(graph.edges.get(5));
        graph.nodes.get(4).in.add(graph.edges.get(5));
        graph.nodes.get(4).out.add(graph.edges.get(4));
        graph.nodes.get(4).in.add(graph.edges.get(4));
        graph.nodes.get(4).out.add(graph.edges.get(6));
        graph.nodes.get(4).in.add(graph.edges.get(6));
        graph.nodes.get(4).out.add(graph.edges.get(8));
        graph.nodes.get(4).in.add(graph.edges.get(8));
        graph.nodes.get(4).out.add(graph.edges.get(9));
        graph.nodes.get(4).in.add(graph.edges.get(9));
        /* F */
        graph.nodes.get(5).out.add(graph.edges.get(7));
        graph.nodes.get(5).in.add(graph.edges.get(7));
        graph.nodes.get(5).out.add(graph.edges.get(8));
        graph.nodes.get(5).in.add(graph.edges.get(8));
        graph.nodes.get(5).out.add(graph.edges.get(10));
        graph.nodes.get(5).in.add(graph.edges.get(10));
        /* G */
        graph.nodes.get(6).out.add(graph.edges.get(9));
        graph.nodes.get(6).in.add(graph.edges.get(9));
        graph.nodes.get(5).out.add(graph.edges.get(10));
        graph.nodes.get(5).in.add(graph.edges.get(10));

        boruvka.minTree(graph);
    }
}
