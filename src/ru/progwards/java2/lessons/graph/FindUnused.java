// Oleg Kiselev
// 13.07.2020, 20:53

package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindUnused {

    private enum State {UNUSED, IN_PROCESSING, PROCESSED}

    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        List<CObject> unused = new ArrayList<>();

        for (CObject root : roots) {
            if (root.mark == State.UNUSED)
                deepFirstSearch(root);
        }

        return unused;
    }

    private static void deepFirstSearch(CObject node) {
        node.mark = State.IN_PROCESSING;

        for (CObject cObject : node.references) {
            if (cObject.mark == State.UNUSED) {
                cObject.mark = State.IN_PROCESSING;
                deepFirstSearch(cObject);
            }
        }

        node.mark = State.PROCESSED;
    }


    public static class CObject {
        private final List<CObject> references;
        private String nameNode;
        private State mark = State.UNUSED;
        // UNUSED - не используется
        // CURRENT - используется
        // USED - посещен

        public CObject() {
            this.references = new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        FindUnused.CObject object;
        List<CObject> roots = new ArrayList<>();
        List<CObject> objects = new ArrayList<>();

        /* graph objects */
        for (int i = 0; i < 24; i++) {
            object = new FindUnused.CObject();
            object.nameNode = "Object-" + i;
            objects.add(object);
        }

        objects.get(0).references.add(objects.get(1));
        objects.get(1).references.add(objects.get(2));
        objects.get(1).references.add(objects.get(3));
        objects.get(2).references.add(objects.get(3));
        objects.get(3).references.add(objects.get(4));
        objects.get(3).references.add(objects.get(5));

        objects.get(6).references.add(objects.get(7));
        objects.get(7).references.add(objects.get(6));

        objects.get(8).references.add(objects.get(9));
        objects.get(8).references.add(objects.get(11));
        objects.get(8).references.add(objects.get(12));
        objects.get(9).references.add(objects.get(12));
        objects.get(9).references.add(objects.get(10));

        objects.get(13).references.add(objects.get(14));
        objects.get(14).references.add(objects.get(15));
        objects.get(15).references.add(objects.get(13));

        objects.get(16).references.add(objects.get(17));
        objects.get(16).references.add(objects.get(18));
        objects.get(18).references.add(objects.get(17));
        objects.get(17).references.add(objects.get(19));

        objects.get(20).references.add(objects.get(21));
        objects.get(21).references.add(objects.get(22));
        objects.get(22).references.add(objects.get(23));

        /* roots */
        object = new FindUnused.CObject();
        object.nameNode = "Root-1";
        object.references.add(objects.get(0));
        roots.add(object);
        object = new FindUnused.CObject();
        object.nameNode = "Root-2";
        object.references.add(objects.get(8));
        roots.add(object);
        object = new FindUnused.CObject();
        object.nameNode = "Root-3";
        object.references.add(objects.get(16));
        roots.add(object);

        find(roots, objects);
    }
}
