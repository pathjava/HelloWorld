// Oleg Kiselev
// 26.06.2020, 19:20

// Oleg Kiselev
// 24.06.2020, 17:55

package ru.progwards.java2.lessons.classloader.loader;

public interface Task {
    // методы для получения и установки времени создания файла
    long getModifiedTime();

    void setModifiedTime(long time);

    // метод для обработки данных и возвращения результата в виде строки
    String process(byte[] data);
}