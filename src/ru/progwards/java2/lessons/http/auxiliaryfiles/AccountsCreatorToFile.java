// Oleg Kiselev
// 24.07.2020, 18:05

package ru.progwards.java2.lessons.http.auxiliaryfiles;

import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import ru.progwards.java2.lessons.http.model.Account;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AccountsCreatorToFile implements AccountsCreator {

    /* данный класс и метод созданы только для тестирования функционала */
    private static final String PATH_FILE = "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\http\\model\\accounts.json";

    @Override
    public void creator() {
        Map<String, Account> accountMap = new HashMap<>();
        int rand = ThreadLocalRandom.current().nextInt(300, 700);
        Account account;
        for (int i = 1; i <= 10; i++) {
            account = new Account();
            account.setDate(new Date());
            account.setHolder("Account_" + i);
            account.setPin(i);
            account.setId("" + i);
            account.setAmount(rand * i);
            accountMap.put(account.getId(), account);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ObjectMapper mapper = null;
        try {
            mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(PATH_FILE).toFile(), accountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* for testing */
//        for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
//            String jsonString = null;
//            try {
//                jsonString = mapper.writeValueAsString(entry.getValue());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println(jsonString);
//        }
    }

    /* for testing */
    public static void main(String[] args) {
        AccountsCreatorToFile cfa = new AccountsCreatorToFile();
        cfa.creator();
    }

}
