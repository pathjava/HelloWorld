// Oleg Kiselev
// 08.05.2020, 15:16

package ru.progwards.java2.lessons.recursion;

import org.apache.logging.log4j.core.util.JsonUtils;

public class AsNumbersSum {

    public static String asNumbersSum(int number) {
        if (number <= 0)
            return 0 + " ";
        if (number == 1) {
            System.out.println(number);
            return asNumbersSum(number - 1) + 1 + " ";
        }
        if (number == 2) {
            System.out.println(number);
            return asNumbersSum(number - 1) + 2 + " ";
        }
        if (number == 3) {
            System.out.println(number);
            return asNumbersSum(number - 1) + 3 + " ";
        }
        if (number == 4) {
            System.out.println(number);
            return asNumbersSum(number - 1) + 4 + " ";
        }

//        System.out.println(number);
        return asNumbersSum(number - 1) + " ";
    }


    /* Реализовать класс, AsNumbersSum, содержащий метод
     * public static String asNumbersSum(int number), который раскладывает параметр number,
     * как всевозможные уникальные комбинации сумм натуральных чисел, например:
     * 5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1

     * Строка должна содержать результат, отформатированный точно, как указано в примере.
     * Повторные комбинации не допускаются, например, если а строке уже есть 3+2, то 2+3 там быть не должно.
     * Задача должна быть решена методом рекурсии, циклы использовать запрещено.
     */

    public static void main(String[] args) {
        System.out.println("5 = " + asNumbersSum(5));
    }
}
