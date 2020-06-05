// Oleg Kiselev
// 04.06.2020, 18:23

package ru.progwards.java2.lessons.reflection.testfiles;

public class PersonGettersAndSetters {
    private String name;
    private int age;
    private boolean sex;
    public String secondName;
    private static String nextName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSex() {
        return sex;
    }
}
