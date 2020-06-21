// Oleg Kiselev
// 21.06.2020, 12:27

package ru.progwards.sever.testprogwards2.test_10;

public interface Task {
    // методы для получения и установки
    // времени создания файла
    long getModifiedTime();

    void setModifiedTime(long time);

    // метод для обработки данных
    // и возвращения результата в виде строки
    String process(byte[] data);
}