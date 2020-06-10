// Oleg Kiselev
// 10.06.2020, 21:09

// Oleg Kiselev
// 10.06.2020, 20:10

package ru.progwards.sever.testprogwards2.test_09.javadoc;

public class Cow extends Animal {
    public double milk;

    public Cow(double weight, String name, double milk) {
        super(weight, name);
        this.milk = milk;
    }

    /**
     *
     * @throws RuntimeException исключение, так как коровы не летают
     */
    @Override
    public void fly() throws RuntimeException {
        throw new RuntimeException("Я корова и не умею летать");
    }

    /**
     *
     * @return удойность коровы
     * @exception ArithmeticException вес коровы не может быть меньше 0
     */
    public double milkForWeight() {
        if (weight <= 0)
            throw new ArithmeticException("У коровы не может быть вес " + weight);
        return milk / weight;
    }
}
