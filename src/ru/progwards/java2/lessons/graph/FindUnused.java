// Oleg Kiselev
// 13.07.2020, 20:53

package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

public class FindUnused {

    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {


    }

    public static class CObject {
        private List<CObject> references;
        private int mark;
    }


    public static void main(String[] args) {
        List<CObject> roots = new ArrayList<>();
        List<CObject> objects = new ArrayList<>();

        find(roots, objects);
    }
}
