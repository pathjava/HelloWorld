package ru.progwards.java1.lessons.abstractnum;

public abstract class  Number {

    String stringNumber = null;
    enum TypeNumber {INTEGER, DOUBLE}
    TypeNumber typeNumber = null;

    public Number() {
    }

    public Number(String stringNumber) {
        this.stringNumber = stringNumber;
        this.typeNumber = getNumberType(stringNumber);
    }

    public Number(String stringNumber, TypeNumber typeNumber) {
        this.stringNumber = stringNumber;
        this.typeNumber = typeNumber;
    }

    public Number mul(Number num){
//        switch (num.typeNumber){
//            case INTEGER:
//                return IntNumber.mul(num, num);
//        }
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

    public TypeNumber getNumberType(String stringNumber){
        try {
            Integer.parseInt(stringNumber);
            return TypeNumber.INTEGER;
        } catch (NumberFormatException e) {
            Double.parseDouble(stringNumber);
            return TypeNumber.DOUBLE;
        }
    }
}
