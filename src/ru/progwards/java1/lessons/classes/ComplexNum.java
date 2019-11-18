package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    private int a, b;

    public ComplexNum(int a, int b){
        this.a = a;
        this.b = b;
    }
    public String toString(){
        return a + "+" + b+"i";
    }
    public ComplexNum add(ComplexNum num1, ComplexNum num2){
        return new ComplexNum(num1.a + num2.a, num1.b + num2.b);
    }
    public ComplexNum sub(ComplexNum num1, ComplexNum num2){
        return new ComplexNum(num1.a - num2.a, num1.b - num2.b);
    }
    public ComplexNum mul(ComplexNum num1, ComplexNum num2){
        return new ComplexNum(num1.a * num2.a - num1.b * num2.b, num1.a * num2.b + num1.b * num2.a);
    }
    public ComplexNum div(ComplexNum num1, ComplexNum num2){
        return new ComplexNum((num1.a * num2.a + num1.b * num2.b)/(num2.a * num2.a + num2.b * num2.b),(num1.b * num2.a - num1.a * num2.b)/(num2.a * num2.a + num2.b * num2.b));
    }

    public static void main(String[] args) {

    }
}
