package ru.progwards.java1.lessons.abstractnum;

public class IntNumber extends Number {

    private int number;

    public IntNumber(int num) {
        number = num;
    }

    @Override
    public Number mul(Number num) {
        /*if (num == null || num.getClass() != getClass()) {
            Integer nn = number * Integer.parseInt(num.toString());
            return newNumber(nn.toString());
        }*/
        int nn = number * ((IntNumber) num).number;
        return new IntNumber(nn);
    }

    @Override
    public Number div(Number num) {
        int nn = number / ((IntNumber) num).number;
        return new IntNumber(nn);
    }

    @Override
    public Number newNumber(String strNum) {
        return new IntNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString() {
        return ((Integer) number).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        return this.number == ((IntNumber) obj).number;
    }
}
