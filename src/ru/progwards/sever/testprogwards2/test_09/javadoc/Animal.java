// Oleg Kiselev
// 10.06.2020, 21:09

// Oleg Kiselev
// 10.06.2020, 20:10

/**
 * тестовый пакет Javadoc
 */

package ru.progwards.sever.testprogwards2.test_09.javadoc;

/**
 * класс Animal - прародитель в том числе коровы {@link Cow}
 *
 * @author Oleg
 * @version 1.0
 * @see Cow
 * @since 1.1
 */

public class Animal {
    public static final String description = "абстрактное животное";
    /**
     * свойство - вес
     */
    public double weight;
    /**
     * свойство - имя
     */
    public String name;

    /**
     * конструктор по умолчанию
     *
     * @see #Animal(double, String)
     */
    public Animal() {
    }

    /**
     * конструктор с параметрами про {@value #description}
     *
     * @param weight weight animal
     * @param name   name animal
     * @see #Animal()
     */
    public Animal(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    /**
     * @return return weight animal
     */
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * описание метода fly
     *
     * @deprecated животные не летают, метод устарел
     */
    public void fly() {
    }
}
