package ru.progwards.sever;

public class Test_H6_2 {
    public static void main(String args[]) {
        int[] ar = {12,-3,8,1,19,22,0};
        int temp;

        for(int i = 0; i < ar.length; i++){
            for (int j = 1; j < (ar.length - i); j++) {
                if(ar[j - 1] > ar[j]){
                    temp=ar[j-1];
                    ar[j-1]=ar[j];
                    ar[j]=temp;
                }
            }
        }

        for(int i = 0; i<ar.length;i++){
            System.out.print(ar[i] + " ");
        }
    }
}
