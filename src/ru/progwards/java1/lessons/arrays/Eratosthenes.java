package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    int N = 0;

    private boolean[] sieve;

    public Eratosthenes(int N){
        this.N = N;
        sieve = new boolean[N];
        Arrays.fill(sieve, true);
        sift();
    }

    private void sift(){
        int i = 2;
        int j = 2;
        for (i = 2; i < (N - 1); i++){
            for (j = 2; j < i; j++){
                if ((i % j) == 0){
                    sieve[i] = false;
                    break;
                }
            }
        }

    }

    public boolean isSimple(int n){
        return sieve[n];
    }

    public static void main(String[] args) {
        Eratosthenes eratosthenes = new Eratosthenes(2000);
        System.out.println(eratosthenes.isSimple(109));
    }
}
