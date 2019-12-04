package ru.progwards.java1.lessons.arrays;

public class DIntArray {
    private int[] a;

    DIntArray(){
    }

    public void add(int num){
        if(a.length > 0){
            int[] tempArr = new int[(num)];
            System.arraycopy(a,0,tempArr,0,a.length);
            a = new int[(num+1)];
            System.arraycopy(tempArr,0,a,0,tempArr.length);
            tempArr = null;
        }
        else{
            a = new int[1];
        }
    }

    public void atInsert(int pos, int num){
        if (a.length < pos) {
            add(pos);
        }
        a[pos] = num;
    }

    public void atDelete(int pos){

    }

    public int at(int pos){
        return pos;
    }
}
