// Oleg Kiselev
// 21.06.2020, 12:31

package ru.progwards.sever.testprogwards2.test_10;

public class CheckSum implements Task {
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
        byte checkSum = 0;
        for (byte datum : data) checkSum += datum;
        return "Sum: " + checkSum;
    }
}