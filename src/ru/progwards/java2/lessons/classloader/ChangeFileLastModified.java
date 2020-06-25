// Oleg Kiselev
// 25.06.2020, 12:43

package ru.progwards.java2.lessons.classloader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeFileLastModified {

    public static void main(String[] args) {

        try {
            File file = new File("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\classloader\\MathExpectation.class");

            // вывод оригинальной даты последнего изменения
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println("Original Last Modified Date : "
                    + sdf.format(file.lastModified()));

            // установить эту дату
            String newLastModified = "05/15/2020";

            // необходимо преобразовать указанную дату в миллисекунды в длинное значение
            Date newDate = sdf.parse(newLastModified);
            file.setLastModified(newDate.getTime());

            // распечатать последнюю дату последнего изменения
            System.out.println("Latest Last Modified Date : "
                    + sdf.format(file.lastModified()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
