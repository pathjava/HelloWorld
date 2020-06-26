// Oleg Kiselev
// 26.06.2020, 19:20

// Oleg Kiselev
// 24.06.2020, 17:56

package ru.progwards.java2.lessons.classloader.loader;

public class MathExpectation implements Task {
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
        long sum = 0;
        for (byte datum : data) sum += datum;
        double mathExpectation = (double) sum / data.length;
        return "Мат ожидание: " + mathExpectation;
    }
}