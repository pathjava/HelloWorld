// Oleg Kiselev
// 25.06.2020, 12:43

package ru.progwards.java2.lessons.classloader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeFileLastModified {

    public static void changeLastModifiedTime(String pathName) {
        if (pathName.isEmpty()) {
            System.out.println("File path not specified!");
            return;
        }
        File file = new File(pathName);
        if (!file.exists()) {
            System.out.println("File not exist!");
            return;
        }
        if (file.getName().endsWith(".class")) {
            // вывод оригинальной даты последнего изменения
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss");
            System.out.println("Original Last Modified Date : " + sdf.format(file.lastModified()));
            // установить эту дату
            String newLastModified = "05/16/2020/01/35/20";
            // необходимо преобразовать указанную дату в миллисекунды в длинное значение
            Date newDate = null;
            try {
                newDate = sdf.parse(newLastModified);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert newDate != null;
            file.setLastModified(newDate.getTime());
            // распечатать последнюю дату последнего изменения
            System.out.println("Latest Last Modified Date : " + sdf.format(file.lastModified()));
        }
    }

    public static void main(String[] args) {
        changeLastModifiedTime("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\classloader\\MathExpectation.class");
    }
}
