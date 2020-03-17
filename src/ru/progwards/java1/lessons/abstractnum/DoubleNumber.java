package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number {
    public double num;

    public DoubleNumber(double num) {
        super();
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
        return super.mul(num);
    }

    @Override
    public Number div(Number num) {
        return super.div(num);
    }

    @Override
    public Number newNumber(String strNum) {
        return super.newNumber(strNum);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
