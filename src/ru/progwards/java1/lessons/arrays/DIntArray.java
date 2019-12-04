package ru.progwards.java1.lessons.arrays;

public class DIntArray {
    private int[] a;

    DIntArray(){
    }

    public void add(int num){
        growArr(a.length);
        a[a.length-1] = num;
    }

    public void atInsert(int pos, int num){
        if(a.length < pos){
            growArr(pos);
        }
        a[pos] = num;
    }

    public void atDelete(int pos){
        a[pos] = 0;
        shrinkArr();
    }

    private void shrinkArr(){
        int totalNotNull = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] != 0)
                totalNotNull++;
        }
        int[] tempArr = new int[totalNotNull];
        int currentPos = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] != 0){
                tempArr[currentPos] = a[i];
                currentPos++;
            }
        }
        a = new int[totalNotNull];
        System.arraycopy(tempArr,0,a,0,tempArr.length);
        tempArr = null;
    }


    private void growArr(int newSize){
        if(a.length > 0){
            int[] tempArr = new int[(newSize)];
            System.arraycopy(a,0,tempArr,0,a.length);
            a = new int[(newSize+1)];
            System.arraycopy(tempArr,0,a,0,tempArr.length);
            tempArr = null;
        }
        else{
            a = new int[1];
        }
    }

    public int at(int pos){
        return pos;
    }
}
