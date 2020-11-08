package ru.progwards.java1.lessons.queues;


public class Calculate {
    public static double calculation1() {
        /* создаем новый объект класса StackCalc чтобы иметь доступ к методам класса StackCalc */
        StackCalc stackCalc = new StackCalc();
        stackCalc.push(2.2);
        stackCalc.push(3);
        stackCalc.push(12.1);
        stackCalc.add(); // складываем (3+12.1)
        stackCalc.mul(); // умножаем 2.2 на результат (3+12.1)
        return stackCalc.pop();
    }

    public static double calculation2() {
        StackCalc stackCalc = new StackCalc();
        /* операции проводим сверху вниз  */
        stackCalc.push(737.22);
        stackCalc.push(24);
        stackCalc.add(); // результат сложения 761.22 (737.22+24)
        stackCalc.push(55.6);
        stackCalc.push(12.1);
        stackCalc.sub(); // результат вычитания 43.5 (55.6-12.1)
        stackCalc.div(); // результат деления 17,49931034482759 (737.22+24)/(55.6-12.1)
        stackCalc.push(19);
        stackCalc.push(3.33);
        stackCalc.sub(); // результат вычитания 15.67 (19-3.33)
        stackCalc.push(87);
        stackCalc.push(2);
        stackCalc.push(13.001);
        stackCalc.push(9.2);
        stackCalc.sub(); // результат вычитания 3.801 (13.001-9.2)
        stackCalc.mul(); // результат умножения 7.602 2*(13.001-9.2))
        stackCalc.add(); // результат сложения 94.602 (87+2*(13.001-9.2))
        stackCalc.mul(); // результат умножения 1482,41334 (19-3.33)*(87+2*(13.001-9.2))
        stackCalc.add(); // результат сложения 1499.9126503448276 (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2))
        return stackCalc.pop();
    }


    public static void main(String[] args) {
        System.out.println(calculation1());
        System.out.println(calculation2());
    }
}
