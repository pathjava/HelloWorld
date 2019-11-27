package ru.progwards.sever;


class Test2 {
    private double a;
    private double b;

    public Test2(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return a*b;
    }

    public int compareTo(Test2 anRectangle){
        if (this.area() > anRectangle.area())
            return 1;
        else if (this.area() == anRectangle.area())
            return 0;
        else
            return -1;
    }
}
