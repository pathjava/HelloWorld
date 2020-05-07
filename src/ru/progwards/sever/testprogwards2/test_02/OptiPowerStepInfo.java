// Oleg Kiselev
// 07.05.2020, 12:42

package ru.progwards.sever.testprogwards2.test_02;

public class OptiPowerStepInfo {

    static double power(double val, int pow) {
        System.out.println("прямой ход, power = " + pow);
        double result = 0;
        switch (pow) {
            case 0:
                result = 1;
                break;
            case 1:
                result = val;
                break;
            default: {
                double val_halfPower = power(val, pow / 2);
                if (pow % 2 == 1) {
                    result = val * val_halfPower * val_halfPower;
                } else {
                    result = val_halfPower * val_halfPower;
                }
            }
        }
        System.out.println("обратный ход, power = " + pow);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 1000));
    }

}
