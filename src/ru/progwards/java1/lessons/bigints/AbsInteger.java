package ru.progwards.java1.lessons.bigints;

public class AbsInteger {
    protected int b;

    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        if (num1.b >= num2.b) {
            return num1.addTwo(num2);
        } else
            return num2.addTwo(num1);
    }

    protected AbsInteger addTwo(AbsInteger num1) {
        return null;
    }

    protected byte byteValue() {
        return 0;
    }

    protected int intValue() {
        return 0;
    }

    protected short shortValue() {
        return 0;
    }

    public static void main(String[] args) {
        AbsInteger num1 = new ByteInteger((byte) 15);
        AbsInteger num2 = new ShortInteger((short) 314);
        System.out.println(AbsInteger.add(num1, num2));
        System.out.println();
        AbsInteger num3 = new ShortInteger((short) 472);
        AbsInteger num4 = new IntInteger(12);
        System.out.println(AbsInteger.add(num3, num4));
        System.out.println();
        AbsInteger num5 = new ByteInteger((byte) 25);
        AbsInteger num6 = new IntInteger(234);
        System.out.println(AbsInteger.add(num5, num6));
    }
}

class ByteInteger extends AbsInteger {
    byte num;

    public ByteInteger(byte num) {
        this.num = num;
        b = 1;
    }

    @Override
    public String toString() {
        return Byte.toString(num);
    }

    @Override
    protected byte byteValue() {
        return num;
    }

    @Override
    protected int intValue() {
        return num;
    }

    @Override
    protected short shortValue() {
        return num;
    }

    @Override
    protected AbsInteger addTwo(AbsInteger num1) {
        return new ByteInteger((byte) (num + num1.byteValue()));
    }
}

class ShortInteger extends AbsInteger {
    short num;

    public ShortInteger(short num) {
        this.num = num;
        b = 2;
    }

    @Override
    public String toString() {
        return Short.toString(num);
    }

    @Override
    protected int intValue() {
        return num;
    }

    @Override
    protected short shortValue() {
        return num;
    }

    @Override
    protected AbsInteger addTwo(AbsInteger num1) {
        return new ShortInteger((short) (num + num1.shortValue()));
    }
}

class IntInteger extends AbsInteger {
    int num;

    public IntInteger(int num) {
        this.num = num;
        b = 4;
    }

    @Override
    public String toString() {
        return Integer.toString(num);
    }

    @Override
    protected int intValue() {
        return num;
    }

    @Override
    protected AbsInteger addTwo(AbsInteger num1) {
        return new IntInteger((int) (num + num1.intValue()));
    }
}

