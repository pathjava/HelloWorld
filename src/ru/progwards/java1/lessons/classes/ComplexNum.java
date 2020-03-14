package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    private int a, b;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return a + "+" + b + "i";
    }

    // с этого момента я зашел в тупик, после чего прибегнул к гуглу
    public ComplexNum add(ComplexNum num1, ComplexNum num2) {
        return new ComplexNum(num1.a + num2.a, num1.b + num2.b);
    }

    public ComplexNum sub(ComplexNum num1, ComplexNum num2) {
        return new ComplexNum(num1.a - num2.a, num1.b - num2.b);
    }

    public ComplexNum mul(ComplexNum num1, ComplexNum num2) {
        return new ComplexNum(num1.a * num2.a - num1.b * num2.b, num1.a * num2.b + num1.b * num2.a);
    }

    public ComplexNum div(ComplexNum num1, ComplexNum num2) {
        return new ComplexNum((num1.a * num2.a + num1.b * num2.b) / (num2.a * num2.a + num2.b * num2.b), (num1.b * num2.a - num1.a * num2.b) / (num2.a * num2.a + num2.b * num2.b));
    }

    // вывести результаты получилось только после подсказки Григория - сам проверял варианты близкие к правильному решению, но верного не нашел
    public static void main(String[] args) {
        ComplexNum a = new ComplexNum(2, 3);
        ComplexNum b = new ComplexNum(3, 2);
        System.out.println(a.add(a, b));
        System.out.println(a.sub(a, b));
        System.out.println(a.mul(a, b));
        System.out.println(a.div(a, b));
    }
}
