package ru.progwards.java1.lessons.queues;

public class Order {
    /* заводим переменные класса (поля) */
    private double sum;
    private int num;
    /* инициализируем переменную начального индекса добавляемых заказов */
    static int numGen = 1;

    /* конструктор класса */
    public Order(double sum) {
        this.sum = sum;
        /* инкрементируем номер каждого нового заказа и присваиваем это значение переменной num */
        this.num = numGen++;
    }

    /* предоставляем доступ к приватным переменным sum и num */
    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    /* переопределяем метод toString для вывода суммы заказа и номера заказа */
    @Override
    public String toString() {
        return sum + "(" + num + ")";
    }
}
