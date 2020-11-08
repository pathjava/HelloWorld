package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
    public static String format(String phone) {
        String formatNumber = "";
        StringBuilder stringBuilder = new StringBuilder();
        /* проверяем наличие цифр во входящей строке и сохраняем только цифры, отфильтровав все остальное*/
        for (char c : phone.toCharArray()) {
            if (Character.isDigit(c)) {
                stringBuilder.append(c);
            }
            formatNumber = stringBuilder.toString();
        }
        /* проверяем сколько цифр пришло и если не соответствует, выбрасываем ошибку*/
        if (formatNumber.length() < 10 || formatNumber.length() > 11) {
            throw new RuntimeException("В номере должно быть 10 или 11 цифр!");
        }
        /* забираем из строки цифр только последние 10 символов
         * таким образом откидывая 8 в начале */
        if (formatNumber.length() >= 11) {
            // берем 10 цифр с конца строки
            formatNumber = (formatNumber.substring(formatNumber.length() - 10));
        }
        /* вариант реализации через substring */
        return "+7(" + formatNumber.substring(0, 3) + ")" + formatNumber.substring(3, 6) + "-" + formatNumber.substring(6);

        /* вариант реализации через StringBuilder и insert */
//        StringBuilder strFormat = new StringBuilder(formatNumber);
//        strFormat.insert(0, "+7").insert(2, "(").insert(6, ")").insert(10, "-");
//        return strFormat.toString();
    }

    public static void main(String[] args) {
        System.out.println(format("+(951) 132-04-75"));
    }
}
