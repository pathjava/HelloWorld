package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    // решение дал Григорий
//    public static boolean containsDigit(int number, int digit){
//        if (number == 0 && digit == 0) return true;
//        int n = number;
//        while (n > 0) {
//            if (n % 10 == digit) return true;
//            n /= 10;
//        }
//        return false;
//    }

    public static int fiboNumber(int n){
        if (lastFibo.n == 0)
            return lastFibo.fibo;
            int f1 = 0;
            int f2 = 1;
            int t;
            int i = 1;
            while (i++ < n) {
                t = f2;
                f2 += f1;
                f1 = t;
            }
            lastFibo.n = n;
            lastFibo.fibo = f2;
            return f2;
    }
    private static CacheInfo lastFibo;

    static {
        lastFibo = new CacheInfo();
    }

    static class CacheInfo{
        public int n;
        public int fibo;

        CacheInfo(){
            n = -1;
        }

        CacheInfo(int n, int fibo) {
            this.n = n;
            this.fibo = fibo;
        }
    }

    public CacheInfo getLastFibo(){
        return lastFibo;
    }

    public void clearLastFibo(){
        lastFibo = null;
    }

    // решение дал Григорий
//    public static boolean isGoldenTriangle(int a, int b, int c){
//        float k = 0;
//        if (a == b)
//            k = (float)a / c;
//        else if (b == c)
//            k = (float)b / a;
//        else if (a == c)
//            k = (float)a / b;
//        return k > 1.61703f && k < 1.61903f;
//    }

    public static void main(String[] args) {
//        System.out.println(containsDigit(12345, 2));
        System.out.println(fiboNumber(20));
//        System.out.println(isGoldenTriangle(25, 25, 25));
    }
}