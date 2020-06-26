// Oleg Kiselev
// 26.06.2020, 19:20

// Oleg Kiselev
// 24.06.2020, 17:55

package ru.progwards.java2.lessons.classloader.pathloader;

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