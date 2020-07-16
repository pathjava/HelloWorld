// Oleg Kiselev
// 16.07.2020, 17:46

package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

public class Boruvka<N, E> {

    public List<Edge<N, E>> minTree(Graph<N, E> graph) {
        List<Edge<N, E>> edgeList = new ArrayList<>();

        return edgeList;
    }

    private class Node<N, E> {
        N info; // информация об узле
        List<Edge<N, E>> in; // массив входящих ребер
        List<Edge<N, E>> out; // массив исходящих ребер
    }

    private class Edge<N, E> {
        E info; // информация о ребре
        Node<N, E> out; // вершина, из которой исходит ребро
        Node<N, E> in; // вершина, в которую можно попасть по этому ребру
        double weight; // стоимость перехода
    }

    private class Graph<N, E> {
        List<Node<N, E>> nodes;
        List<Edge<N, E>> edges;
    }


    public static void main(String[] args) {

    }
}
