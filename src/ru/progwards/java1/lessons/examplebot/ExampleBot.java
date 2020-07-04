//package ru.progwards.java1.lessons.examplebot;
//
//import java.util.Scanner;
//
//import org.telegram.telegrambots.ApiContextInitializer;
//
//import ru.progwards.java1.telegrambot.ProgwardsTelegramBot;
//import ru.progwards.java1.telegrambot.ProgwardsTelegramBot.FoundTags;
//
//public class ExampleBot extends ProgwardsTelegramBot {
//
//    private final String menu = "У нас есть пицца, напитки, пироги и десерты";
//
//    private static final String orderKey = "order";
//    private static final String addressKey = "address";
//
//    private boolean stop = false;
//
//    // Пердложить группы блюд
//    // спросить адрес доставки
//    String finishCheck(Integer userid) {
//        // проверить что все 4 группы блюд в заказе
//        // если какой-то группы нет && бот не предлагал
//        // то предложить и учтановить ключ, что бы не предлагать 2 раза
//        if (getUserData(userid, "Pizza") == null) {
//            setUserData(userid, "Pizza", "2");
//            return "Вы не заказали ни одной пиццы!";
//        } else if (getUserData(userid, "Beverages") == null) {
//            setUserData(userid, "Beverages", "2");
//            return "Вы не заказали ни одного напитка!";
//        }
//
//        // спросить адрес доставки
//        // проверить что заказ не пуст
//        if (getUserData(userid, addressKey) == null) {
//            setUserData(userid, addressKey, "*");
//
//            return "Укажите, пожалуйста адрес доставки";
//        }
//        stop = true;
//        // если заказ пуст выдать другое сообщение
////        if (getUserData(userid, orderKey) == null){
////            return "Вы ничего не заказали!";
////        }
//        return "Спасибо за заказ.";
//    }
//
//    // сохранить в заказ
//    void saveOrderItem(Integer userid, FoundTags tags) {
//        // считать количество
//        Integer count = 0;
//        String str = getUserData(userid, orderKey);
//        if (str != null)
//            count = Integer.parseInt(str);
//        // инкрементировать количество
//        count++;
//        // сохранить количество
//        setUserData(userid, orderKey, count.toString());
//        // сохранить позицию в заказе как orderKey + count
//        // ключи - order1, order2
//        // данные - getLastFound(tags)
//
//        // дополнительно 1
//        // проверить связанные покупки
//        // если он заказывает картошку, то предложить соус, если не предлагали
//    }
//
//    String getOrder(Integer userid) {
//        // считать количество
//
//        // в цикле по каждому элементу вывести содержимое
//        // ключ orderKey + номер
//        return "Выш заказ";
//    }
//
//    /**
//     * Метод, который возвращает ответ бота
//     *
//     * @return ответ
//     */
//    @Override
//    public String processMessage(Integer userid, String text) {
//        // проверяем, спрашивали ли адрес доставки
//        if (getUserData(userid, addressKey) != null && getUserData(userid, addressKey).equals("*")) {
//            setUserData(userid, addressKey, text);
//            return finishCheck(userid);
//        }
//        // сброс всех данных для пользователя - нужно для тестирования
//        if (text.equals("/reset"))
//            cleartUserData(userid);
//
//        // ищем подходящие тэги рлд запрос из заданных через addTags
//        FoundTags tags = checkTags(text);
//        // если найдено всего один вариант
//        if (foundCount(tags) == 1) {
//            if (checkLastFound(tags, "привет"))
//                return "Приветствую Вас!\nЧто желаете? " + menu;
//            if (checkLastFound(tags, "конец"))
//                return finishCheck(userid);
//            if (checkLastFound(tags, "дурак"))
//                return "Не надо ругаться. Я не волшебник, я только учусь";
//            if (checkLastFound(tags, "нет"))
//                return "Ну нет, так нет";
//            if (checkLastFound(tags, "заказ"))
//                return getOrder(userid);
//            // Добавить связанные предложения, например если он заказывает картошку, то предложить соус,
//            // если отказывается - сохранить флажок, что бы бесконечно не предлагать
//            // дополнительно 2
//            // реализовать отмену позиции заказа
//            if (checkLastFound(tags, "Пицца гавайская") || checkLastFound(tags, "Пицца маргарита") || checkLastFound(tags, "Пицца пеперони") || checkLastFound(tags, "Пицца Магия") || checkLastFound(tags, "Пицца Тарантелла"))
//                setUserData(userid, "Pizza", "1");
//            if (checkLastFound(tags, "Кола") || checkLastFound(tags, "Холодный чай") || checkLastFound(tags, "Сок") || checkLastFound(tags, "Коктейль молочный") || checkLastFound(tags, "Коктейль молочный банановый") || checkLastFound(tags, "Коктейль молочный шоколадный"))
//                setUserData(userid, "Beverages", "1");
//
//            saveOrderItem(userid, tags);
//            return "Отлично, добавляю в заказ " + getLastFound(tags) + " Желаешь что-то еще?";
//        }
//        if (foundCount(tags) > 1)
//            return "Под твой запрос подходит: \n" + extract(tags) + "Выбери что-то одно, и я добавлю это в заказ.";
//        return "Я не понял, возможно у нас этго нет, попробуй сказать по другому. " + menu;
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Hello bot!");
//        ApiContextInitializer.init();
//
//        // инициализируем бота
//        ExampleBot bot = new ExampleBot();
//        bot.username = "SmartFoodCrazyBot";
//        bot.token = "836953809:AAH7TbXbrg2mdWbxdy6veLc5l8iCBFV5llI";
//
//        // наполняем тэгами
//        bot.addTags("привет", "привет, здасьте, здравствуйте, добр, день, вечер, утро, hi, hello, хай");
//        bot.addTags("конец", "конец, все, стоп, нет, довольно, больше не надо, хватит");
//        bot.addTags("дурак", "дурак, идиот, тупой, глупый, осел, осёл");
//
//        // добавлено
//        bot.addTags("заказ", "заказ");
//        bot.addTags("нет", "нет");
//
//        bot.addTags("Пицца гавайская", "гавайск, пицц, ананасы, курица");
//        bot.addTags("Пицца маргарита", "маргарит, пицц, моцарелла, сыр, кетчуп, помидор, томат");
//        bot.addTags("Пицца пеперони", "пеперони, пицц, салями, колюас, сыр, кетчуп, помидор, томат");
//        bot.addTags("Пицца Магия", "лук, лучок, огурчик, огурц, сыр, томат, помидор, маринован");
//        bot.addTags("Пицца Тарантелла", "лук, лучок, маринован, сыр, томат, помидор, гриб, шампиньон");
//
//        bot.addTags("Пирог с вишней", "дрожжев, вишн, начинк, пирог, выпечк");
//        bot.addTags("Пирог с творогом", "дрожжев, творог, яйц, пирог, выпечк");
//        bot.addTags("Пирог с курочкой и сыром", "дрожжев, творог, кириц, киринн, лук, майон, филе, сыр, начинк, пирог, выпечк");
//
//        bot.addTags("Торт тирамису", "десерт, кофе, маскарпоне, бисквит");
//        bot.addTags("Торт медовик", "десерт, мед, бисквит");
//        bot.addTags("Эклеры", "десерт, заварной, крем, тесто");
//        bot.addTags("Торт шоколадное изобилие", "бисквит, медов, мёд, сметан, крем, шоколад, торт, десерт");
//
//        bot.addTags("Кола", "напит, пить, кола");
//        bot.addTags("Холодный чай", "напит, пить, чай, липтон, лимон");
//        bot.addTags("Сок", "напит, пить, сок, апельсиноый, яблочный, вишневый");
//        bot.addTags("Коктейль молочный", "молочн, молок, коктейл, наит, пить, морожен");
//        bot.addTags("Коктейль молочный банановый", "молочн, молок, коктейл, наит, пить, морожен, банан");
//        bot.addTags("Коктейль молочный шоколадный", "молочн, молок, коктейл, наит, пить, морожен, шоколад");
//
//        //bot.start();
//        bot.test();
//    }
//
//    void test() {
//        Scanner in = new Scanner(System.in);
//        String input;
//        do {
//            input = in.nextLine();
//
//            System.out.println(processMessage(123, input));
//        } while (!stop);
//        in.close();
//    }
//}
