// Oleg Kiselev
// 08.05.2020, 15:16

package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {

    private static int count;
    private static int trueNumber;

    private static String someMethod(int n) {
        StringBuilder str = new StringBuilder();
        if (count == 0) {
            trueNumber = n;
            count++;
            return "" + n;
        } else if (trueNumber - n > 0) {
            if ((trueNumber - n) == 1) {
                count++;
                return " = " + n + "+" + 1;
            } else {
                count++;
//                if (trueNumber / 2 + 1 <= n)
                str.append(" = ").append(n).append("+").append(trueNumber - n);
                int temp = (trueNumber - n) / 2;
//                if (trueNumber - n > n)
//                    str.append(" = ").append((trueNumber - n) - temp).append("+").append((trueNumber - n) - temp).append("+").append(temp);
                int rem = (trueNumber - n) % 2;
                int temp2 = 0;
                int cycle = temp % 2 != 0 ? trueNumber - n : (trueNumber - n) - rem;
                while (temp != 0) {
                    StringBuilder str2 = new StringBuilder();
                    while (cycle >= temp && cycle > 0) {
                        str2.append("+").append(temp);
                        temp2 += temp;
                        cycle -= temp;
                    }
                    if (trueNumber - (n + temp2) == 0)
                        str.append(" = ").append(n).append(str2);
                    else
                        str.append(" = ").append(n).append(str2).append("+").append(rem);
                    temp--;
                    if (temp % 2 != 0)
                        rem = temp % 2;
                    temp2 = 0;
                    cycle = temp % 2 != 0 ? trueNumber - n : (trueNumber - n) - rem;
                }
                return str.toString();
            }
        }
        count++;
        return ":" + (n - 1);
    }


    public static String asNumbersSum(int number) {
        if (number <= 0)
            return String.valueOf(1);

        System.out.print(someMethod(number) + "");

        return asNumbersSum(number - 1);
    }


    public static String asNumbersSumTwo(int number){
        if (number <= 0)
            return String.valueOf(1);

        System.out.println(number + "");

        return asNumbersSumTwo(number - 1);
    }



    /* что с ним делать??? */
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
//        asNumbersSum(5);

        asNumbersSumTwo(10);
    }
}
