package ru.progwards.sever.testprogwards.test_7;

public class Test_7_1 {
    private double a;
    private double b;

    public Test_7_1(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test_7_1 test_7_1 = (Test_7_1) o;

        if (Double.compare(test_7_1.a, a) != 0) return false;
        return Double.compare(test_7_1.b, b) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Rectangle rectangle = (Rectangle) o;
//
//        if (Double.compare(this.area(), ((Rectangle) o).area()) != 0) return false;
//        return Double.compare(this.area(), ((Rectangle) o).area()) == 0;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        temp = Double.doubleToLongBits(a);
//        result = (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(b);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }

}
