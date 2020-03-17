package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number {
    private double number;

    public DoubleNumber(double num) {
        number = num;
    }

    @Override
    public Number mul(Number num) {
        /*if (num == null || num.getClass() != getClass()) {
            Double nn = number * Integer.parseInt(num.toString());
            return newNumber(nn.toString());
        }*/
        double nn = number * ((DoubleNumber) num).number;
        return new DoubleNumber(nn);
    }

    @Override
    public Number div(Number num) {
        double nn = number / ((DoubleNumber) num).number;
        return new DoubleNumber(nn);
    }

    @Override
    public Number newNumber(String strNum) {
        return new DoubleNumber(Double.parseDouble(strNum));
    }

    @Override
    public String toString() {
        return ((Double) number).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        return this.number == ((DoubleNumber) obj).number;
    }
}
