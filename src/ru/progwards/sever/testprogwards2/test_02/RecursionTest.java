// Oleg Kiselev
// 06.05.2020, 20:45

package ru.progwards.sever.testprogwards2.test_02;

public class RecursionTest {

//    public static int sumSequence(int n) {
//        if (n == 1)
//            return n;
//        return sumSequence(n - 2) + n;
//    }

    public static int sumSequenceTwo(int n) {
        if (n == 1)
            return n;
        int sumSequence = sumSequenceTwo(n - 2);
        return n + sumSequence;
    }


    public static int factorial(int val) {
        if (val <= 1)
            return 1;
        return factorial(val - 1) * val;
    }

    public static int factorialTwo(int n) {
        if (n == 0)
            return 1;
        int factorialMinusOne = factorialTwo(n - 1);
        return n * factorialMinusOne;
    }

    public static int factorialThree(int x) {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }


    /* После деления 123 с остатком на 10 (n % 10), и деления 123 на 10 (n/10), мы получаем два результата: 3 и 12,3, которые складываем.
     * Но, т.к., тип данных целочисленный (int), то в 12,3 действительно значение до запятой, т.е., 12. Сложили 3 и 12, получили 15.
     * Но, т.к., в условии остановки рекурсии имеем: n < 10, то, рекурсионный шаг (n % 10 + recursion(n/10) повторяется,
     * только теперь рекурсионной переменной (n), в рез-те первого шага рекурсионной ф-ции, возвращено значение 15.
     * Т.е., в итоге: n%10 == 5, а n/10 == 1,5 (но, т.к., тип данных int, то цифра после запятой отбрасывается).
     * Итого: 5+1 == 6, что соответствует базовому случаю: n < 10.
     */
    public static int recursion(int n) {
        if (n < 10) {
            return n;
        } else {
            return n % 10 + recursion(n / 10);
        }
    }


    public static void main(String[] args) {
//        System.out.println(sumSequence(2));
        System.out.println(sumSequenceTwo(3));

        System.out.println(factorial(5));
        System.out.println(factorialTwo(5));
        System.out.println(factorialThree(5));

        System.out.println(recursion(123));
    }
}
