// Oleg Kiselev
// 26.06.2020, 19:20

// Oleg Kiselev
// 24.06.2020, 17:56

package ru.progwards.java2.lessons.classloader.loader;

public class Dispersion implements Task {
    private long modifiedTime;

    @Override
    public long getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public void setModifiedTime(long time) {
        modifiedTime = time;
    }

    @Override
    public String process(byte[] data) {
        if (data.length == 0)
            return "Нет данных";
        // мат ожидание
        long sum = 0;
        for (byte datum : data) sum += datum;
        double mathExpectation = (double) sum / data.length;
        // дисперсия
        sum = 0;
        for (byte datum : data) {
            double diff = mathExpectation - datum;
            sum += diff * diff;
        }
        double dispersion = (double) sum / data.length;
        return "Дисперсия: " + dispersion + ", среднекв.отклонение: " + Math.sqrt(dispersion);
    }
}