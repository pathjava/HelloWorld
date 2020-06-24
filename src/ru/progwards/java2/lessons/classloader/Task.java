// Oleg Kiselev
// 24.06.2020, 17:55

package ru.progwards.java2.lessons.classloader;

public interface Task {
    // методы для получения и установки времени создания файла
    long getModifiedTime();

    void setModifiedTime(long time);

    // метод для обработки данных и возвращения результата в виде строки
    String process(byte[] data);
}