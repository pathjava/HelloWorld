// Oleg Kiselev
// 08.05.2020, 15:16

package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {

    StringBuilder stringBuilder = new StringBuilder();

    private static int count;
    private static int trueNumber;

    private static String someMethod(int n) {
        if (count == 0) {
            trueNumber = n;
            count++;
            return " =";
        } else if (trueNumber - n > 0) {
            if ((trueNumber - n) == 1) {
                count++;
                return "+" + 1;
            }
        }
        count++;
        return ":" + (n - 1);
    }


    public static String asNumbersSum(int number) {
        if (number <= 0)
            return String.valueOf(1);

        System.out.print(number + "" + someMethod(number) + " ");

        return asNumbersSum(number - 1);
    }

//        5 = 4 + asNumbersSum(1) = 3 + asNumbersSum(2) = 2 + asNumbersSum(3) = 1 + asNumbersSum(4);

    /* Реализовать класс, AsNumbersSum, содержащий метод
     * public static String asNumbersSum(int number), который раскладывает параметр number,
     * как всевозможные уникальные комбинации сумм натуральных чисел, например:
     * 5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1

     * Строка должна содержать результат, отформатированный точно, как указано в примере.
     * Повторные комбинации не допускаются, например, если а строке уже есть 3+2, то 2+3 там быть не должно.
     * Задача должна быть решена методом рекурсии, циклы использовать запрещено.
     */

    public static void main(String[] args) {
//        System.out.println(asNumbersSum(5));
        asNumbersSum(5);
    }
}
