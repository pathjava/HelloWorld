package ru.progwards.java1.lessons.abstractnum;

public abstract class  Number {

    String stringNumber = null;
    enum TypeNumber {INTEGER, DOUBLE}
    TypeNumber typeNumber = null;

    public Number(String stringNumber) {
        this.stringNumber = stringNumber;
    }

    public Number(String stringNumber, TypeNumber typeNumber) {
        this.stringNumber = stringNumber;
        this.typeNumber = typeNumber;
    }

    public Number mul(Number num){
        return null;
    }
    public Number div(Number num){
        return null;
    }
    public Number newNumber(String strNum){
        return null;
    }
    public String toString(){
        return null;
    }
}
