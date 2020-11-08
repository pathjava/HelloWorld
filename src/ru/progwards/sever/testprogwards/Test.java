// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards;

class Test {

    public static int a = 1;
    public static int b = 3;
    public static int c = 9;
    public static int d = 27;

    public static void main(String[] args) {
        int value = 10;
        System.out.println(value);


        int result = -a + b - c + d;
        System.out.println(result);


    }


    public enum Grade {VERYBAD, BAD, SATISFACTORILY, GOOD, EXCELLENT, NOTDEFINED}

    static Grade intToGrade(int grade) {
        switch (grade) {
            case 1:
                return Grade.VERYBAD;
            case 2:
                return Grade.BAD;
            case 3:
                return Grade.SATISFACTORILY;
            case 4:
                return Grade.GOOD;
            case 5:
                return Grade.EXCELLENT;
            default:
                return Grade.NOTDEFINED;
        }
    }
}

