package ru.progwards.java_junior;

public class Chapter_1_1 {
    public static void main(String[] args) {
        double inch, meter;
        int counter;

        counter = 0;
        for (inch = 1; inch < 134; inch++){
            meter = inch * 39.37;
            System.out.println(inch + " = " + meter);

            counter++;
            if (counter == 12){
                System.out.println();
                counter = 0;
            }
        }
    }
}
