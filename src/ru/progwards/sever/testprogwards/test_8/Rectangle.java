package ru.progwards.sever.testprogwards.test_8;

import java.math.BigDecimal;

public class Rectangle {
    Rectangle(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public BigDecimal a;
    public BigDecimal b;

    boolean rectCompare(Rectangle r1, Rectangle r2) {
        BigDecimal sq1 = r1.a.multiply(r1.b);
        BigDecimal sq2 = r2.a.multiply(r2.b);
        return sq1.compareTo(sq2) == 0;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
