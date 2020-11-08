package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    //изначально решение увидел в обсуждениях, но там сразу было указано, что оно ошибочное
    //взял его за основу и далее переработал
    int N = 0;

    private boolean[] sieve;

    public Eratosthenes(int N) {
        this.N = N;
        sieve = new boolean[N + 1];
        Arrays.fill(sieve, true);
        sift();
    }

    private void sift() {
        sieve[0] = false;
        sieve[1] = false;
//        int i = 2;
//        int j = 2;
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i]) {
                for (int j = 2; i * j < sieve.length; ++j) {
                    sieve[i * j] = false;
                }
            }
        }

    }

    public boolean isSimple(int n) {
        return sieve[n];
    }

    public static void main(String[] args) {
        Eratosthenes eratosthenes = new Eratosthenes(300);
        System.out.println(eratosthenes.isSimple(79));
    }
}
