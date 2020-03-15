package ru.progwards.sever.testprogwards.test_17;

import java.util.Date;
import java.util.Locale;

public class Test_3 {

    public static void printPersons(Person[] persons) {
        Locale locale = new Locale("ru", "RU");
        for (Person person : persons) {
            System.out.format(locale, "|%-10s|%td/%tm/%tY|%,10.2f|\n",
                    person.name, person.birth, person.birth, person.birth, person.salary);
        }
    }


    public static void main(String[] args) {
        Person[] persons = {new Person("Вася", new Date(0), 200000.001),
                new Person("Петя", new Date(199234885678L), 55331.123),
                new Person("Коля", new Date(598812345678L), 99999.999)};
        printPersons(persons);


//        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);
//        |0002|0xf|3,3|
    }
}

class Person {
    public String name;
    public Date birth;
    public double salary;

    Person(String name, Date birth, double salary) {
        this.name = name;
        this.birth = birth;
        this.salary = salary;
    }
}