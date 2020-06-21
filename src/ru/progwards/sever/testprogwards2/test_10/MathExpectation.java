// Oleg Kiselev
// 21.06.2020, 12:31

package ru.progwards.sever.testprogwards2.test_10;

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
        for (int i = 0; i < data.length; i++)
            sum += data[i];
        double mathExpectation = (double) sum / data.length;
        return "Мат ожидание: " + mathExpectation;
    }
}