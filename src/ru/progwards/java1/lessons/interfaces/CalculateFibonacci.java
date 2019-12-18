package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    // данное решение в значительной мере показал Григорий
    public static int fiboNumber(int n){
        if (lastFibo.n == 0)
            return lastFibo.fibo;
            int f1 = 0, f2 = 1;
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

    public static class CacheInfo{
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

    static public CacheInfo getLastFibo(){
        return lastFibo;
    }

    static public void clearLastFibo(){
        if (lastFibo.n == 0)
        lastFibo = null;
    }

    public static void main(String[] args) {
        System.out.println(fiboNumber(20));
        System.out.println(getLastFibo());
//        System.out.println(clearLastFibo());
    }
}