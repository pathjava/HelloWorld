// Oleg Kiselev
// 06.11.2020, 18:03

// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards.test_2;


class Test2 {
    private double a;
    private double b;

    public Test2(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return a * b;
    }

    public int compareTo(Test2 anRectangle) {
        if (this.area() > anRectangle.area())
            return 1;
        else if (this.area() == anRectangle.area())
            return 0;
        else
            return -1;
    }
}
