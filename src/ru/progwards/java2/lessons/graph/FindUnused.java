// Oleg Kiselev
// 13.07.2020, 20:53

package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

public class FindUnused {

    private enum State {UNUSED, CURRENT, USED}

    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        List<CObject> unused = new ArrayList<>();


        return unused;
    }

    public static class CObject {
        private List<CObject> references;
        private final State mark = State.UNUSED;
        // UNUSED - не используется
        // CURRENT - используется
        // USED - посещен
    }


    public static void main(String[] args) {
        List<CObject> roots = new ArrayList<>();
        List<CObject> objects = new ArrayList<>();

        find(roots, objects);
    }
}
