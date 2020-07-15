// Oleg Kiselev
// 13.07.2020, 20:53

package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FindUnused {

    private enum State {UNUSED, CURRENT, USED}

    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        List<CObject> unused = new ArrayList<>();


        return unused;
    }

    public static class CObject {
        private List<CObject> references;
        private String nameNode;
        private final State mark = State.UNUSED;
        // UNUSED - не используется
        // CURRENT - используется
        // USED - посещен
    }

    public static List<CObject> graphCreator() {
        List<CObject> objects = new ArrayList<>();
        CObject cObject;
        int rand = ThreadLocalRandom.current().nextInt(15, 30);

        for (int i = 0; i < rand; i++) {
            cObject = new CObject();
            cObject.nameNode = "NodeGraph-" + i;
            objects.add(cObject);
        }
        return objects;
    }

    public static List<CObject> rootCreator(int n) {
        List<CObject> roots = new ArrayList<>();
        CObject cObject;

        for (int i = 0; i < n; i++) {
            cObject = new CObject();
            cObject.nameNode = "NodeRoot-" + i;
            cObject.references = graphCreator();
            roots.add(cObject);
        }
        return roots;
    }


    public static void main(String[] args) {
        int rootCount = 5;
        List<CObject> roots;
        List<CObject> objects;

        while (rootCount > 0) {
            roots = rootCreator();
            objects = graphCreator();
            find(roots, objects);
            rootCount--;
        }
    }
}
