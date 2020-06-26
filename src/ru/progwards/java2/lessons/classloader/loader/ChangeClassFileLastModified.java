// Oleg Kiselev
// 26.06.2020, 19:20

// Oleg Kiselev
// 25.06.2020, 12:43

package ru.progwards.java2.lessons.classloader.loader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeClassFileLastModified {

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
            /* выводим оригинальную дату последнего изменения согласно шаблона SimpleDateFormat */
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss");
            System.out.println("Original Last Modified Date : " + sdf.format(file.lastModified()));
            /* устанавливаем новую дату и время */
            String newLastModified = "05/16/2020/01/35/20";
            /* преобразовываем дату и время в миллисекунды */
            Date newDate = null;
            try {
                newDate = sdf.parse(newLastModified);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            /* устанавливаем в файл новую дату и время */
            assert newDate != null;
            file.setLastModified(newDate.getTime());
            /* выводим измененную дату и время */
            System.out.println("Latest Last Modified Date : " + sdf.format(file.lastModified()));
        }
    }

    public static void main(String[] args) {
        changeLastModifiedTime("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\classloader\\loader\\MathExpectation.class");
    }
}
