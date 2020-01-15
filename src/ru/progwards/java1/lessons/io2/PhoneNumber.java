package ru.progwards.java1.lessons.io2;

/*
1) заводим переменную formatNumber
2) в цикле for строку phone переводим в массив toChartArray и условием if проверяем isDigit и помещаем в переменную formatNumber
3) проверяем formatNumber на длину строки (как проверяем?) и если 11 цифр,
методом substring(int from) забираем конечные 10 цифр и помещаем в formatNumber, после чего на первую позицию добавляе  +7
4) далее методом insert(int offset, ...) поочередно вставляем ( и ), а также -
5) делаем return formatNumber
*/

public class PhoneNumber {
    public static String format(String phone){
        String formatNumber = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : phone.toCharArray()){
            if (Character.isDigit(c)){
                stringBuilder.append(c);
            }
            formatNumber = stringBuilder.toString();
        }

        if(formatNumber.length() < 10 || formatNumber.length() > 11) {
            throw new RuntimeException("В номере должно быть 10 или 11 цифр!");
        }

        if (formatNumber.length() >= 11){
            // берем 10 цифр с конца строки
            formatNumber = (formatNumber.substring(formatNumber.length() - 10));
        }
        // вариант реализации через substring
        return "+7(" + formatNumber.substring(0,3) + ")" + formatNumber.substring(3,6) + "-" + formatNumber.substring(6);

        // вариант реализации через StringBuilder и insert
//        StringBuilder strFormat = new StringBuilder(formatNumber);
//        strFormat.insert(0, "+7").insert(2, "(").insert(6, ")").insert(10, "-");
//        return strFormat.toString();
    }

    public static void main(String[] args) {
        System.out.println(format("+(951) 132-04-75"));
    }
}
