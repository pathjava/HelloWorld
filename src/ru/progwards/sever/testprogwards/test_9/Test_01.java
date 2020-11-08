package ru.progwards.sever.testprogwards.test_9;

public class Test_01 {
    public Integer sqr(Integer n) {
        try {
            return (int) Math.pow(n, 2);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Площадь равна: " + sqr(4);
    }

    public static void main(String[] args) {
        Test_01 square = new Test_01();
//        square.sqr(4);
        System.out.println(square);
    }
}
