package ru.progwards.sever.testprogwards.test_6;

class Test6 {
    private double a;
    private double b;

    public Test6(double a, double b) {
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

        Test6 test6 = (Test6) o;

//        if (Double.compare(test6.a, a) != 0) return false;
//        return Double.compare(test6.b, b) == 0;

        if (Double.compare(this.area(), ((Test6) o).area()) != 0) return false;
        return Double.compare(this.area(), ((Test6) o).area()) == 0;
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

    public static void main(String[] args) {
        Test6 g1 = new Test6(3.0, 2.0);
        Test6 g2 = new Test6(2.0, 3.0);

        if (g1.hashCode() == g2.hashCode()) {
            if (g1.equals(g2))
                System.out.println("Both Objects are equal. ");
            else
                System.out.println("Both Objects are not equal. ");
        } else
            System.out.println("Both Objects are not equal. ");
    }
}
