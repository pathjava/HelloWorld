package ru.progwards.sever.testprogwards.test_10;

import java.io.RandomAccessFile;

public class Test_04 {
    public String setStars(String filename) {
        int starInsert = (int) '*';
        StringBuilder result = new StringBuilder();
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            long sumBytes = file.length();
//            for (long i = 9; i < sumBytes; i += 10){
//                file.seek(i);
//                result.append((char)file.read());
//                file.seek(i);
//                file.write(starInsert);
//            }
            long i = 9;
            while (i < sumBytes) {
                file.seek(i);
                result.append((char) file.read());
                file.seek(i);
                file.write(starInsert);
                i += 10;
            }
        } catch (Throwable e) {

        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Test_04().setStars("src/ru/progwards/sever/testprogwards/test_10/test10.txt"));
    }
}

