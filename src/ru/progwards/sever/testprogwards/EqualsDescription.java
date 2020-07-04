// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards;

//import org.glassfish.grizzly.utils.Pair;

public class EqualsDescription {
//    String first;
//    String second;
//    @Override
//    public boolean equals(Object o) {
//        //Если объект, с которым происходит сравнение, этим же объектом и является, то они равны. Сравнивает ссылки текущего объекта и принятого как аргумент.
//        if (this == o) return true;
//        //Если принятый объект null или другого класса, то возвращает false, не равны. При o == null они не равны, т.к. у null не может быть метода equals. Следовательно основной объект не null.
//        if (o == null || getClass() != o.getClass()) return false;
//
//        //кастует 'o' в Pair<?, ?> pair, чтобы иметь доступ к методам.
//        Pair<?, ?> pair = (Pair<?, ?>) o;
//
//        //Проверяет что свойство first текущего объекта не равен null. Если не равен, то сравнивает first текущего объекта, с pair.first. В случае, если они не равны, то ! инвертирует в true и условие if выполняется. Выполнится return false. Объекты не равны.
//        //Если first равен null, то проверяет pair.first. Если pair.first не равен null, то объекты не равны, возвращает false.
//        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
//
//        //Проверяет, что second  не равен null.
//        //Если не равен, то сравнивает second с pair.second.
//        //Если second == pair.second, то сначала первый ! в  "!second.equals(pair.second)" инвертирует его в его в false, а второй !, который идет сразу после return, инвертирует в true. Значит вернет true, объекты равны.
//        //Если second == null, то проверяет второе условие. Если pair.second != null, то вернет true, который инвертируется в false.
//        return !(second != null ? !second.equals(pair.second) : pair.second != null);
//
//    }
}
