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
        String name; // имя узла

        public Node() {
            in = new ArrayList<>();
            out = new ArrayList<>();
            in.sort((o1, o2) -> {
                if (o1.weight > o2.weight)
                    return 1;
                else if (o1.weight < o2.weight)
                    return -1;
                else {
                    if (o1.id > o2.id)
                        return 1;
                    else if (o1.id < o2.id)
                        return -1;
                }
                return 0;
            });
        }
    }

    static class Edge<N, E> {
        E info; // информация о ребре
        Node<N, E> out; // вершина, из которой исходит ребро
        Node<N, E> in; // вершина, в которую можно попасть по этому ребру
        double weight; // стоимость перехода
        int id; // уникальный идентификатор ребра
    }

    static class Graph<N, E> {
        List<Node<N, E>> nodes;
        List<Edge<N, E>> edges;
    }

    public static void main(String[] args) {
        Boruvka<String, Integer> boruvka = new Boruvka<>();
        Graph<String, Integer> graph = new Graph<>();
        Node<String, Integer> node = new Node<>();
        Edge<String, Integer> edge = new Edge<>();
    }
}
