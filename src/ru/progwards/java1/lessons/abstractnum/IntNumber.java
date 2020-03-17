package ru.progwards.java1.lessons.abstractnum;

public class IntNumber extends Number {

    public IntNumber(int num){
        super("" + num, TypeNumber.INTEGER);
    }

    @Override
    public Number mul(Number num) {
        return this.mul(num);
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
